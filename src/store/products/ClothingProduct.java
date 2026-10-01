
package store.products;

import java.awt.*;
import java.util.List;

/** ClothingProduct class representing a clothing product in the store.
 * Inherits from Product class.
 */
public class ClothingProduct extends Product {
    private int size;


    public ClothingProduct(){
        super();
        this.size = 0;
    }

    /**
     * Constructor for ClothingProduct class.
     *
     * @param name        Name of the clothing product.
     * @param price       Price of the clothing product.
     * @param stock       Stock quantity of the clothing product.
     * @param description Description of the clothing product.
     * @param category    Category of the clothing product.
     * @param color       Color of the clothing product.
     * @param size        Size of the clothing product.
     */
    public ClothingProduct(String name, double price, int stock, String description, Category category, Color color, String imagePath, int size) {
        super(name, price, stock, description, category, color, imagePath);
        if (size < 0) {//size can be 0 for items like one-size-fits-all
            throw new IllegalArgumentException("size must be non-negative");
        }
        this.size = size;

    }

    /**
     * toString method to represent the ClothingProduct object as a string.
     *
     * @return String representation of the ClothingProduct object.
     */
    @Override
    public String toString() {
        return super.toString() + "\nsize: " + size + "}";
    }


    /**
     * Save the ClothingProduct details to a file.
     *
     * @param path Path to the file where the details should be saved.
     */
    @Override
    public void saveToFile(String path) {
        // Implementation for saving product details to a file.
    }

    /**
     * Get the type of the product.
     *
     * @return String representing the type of the product.
     */
    public String myType() {
        return "ClothingProduct";
    }

    /**
     * Convert the ClothingProduct details to CSV format.
     *
     * @return String representation of the ClothingProduct in CSV format.
     */
    @Override
    public String toCSV() {
        return super.toCSV() + "," + size;
    }

    public boolean setSize(int size) {
        if (size < 0) {
            return false;
        }
        this.size = size;
        return true;
    }
}

