package GoogleHashCode;

import java.util.*;

public class Simulation {
    int totalDays;
    ArrayList<Library> libraries;
   // Book[] allBooks;

    //constantly changing
    int day = 0;
    boolean shipping = false;


    int libraryCount = 0;
    ArrayList<Library> librariesScanning = new ArrayList<>();
    ArrayList<Book> booksOrder = new ArrayList<>();
    public Simulation(ArrayList<Library> libraries, int availableDays) {
        this.libraries = libraries;
        //this.allBooks = books;
        this.totalDays = availableDays;
    }


    public void start(){
        Library thatsShipping = null;
        for (int days = 0; days < totalDays; days++) {
            //scan all current books
            
            if(!shipping) { //find the library with the greatest points and start it!
                updatePossiblePoints();
                thatsShipping = pickBestLibrary();
                populateLibraryPriorityQueue(thatsShipping);
                thatsShipping.setSignUpTime(thatsShipping.getSignUpTime() - 1);
                shipping = true;
            }
            else // it is currently shipping, so pop the books, make them visited, and
            {
                if(thatsShipping.getSignUpTime() == 0) {
                    librariesScanning.add(thatsShipping);
                    shipping = false;
                }
                else {
                    thatsShipping.setSignUpTime(thatsShipping.getSignUpTime() - 1);
                }
            }
            for (Library l : librariesScanning) {
                for (int i = 0; i < l.getScannedBooks() && !l.pq.isEmpty(); i++) {
                    Book temp = l.pq.poll();
                    booksOrder.add(temp);
                    l.submission.add(temp);
                }
            }
        }
        long total = 0;
        System.out.println(librariesScanning.size());
        for (int i = 0; i < librariesScanning.size(); i++) {
            System.out.print(librariesScanning.get(i).id + " " + librariesScanning.get(i).submission.size());
            System.out.println("");
            for (int j = 0; j < librariesScanning.get(i).submission.size(); j++) {
                System.out.print(librariesScanning.get(i).submission.get(j).getId() + " ");
                total += librariesScanning.get(i).submission.get(j).getScore();
        }
            System.out.println("");
        }
        System.out.println(total);
           
    }

    /**
     * This method updates the MAXIMUM amount of points that a library can provide
     * During a given day
     * Precondition: the day is possible, the books may or may not have been scanned
     */
    public void updatePossiblePoints(){
        //go through all the libraries
        for (int library = 0; library < libraries.size(); library++) {
            int score = 0;
            if(!libraries.get(library).isVisited()){
                Book[] bookArray = libraries.get(library).getBookList();
                for (int book = 0; book < bookArray.length && book < (totalDays - (day + libraries.get(library).getSignUpTime()) + 1); book++) {
                    if(!bookArray[book].isVisited()){
                        score += bookArray[book].getScore();
                        //bookArray[book].setVisited(true);
                    }
                }
                libraries.get(library).setPossiblePoints(score);
            }
        }
    }

    /**
     * To be called AFTER update updatePossiblePoints
     * @return
     */
    public Library pickBestLibrary(){
        int max = libraries.get(0).getPossiblePoints();
        int library = 0;
        for(int i = 1; i < libraries.size(); i++){
            if(max < libraries.get(i).getPossiblePoints()){
                library = i;
                max = libraries.get(i).getPossiblePoints();
            }
        }
        libraries.get(library).setVisited(true);
        Library l = libraries.get(library);
        libraries.remove(library);
        return l;
    }

    public void populateLibraryPriorityQueue(Library lib){
        Book[] bookArray = lib.getBookList();
        for (int i = 0;  i < bookArray.length && i < (totalDays - (day + lib.getSignUpTime()) + 1); i++) {
            if (!bookArray[i].isVisited()){
                lib.pq.add(bookArray[i]);
                bookArray[i].setVisited(true);
            }


        }
    }
}