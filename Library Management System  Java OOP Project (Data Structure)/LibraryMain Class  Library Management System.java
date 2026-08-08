
import java.util.*;
public class LibraryMain {
    private static Scanner keyboard;

    public static void main(String[] args) {
        keyboard = new Scanner(System.in);
        LibrarySystem librarySystemList = new LibrarySystem();
        Book book;

        System.out.println(" HELLO YOU CAN ENTER ANY NUMBER FROM 1 TO 12 TO DO THIS SERVICE AND -1 TO EXIT .");
        System.out.println(" Enter -1 to to exit .");
        System.out.println(" Enter 1 to Add book.");
        System.out.println(" Enter 2 to Delete book.");
        System.out.println(" Enter 3 to Add member.");
        System.out.println(" Enter 4 to Delete member");
        System.out.println(" Enter 5 to search about book.");
        System.out.println(" Enter 6 to search about member.");
        System.out.println(" Enter 7 to check the size of books List.");
        System.out.println(" Enter 8 to check the size of members List.");
        System.out.println(" Enter 9 to Issue book.");
        System.out.println(" Enter 10 to return book.");
        System.out.println(" Enter 11 to print the book issued by member.");
        System.out.println(" Enter 12 to check if the book is issued.");

        int number;
        do {

            System.out.println("****************************");
            System.out.println(" Enter number from 1 to 12 only to help you!");


            number = keyboard.nextInt();
            switch (number) {
                case -1:
                    System.exit(-1);
                    break;

                // when the user enter -1 this help the user to exit


                case 1:
                    System.out.println("Enter the title of the book: ");
                    keyboard.nextLine();
                    String titleB = keyboard.nextLine();

                    System.out.println("Enter the Author 1 : ");
                    String author1B = keyboard.nextLine();

                    System.out.println("Enter the Author 2 : ");
                    String author2B = keyboard.nextLine();

                    System.out.println("Enter the publisher : ");
                    String publisherB = keyboard.nextLine();

                    System.out.println("Enter the year of Publication: ");
                    int yearPublicationB = keyboard.nextInt();

                    System.out.println("Enter the ISBN : ");
                    String isbnB = keyboard.next();

                    System.out.println("Enter the accession Number: ");
                    long accessionNumB = keyboard.nextLong();

                     book = new Book(titleB,author1B,author2B,publisherB,yearPublicationB,isbnB,accessionNumB);
                    if (librarySystemList.addBook(book)) {
                        System.out.println("************************");
                        System.out.println("DONE THE BOOK WAS ADDED");
                        break;
                    } else {
                        System.out.println("************************");
                        System.out.println("SORRY THE BOOK WAS NOT ADDED");
                        break;

                    }



                case 2:
                    System.out.print("Enter the accession number: ");
                    long accessionNumber = keyboard.nextLong();
                    if (librarySystemList.deleteBook(accessionNumber)) {
                        System.out.println("************************");
                        System.out.println("The book deleted ");
                        break;
                    } else {
                        System.out.println("************************");
                        System.out.println("The book can not  deleted");
                        break;
                    }


                case 3:
                    System.out.print("Enter the First name: ");
                    String FirstName = keyboard.next();
                    System.out.print("Enter the last name: ");
                    String LastName = keyboard.next();
                    System.out.print("Enter the gender For Male (M) and For Female (F): ");
                    char Gender = keyboard.next().charAt(0);
                    System.out.print("Enter CPR number: ");
                    long CPR = keyboard.nextLong();
                    System.out.print("Enter the telephone number: ");
                    String TelephoneNum = keyboard.next();
                    LibMember libMember = new LibMember(FirstName, LastName, Gender, CPR, TelephoneNum);
                    if (librarySystemList.addMember(libMember)) {
                        System.out.println("************************");
                        System.out.println("The member was added ");
                        break;
                    } else {
                        System.out.println("************************");
                        System.out.println("The member can not added");
                        break;
                    }


                case 4:
                    System.out.print("Enter the CPR number: ");
                    long CPRn = keyboard.nextLong();
                    if (librarySystemList.deleteMember(CPRn)) {
                        System.out.println("************************");
                        System.out.println("The member was deleted ");
                        break;
                    } else {
                        System.out.println("************************");
                        System.out.println("The member can not deleted");
                        break;
                    }


                case 5:
                    System.out.print("Enter the accession number: ");
                    long AccessionNumm = keyboard.nextLong();
                    if (librarySystemList.searchBook(AccessionNumm) != -1) {
                        System.out.println("************************");
                        System.out.println("The book exists");
                        break;
                    } else {
                        System.out.println("************************");
                        System.out.println("The book doesn't exist");
                        break;
                    }


                case 6:
                    System.out.print("Enter the CPR: ");
                    long CPRnum1 = keyboard.nextLong();
                    if (librarySystemList.searchMember(CPRnum1) != -1) {
                        System.out.println("************************");
                        System.out.println("The member exists");
                        break;
                    } else {
                        System.out.println("************************");
                        System.out.println("The member doesn't exist");
                        break;
                    }


                case 7:
                    System.out.println("************************");
                    System.out.println("The size of books list is : " + librarySystemList.sizeBooksList());
                    break;


                case 8:
                    System.out.println("************************");
                    System.out.println("The size members  list is : " + librarySystemList.sizeMembersList());
                    break;


                case 9:
                    System.out.print("Enter the  accession number: ");
                    long AccessionNum1 = keyboard.nextLong();
                    System.out.print("Enter the CPR number: ");
                    long CPRnum = keyboard.nextLong();
                    if (librarySystemList.issueBook(AccessionNum1, CPRnum)) {
                        System.out.println("************************");
                        System.out.println("The book issued to member successfully");
                        break;
                    } else {
                        System.out.println("************************");
                        System.out.println("The book can not issued to the member");
                        break;
                    }


                case 10:
                    System.out.print("Enter the accession number: ");
                    long AccessionNum2 = keyboard.nextLong();
                    if (librarySystemList.returnBook(AccessionNum2)) {
                        System.out.println("************************");
                        System.out.println("The book  was return ");
                        break;
                    } else {
                        System.out.println("************************");
                        System.out.println("The book can not be return");
                        break;
                    }


                case 11:
                    System.out.print("Enter The CPR Number: ");
                    long cprNum = keyboard.nextLong();
                    librarySystemList.printBooksIssued(cprNum);
                    break;


                case 12:
                    System.out.print("Enter the accession number of the book: ");
                    long AccessionNum = keyboard.nextLong();
                    if (librarySystemList.isBookIssued(AccessionNum)) {
                        System.out.println("************************");
                        System.out.println("YES");
                        break;
                    } else if (librarySystemList.searchBook(AccessionNum) != -1) {
                        System.out.println("************************");
                        System.out.println("NO");
                        break;
                    } else {
                        System.out.println("************************");
                        System.out.println("The book doesn't exist");
                        break;
                    }
                default:
                    System.out.println("Invalid");
                    break;
            }
        } while (number != -1);
    }
}










