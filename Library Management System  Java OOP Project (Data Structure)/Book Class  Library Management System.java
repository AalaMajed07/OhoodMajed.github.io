class Book {
    private String title;
    private String author1;
    private String author2;
    private String publisher;
    private int yearPublication;
    private String isbn;
    private long accessionNum;
    private LibMember issuedTo;


    //Default constructor
    public Book() {
        this.title = "title";
        this.author1 = "author1";
        this.author2 = "author2";
        this.publisher = "publisher";
        this.yearPublication = 0;
        this.isbn = "isbn";
        this.accessionNum = 1001;
        this.issuedTo = null;
    }

     //Constructor with parameters
    public Book(String title, String author1, String author2,
                String publisher, int yearPublication, String isbn, long accessionNum) {
        this.title = title;
        this.author1 = author1;
        this.author2 = author2;
        this.publisher = publisher;
        this.yearPublication = yearPublication;
        this.isbn=isbn;
        this.accessionNum=accessionNum;
        this.issuedTo = null;
    }
     //set and get methods
    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return title;
    }
    public void setAuthor1(String author1){
        this.author1=author1;
    }
    public String getAuthor1(){
        return author1;
    }
    public void setAuthor2(String author2){
        this.author2=author2;
    }
    public String getAuthor2() {
        return author2;
    }
    public void setPublisher(String publisher){
        this.publisher=publisher;
    }
    public String getPublisher(){
        return publisher;
    }
    public void setYearPublication(int yearPublication){
        this.yearPublication=yearPublication;
    }
    public int getYearPublication() {
        return yearPublication;
    }
    public void setIsbn(String isbn) {
        if (isbn.length() == 13)
            this.isbn = isbn;
        else {
            System.out.println("WRONG ISBN");
            return;}
    }
    public String getIsbn(){
        return isbn;
    }

    public void setAccessionNum(long accessionNum){
        if(accessionNum>1000){
            this.accessionNum=accessionNum;
        }else{
            System.out.println("WRONG ACCESSION");
        }
    }
    public long getAccessionNum(){
        return accessionNum;
    }
    public void setIssuedTo(LibMember iTo){
        issuedTo = iTo;
    }

    public LibMember getIssuedTo() {
        return issuedTo;
    }



    //equals method
    public boolean equals(Book other){
        if (this == other) return true;
        return yearPublication== other.yearPublication &&
                accessionNum== other.accessionNum && title.equals(other.title )
                && author1.equals(other.author1)&& author2.equals(other.author2)
                && publisher.equals(other.publisher)
                &&isbn.equals(other.isbn);}


    //To String method
    public String toString() {
        return ("Book { " +
                "title= '" + title + '\'' +
                ", author1= '" + author1 + '\'' +
                ", author2= '" + author2 + '\'' +
                ", publisher= '" + publisher + '\'' +
                ", yearPublication= " + yearPublication +
                ", isbn= '" + isbn + '\'' +
                ", accessionNum= " + accessionNum +
                ", issuedTo= " + issuedTo.getFirstName() + " " + issuedTo.getLastName() +
                '}');

    }}