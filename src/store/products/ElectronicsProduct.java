
package store.products;

import java.awt.*;
import java.util.List;

/**
 * Represents an electronic product in the store.
 */
public class ElectronicsProduct extends Product{
    private int warrantMonths;
    private String brand;

    public ElectronicsProduct(){
        super();
        this.warrantMonths = 0;
        this.brand = "Default Brand";
    }

    /**
     * Constructor for ElectronicsProduct
     *
     * @param name         the name of the product
     * @param price        the price of the product
     * @param stock        the stock quantity of the product
     * @param description  the description of the product
     * @param category     the category of the product
     * @param color        the color of the product
     * @param warrantMonths the warranty period in months
     * @param brand        the brand of the product
     */
    public ElectronicsProduct(String name, double price, int stock, String description, Category category, Color color, String imagePath, int warrantMonths, String brand) {
        super(name, price, stock, description, category, color, imagePath);
        if (warrantMonths <= 0) {
            throw new IllegalArgumentException("warranty months must be non-negative");
        }
        this.warrantMonths = warrantMonths;
        this.brand = brand;
    }

    /** toString method to represent the ElectronicsProduct object as a string.
     * @return String representation of the ElectronicsProduct object.
     */
    @Override
    public String toString() {
        return super.toString() + "\nwarranty months: " + warrantMonths + "\nbrand: " + brand + "}";
    }

    /** save the ElectronicsProduct details to a file.
     * @param path Path to the file where the details should be saved.
     */
    @Override
    public void saveToFile(String path){
        // Implementation for saving product details to a file.
    }

    /** Get the type of the product.
     * @return String representing the type of the product.
     */
    @Override
    public String myType(){
        return "ElectronicsProduct";
    }

    /** Convert the ElectronicsProduct details to CSV format.
     * @return String in CSV format representing the ElectronicsProduct details.
     */
    @Override
    public String toCSV(){
        return super.toCSV() + "," + warrantMonths + "," + brand;
    }



    public boolean setWarranty(int months) {
        if (months < 0) {
            return false;
        }
        this.warrantMonths = months;
        return true;
    }

    public boolean setBrand(String brand) {
        this.brand = brand;
        return true;
    }

}
