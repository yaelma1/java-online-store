
package store.products;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BookProduct class represents a book item in the store's product catalog.
 * It extends the Product class and adds specific attributes for books such as author and number of pages.
 */
public class BookProduct extends Product{
     private String author;
     private int pages;


     public BookProduct(){
            super();
            this.author = "Default Author";
            this.pages = 1;
     }

     /** Constructor for BookProduct class.
      * @param name Name of the book.
      * @param price Price of the book.
      * @param stock Stock quantity of the book.
      * @param description Description of the book.
      * @param category Category of the book.
      * @param color Color of the book.
      * @param author Author of the book.
      * @param pages Number of pages in the book.
      */
     public BookProduct(String name, double price, int stock, String description, Category category, Color color, String imagePath, String author, int pages){
         super(name, price, stock, description, category, color, imagePath);
         this.author = author;
         if (pages < 1){
             throw new IllegalArgumentException("pages must be at least 1");
         }
         this.pages = pages;

     }



     /** toString method to represent the BookProduct object as a string.
      * @return String representation of the BookProduct object.
      */
     public String toString(){
         return super.toString() + "\nauthor: " + author + "\npages: " + pages;
     }


     /** Save the BookProduct details to a file.
      * @param path Path to the file where the details should be saved.
      */
    @Override
    public void saveToFile(String path){
        // Implementation for saving product details to a file.
    }

    /** Get the type of the product.
     * @return String representing the type of the product.
     */
    public String myType(){
        return "BookProduct";
    }

    /** Convert the BookProduct details to CSV format.
     * @return String in CSV format representing the BookProduct details.
     */
    @Override
    public String toCSV() {
        return super.toCSV() + "," + author + "," + pages;
    }


    public boolean setAuthor(String author) {
        this.author = author;
        return true;
    }
    public boolean setPages(int pages) {
        if (pages < 1) {
            return false;
        }
        this.pages = pages;
        return true;
    }
}
