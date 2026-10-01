
package store.products;

import store.core.Persistable;
import store.core.StoreEntity;

import java.awt.*;
import java.util.List;

/**
 * Abstract class representing a product in the store.
 * Implements Persistable, StoreEntity, PricedItem, and StockManageable interfaces.
 */
public abstract class Product implements Persistable, StoreEntity, PricedItem, StockManageable{
    private String name;
    private double price;
    private int stock;
    private String description;
    private Category category;
    private Color color;
    private String imagePath;

    /**
     * Constructor for Product class.
     *
     * @param name        Name of the product.
     * @param price       Price of the product.
     * @param stock       Stock quantity of the product.
     * @param description Description of the product.
     * @param category    Category of the product.
     * @param color       Color of the product.
     * @throws IllegalArgumentException if price is non-positive or stock is negative.
     */
    public Product(String name, double price, int stock, String description, Category category, Color color, String imagePath) {

        this.name = name;
        if (price <= 0) {
            throw new IllegalArgumentException("price must be positive");
        }
        this.price = price;
        if (stock < 0) {
            throw new IllegalArgumentException("stock cannot be negative");
        }
        this.stock = stock;
        this.description = description;
        this.category = category;
        this.color = color;
        this.imagePath = imagePath;
    }

    public Product(){
        this.name = "Default Product";
        this.price = 1.0;
        this.stock = 0;
        this.description = "Default Description";
        this.category = Category.BOOKS;
        this.color = Color.WHITE;
        this.imagePath = "default.png";
    }


    /**
     * toString method to represent the Product object as a string.
     *
     * @return String representation of the Product object.
     */
    @Override
    public String toString() {
        return myType() + "{Name: " + name + "\nPrice: " + price + "\nStock: " + stock + "\nDescription: " + description + "\n  Category: " + category;
    }


    /**
     * Equals method to compare two Product objects based on name and category.
     *
     * @param o Object to compare with.
     * @return true if both products have the same name and category, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product))
            return false;
        Product p = (Product) o;
        return name.equals(p.name) && category == p.category;
    }

    /**
     * Get the display name of the product.
     *
     * @return Name of the product.
     */
    @Override
    public String getDisplayName() {
        return name;
    }

    /**
     * Get the display details of the product.
     *
     * @return String representation of the product details.
     */
    @Override
    public String getDisplayDetails() {
        return toString();
    }

    /**
     *
     * @param path File path to save the product details.
     */
    @Override
    public void saveToFile(String path) {
        // Implementation for saving product details to a file.
    }

    /**
     * Get the current price of the product.
     *
     * @return Price of the product.
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Set a new price for the product.
     *
     * @param price New price to set if it is positive.
     * @return true if the price is set successfully, false otherwise.
     */
    @Override
    public boolean setPrice(double price) {//interface method must be public
        if (price <= 0)
            return false;
        this.price = price;
        return true;
    }

    /**
     * Get the current stock of the product.
     *
     * @return Stock quantity of the product.
     */
    @Override
    public int getStock() {
        return stock;
    }

    /**
     * Increase the stock of the product by a specified amount.
     *
     * @param amount Amount to increase the stock.
     * @return true if the stock is increased successfully, false otherwise.
     */
    @Override
    public boolean increaseStock(int amount) {
        if (amount <= 0)
            return false;
        this.stock += amount;
        return true;
    }

    /**
     * Decrease the stock of the product by a specific amount.
     *
     * @param amount Amount to decrease the stock.
     * @return true if the stock is decreased successfully, false otherwise.
     */
    @Override
    public boolean decreaseStock(int amount) {
        if (amount <= 0 || this.stock - amount < 0)
            return false;
        this.stock -= amount;
        return true;
    }

    /**
     * Abstract method.
     * @return String that represent the type of the product.
     */
    protected abstract String myType();

    /**
     * Converts the product details to a CSV format string.
     *
     * @return CSV format string of the product details.
     */
    public String toCSV(){
        return myType()+ "," + name + "," + price + "," + stock + "," + description + "," + category + "," + color.getRGB() + "," + imagePath;
    }


    /**
     * Get the image path of the product.
     * @return
     */
    public String getImagePath(){
        return imagePath;
    }


    /**
     * Get the name of the product.
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * Get the description of the product.
     * @return
     */
    public String getDescription(){
        return description;
    }

    /**
     * Get the category of the product.
     * @return
     */
    public Category getCategory(){
        return category;
    }

    public boolean setStock(int stock) {
        if (stock < 0) {
            return false;
        }
        this.stock = stock;
        return true;
    }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    public boolean setDescription(String description) {
        this.description = description;
        return true;
    }

    public boolean setColor(Color color) {
        this.color = color;
        return true;
    }

    public boolean setImageUrl(String imagePath) {
        this.imagePath = imagePath;
        return true;
    }

    public boolean setCategory(Category category) {
        this.category = category;
        return true;
    }





}