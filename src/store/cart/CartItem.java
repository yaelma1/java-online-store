
package store.cart;

import store.products.Product;

/**
 * Represents an item in the shopping cart, consisting of a product and its quantity.
 */
public class CartItem {
    private Product product;
    private int quantity;

    /**
     * Constructs a CartItem with the specified product and quantity.
     * @param product The product to be added to the cart.
     * @param quantity The quantity of the product.
     * @throws IllegalArgumentException if the product is null or quantity is invalid.
     */
    public CartItem(Product product, int quantity) {
        if (product == null)
            throw new IllegalArgumentException("Product cannot be null");
        if (quantity <= 0 || quantity > product.getStock())
            throw new IllegalArgumentException("Invalid quantity");
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Sets the quantity of the product in the cart item.
     * @param q The new quantity to set.
     * @return true if the quantity was set successfully, false if the quantity is invalid.
     */
    public boolean setQuantity(int q){
        if (q <= 0 || q > product.getStock())
            return false;
        this.quantity = q;
        return true;
    }

    /**
     * Gets the quantity of the product in the cart item.
     * @return The quantity of the product.
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * Calculates the total price for this cart item.
     * @return The total price (product price multiplied by quantity).
     */
    public double getTotalPrice(){
        return product.getPrice() * quantity;
    }

    /**
     * Returns a string representation of the cart item.
     * @return String representation of the cart item.
     */
    @Override
    public String toString(){
        return product.toString() + "\nQuantity: " + quantity + "\n";
    }

    /**
     * Compares this cart item with another object for equality based on the product.
     * @param o Object to compare with.
     * @return true if both cart items have the same product, false otherwise.
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof CartItem))
            return false;
        CartItem item = (CartItem) o;
        return this.product.equals(item.product);
    }

    /**
     * Gets the product associated with this cart item.
     * @return The product in the cart item.
     */
    public Product getProduct(){
        return product;
    }

    public String toCSV(){
        return quantity + "," + product.toCSV();
    }

}
