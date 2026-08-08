import java.util.*;
public class LibMember{

    // the data members
    private String firstName;
    private String lastName;
    private char gender;
    private long cprNum;
    private String teleNum;
    private Book[] booksIssued;
    private int numBooksIssued;


    // default constructor
    public LibMember() {
        firstName = "" ;
        lastName = "" ;
        gender = ' ' ;
        cprNum = 0 ;
        teleNum = "" ;
        booksIssued = new Book [10];
        numBooksIssued = 0;
    }


    // constructor with parameter for booksIssued and numBooksIssued
    public LibMember(String firstName, String lastName
            ,char gender,long cprNum, String teleNum) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.cprNum = cprNum;
        this.teleNum = teleNum;
        booksIssued = new Book[10];
        numBooksIssued = 0;}
    //set and get methods
    public void setFirstName(String firstName) {
        this.firstName = firstName;}

    public String getFirstName(){
            return firstName;}

    public void setLastName(String lastName) {
        this.lastName = lastName;}

    public String getLastName() {
        return lastName;}

    public void setGender(char gender) {
        if (gender == 'f' || gender == 'F' || gender == 'M' || gender == 'm')
            this.gender = gender;
        else {
            System.out.println("Unknown!");
            return;}
    }
    public char getGender() {
        return gender;
    }
    public void setCprNum(long cprNum){
        this.cprNum=cprNum;
    }

    public long getCprNum() {
        return cprNum;
    }

    public void setTeleNum(String teleNum){
        this.teleNum=teleNum;
    }
    public String getTeleNum(){
        return teleNum;
    }
    public void setBooksIssued(Book[] booksIssued){
        this.booksIssued=booksIssued;
    }
    public Book[] getBooksIssued() {
        return booksIssued;
    }
    public void setNumBooksIssued(int numBooksIssued) {
        this.numBooksIssued = numBooksIssued;}
    public int getNumBooksIssued() {
        return numBooksIssued;
    }



    //equals method
    public boolean equals(LibMember other){
        if (this==other) return true;
        return gender==other.gender&& cprNum==other.cprNum
                &&numBooksIssued==other.numBooksIssued
                &&firstName.equals(other.firstName)
                &&lastName.equals(other.lastName)&& teleNum.equals(other.teleNum)
                && booksIssued.equals(other.booksIssued);
    }

    //to string method
    public String toString() {
        return "LibMember { " + " firstName: " + firstName
                + '\t' + " lastName: " + lastName +'\t' +
                "gender: " + gender + ", cprNum: " + cprNum
                + ", teleNum: " + teleNum + '\n' +
                " booksIssued: " + Arrays.toString(booksIssued)
                + '\n' + " numBooksIssued: " + numBooksIssued +'\t'+ '}';}

    }