import java.util.*;
class LibrarySystem {

    //list of books
    private LinkedList<Book> booksList;
    //list of members
    private LinkedList<LibMember> membersList;
    //actual number of objects of type Book in bookList
    private int booksListSize;
    //actual number of objects of type LibMember in membersList
    private int membersListSize;

    //default constructor
    public LibrarySystem() {
        booksList = new LinkedList<Book>();
        membersList = new LinkedList<LibMember>();
        booksListSize = 0;
        membersListSize = 0;
    }
    // inserts a new BookObject at the end of the bookList
    public boolean addBook(Book Bookobject) {
        //if the object already exists in the list, it will not be added
        for (int i = 0; i < booksList.size(); i++) {
            if (booksList.get(i).equals(Bookobject)
                    || booksList.get(i).getAccessionNum() == Bookobject.getAccessionNum())
                return false;
        }
        if (Bookobject.getIsbn().length() != 13 || Bookobject.getAccessionNum() < 1001)
            return false;
        booksList.add(Bookobject);
        booksListSize++;
        return true;
    }
    //delete a book from bookList by accession number of the book
    public boolean deleteBook(long accessionNum) {
        //if the list is empty it will be return.
        if (booksList.isEmpty()) return false;
        for (int i = 0; i < booksList.size(); i++) {
            if (booksList.get(i).getAccessionNum() == accessionNum) {

                // check for the book is not issued to any member
                if (booksList.get(i).getIssuedTo() == null) {
                    booksList.remove(i);
                    booksListSize--;
                    return true;
                }
            }
        }
        return false;
    }

    // addMember insert a new LibMember object at the end of memberList.
    public boolean addMember(LibMember newM) {
        for (int i = 0; i < membersList.size(); i++) {
            if (membersList.get(i).equals(newM))
                //it will be not added if the new member is already in the list
                return false;
        }
        char g = newM.getGender();
        // the gender must be only male or female
        if (g != 'm' && g != 'M' && g != 'F' && g != 'f')
            return false;
        membersList.add(newM);
        membersListSize++;
        return true;
    }

    public boolean deleteMember(long cpr) {

        if (membersList.isEmpty()) return false;
        for (int i = 0; i < membersList.size(); i++) {
            if (membersList.get(i).getCprNum() == cpr) {
                //Check if any Book is issued to the member
                if (membersList.get(i).getNumBooksIssued() == 0) {
                    membersList.remove(i);
                    membersListSize--;
                    return true;
                }
            }
        }
        return false;
    }

    //search for bookList by accession number
    public int searchBook(long accessionNum) {
        if (booksList.isEmpty()) return -1;
        for (int i = 0; i < booksList.size(); i++) {
            if (booksList.get(i).getAccessionNum() == accessionNum) ;

            return i;
        }
        return -1;
    }

    //search for member list by cpr number
    public int searchMember(long cpr) {
        if (membersList.isEmpty()) return -1;

        for (int i = 0; i < membersListSize; i++) {
            if (membersList.get(i).getCprNum() == cpr) return i;
        }
        return -1;
    }

    //return true if the book list is empty, else return false
    public boolean isEmptyBookList() {
        return (booksListSize == 0);
    }

    //return true if member list is empty, other ways return false
    public boolean isEmptyMemberList() {
        return (membersListSize == 0);
    }

    //return the number of book list
    public int sizeBooksList() {
        return booksListSize;
    }

    //return the number of member list
    public int sizeMembersList() {
        return membersListSize;
    }


    public boolean issueBook(long accessionNum, long cpr) {

        int bookInd = searchBook(accessionNum);
        int memberInd = searchMember(cpr);
        // check if that both member and book are exist to accept
        if (bookInd == -1 || memberInd == -1)
            return false;

        //Make sure that the Book is not issued to any member and
        //the member has less than 10 books issued to him/her
        if (booksList.get(bookInd).getIssuedTo() != null ||
                membersList.get(memberInd).getNumBooksIssued() >= 10)
            return false;

        LibMember member = membersList.get(memberInd);
        Book book = booksList.get(bookInd);
        Book[] booksIssued = member.getBooksIssued();

        //add book object in the booksIssued array for the member
        booksIssued[member.getNumBooksIssued()] = book;
        //set booksIssued array to the new one
        member.setBooksIssued(booksIssued);
        //increase numBooksIssued by 1
        member.setNumBooksIssued(member.getNumBooksIssued() + 1);
        //Make the issued instance variable of the Book object a reference to the member
        book.setIssuedTo(member);
        return true;
    }

    //return true if the book object exists in the book list and is issued to a member
    //other ways return false
    public boolean isBookIssued(long accessionNum) {
        int index = searchBook(accessionNum);
        if (index == -1)
            return false;
        return (booksList.get(index).getIssuedTo() != null);
    }


    public boolean returnBook(long accessionNum)
    {int bookInd = searchBook(accessionNum);
        //check if the book exists in the books list
        if(bookInd == -1)
            return false;
        // make sure that the book is issued to a member
        if(!isBookIssued(accessionNum))
            return false;

        //if the book can be returned
        Book book = booksList.get(bookInd);
        LibMember member = book.getIssuedTo();
        Book[] booksIssued = member.getBooksIssued();

        int removeIndex = 0;
        for(int i = 0; i < member.getNumBooksIssued(); i++)
        {

            //remove the book issued by the member from its booksIssued array
            if(booksIssued[i].equals(booksList.get(bookInd))) {

                //shifting
                for(int j = removeIndex; j < member.getNumBooksIssued() - 1; j++)
                    booksIssued[j] = booksIssued[j+1];
                // delete last one because it is empty now
                booksIssued[member.getNumBooksIssued()- 1] = null;
                // decrease numBooksIssued by one
                member.setNumBooksIssued(member.getNumBooksIssued() - 1);
                // set booksIssued array to the new one
                member.setBooksIssued(booksIssued);
                //make issuedTo instance of the book object null
                book.setIssuedTo(null);
                return true;
            }
            removeIndex++;
        }
        return false;
    }

    //print the details of all books issued to the member
    public void printBooksIssued(long cpr)
    {
        int memberInd = searchMember(cpr);
        //check if the member exists then print the information of the books issued to them
        if(memberInd != -1) {
            if (membersList.get(memberInd).getNumBooksIssued()==0)
                System.out.println("No Books Issued");
            else if (membersList.get(memberInd).getNumBooksIssued()!=0){
                LibMember member = membersList.get(memberInd);
                Book[] booksArr = member.getBooksIssued();
                for(int i=0; i<member.getNumBooksIssued(); i++) {
                    Book b = booksArr[i];
                    System.out.println(b.toString());
                }
            } }
        else
            System.out.println("Member does not exist");
    }


}