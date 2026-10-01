
package store.core;

import store.cart.Cart;
import store.cart.CartItem;
import store.engine.StoreEngine;
import store.orders.Order;
import store.products.Product;



import java.util.ArrayList;
import java.util.List;

/** * Customer class representing a user who can shop in the store.
 */
public class Customer extends User{
    private Cart cart;
    private List<Order> orderHistory;
    private StoreEngine engine;



    /**     * Constructs a Customer with the specified username and email.
     * @param username The username of the customer.
     * @param email The email of the customer.
     */
    public Customer(String username, String email){
        super(username, email);
        this.cart = new Cart();
        this.orderHistory = new ArrayList<>();
        this.engine = StoreEngine.getInstance();

    }

    /**     * Adds a product to the customer's cart with the specified quantity.
     * @param p The product to add.
     * @param quantity The quantity of the product to add.
     * @return true if the product was added successfully, false otherwise.
     */
    public boolean addToCart(Product p, int quantity){
        if (p == null || quantity <= 0) {
            return false;
        }

        if (p.getStock() < quantity) {
            return false;
        }
        return cart.addItem(p, quantity);

    }
    /**     * Returns a string representation of the customer and their cart.
     * @return String representation of the customer.
     */
    @Override
    public String toString(){
        return "Customer Username: " + getUsername() + "\n" + cart.toString();
    }

    /**     * Removes a product from the customer's cart.
     * @param p The product to remove.
     * @return true if the product was removed successfully, false otherwise.
     */
    public boolean removeFromCart(Product p){
        if (p == null)
            return false;
        return cart.removeItem(p);
    }

    /**     * Checks out the customer's cart, creating an order and clearing the cart.
     * @return true if the checkout was successful, false otherwise.
     */
    public boolean checkout(){
        if (cart.getItems().isEmpty())
            return false;
        Order newOrder = engine.createOrderFromCart(cart);
        if (newOrder != null) {
            for (CartItem item : cart.getItems()){
                Product product = item.getProduct();
                int Quantity = item.getQuantity();
                product.decreaseStock(Quantity);
            }
            orderHistory.add(newOrder);
            cart.clearCart();
            return true;
        }
        return false;
    }

    /**     * Gets the customer's order history.
     * @return List of orders in the customer's order history.
     */
    public List<Order> getOrderHistory() {
        return new ArrayList<>(orderHistory);
    }

    /**     * Gets the customer's cart.
     * @return The customer's cart.
     */
    public Cart getCart() {
        return cart;
    }
}
