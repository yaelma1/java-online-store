

package store.cart;

import java.util.*;
import store.products.Product;

/**
 * Represents a shopping cart containing multiple cart items.
 */
public class Cart {
    private List<CartItem> items;

    /**
     * Constructs an empty Cart.
     */
    public Cart(){
        this.items = new ArrayList<>();
    }

    /**
     * Returns a string representation of the cart and its items.
     * @return String representation of the cart.
     */
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("Cart Items:\n");
        for (CartItem item : items)
            str.append(item.toString()).append("\n");
        return str.toString();
    }

    /**
     * Compares this cart with another object for equality based on cart items.
     * @param o Object to compare with.
     * @return true if both carts have the same items, false otherwise.
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Cart))
            return false;
        Cart cart = (Cart) o;
        return this == o;//by reference
    }

    /**
     * Adds an item to the cart with the specified quantity.
     * If the item already exists, it updates the quantity.
     * @param p Product to add.
     * @param quantity Quantity of the product to add.
     * @return true if the item was added successfully, false if stock is insufficient.
     */
    public boolean addItem(Product p, int quantity){
        for (CartItem item : items)
            if (item.getProduct().equals(p)) {
                if (item.getProduct().getStock() < item.getQuantity() + quantity)
                    return false;
                item.setQuantity(item.getQuantity() + quantity);
                return true;
            }
        if (p.getStock() < quantity)
            return false;
        items.add(new CartItem(p, quantity));
        return true;
    }

    /**
     * Removes an item from the cart.
     * @param p Product to remove.
     * @return true if the item was removed successfully, false if the item was not found.
     */
    public boolean removeItem(Product p){
        if (p == null)
            return false;
        for (CartItem item : items) {
            if (item.getProduct().equals(p)) {
                items.remove(item);
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the total price of all items in the cart.
     * @return Total price of the cart.
     */
    public double calculateTotal(){
        double total = 0;
        for (CartItem item : items)
            total += item.getTotalPrice();
        return total;
    }

    /**
     * Clears all items from the cart.
     */
    public void clearCart(){
        items.clear();
    }

    /**
     * Gets the list of items in the cart.
     * @return List of cart items.
     */
    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Checks if the cart contains a specific product.
     * @param product Product to check.
     * @return true if the product is in the cart, false otherwise.
     */
    public boolean isContainsProduct(Product product){
        for (CartItem item : items)
            if (item.getProduct().equals(product))
                return true;
        return false;
    }
}
