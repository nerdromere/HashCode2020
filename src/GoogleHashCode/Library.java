package GoogleHashCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Library {
    private Book[] bookList;
    public int id;
    public int signUpTime;
    private int scannedBooks;
    private int possiblePoints;
    private boolean visited;
    private int booksSize;
    public ArrayList<Book> submission = new ArrayList<>();
    public PriorityQueue<Book> pq = new PriorityQueue<>();

    public Library(int id, Book[] bookList, int signUpTime, int scannedBooks) {
        this.id = id;
        Arrays.sort(bookList);
        this.bookList = bookList;
        this.signUpTime = signUpTime;
        this.scannedBooks = scannedBooks;
        possiblePoints = 0;
        visited = false;
    }

    public Book[] getBookList() {
        return bookList;
    }

    public void setBookList(Book[] bookList) {
        this.bookList = bookList;
    }

    public int getSignUpTime() {
        return signUpTime;
    }

    public void setSignUpTime(int signUpTime) {
        this.signUpTime = signUpTime;
    }

    public int getScannedBooks() {
        return scannedBooks;
    }

    public void setScannedBooks(int scannedBooks) {
        this.scannedBooks = scannedBooks;
    }

    public int getPossiblePoints() {
        return possiblePoints;
    }

    public void setPossiblePoints(int possiblePoints) {
        this.possiblePoints = possiblePoints;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

//    public boolean addBook(Book book) {
//        return bookList.add(book);
//    }

}
