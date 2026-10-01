
package store.orders;

import store.cart.CartItem;
import store.core.Persistable;

import java.util.ArrayList;
import java.util.List;

/** * Represents an order placed by a customer.
 */
public class Order implements Persistable {
    private int orderID;
    private List<CartItem> items;
    private double totalAmount;
    private OrderStatus status;


    /*** Constructs a new Order with the given items and total amount.
     * @param items List of items in the order.
     * @param totalAmount Total amount for the order.
     */
    public Order(int ID, List<CartItem> items, double totalAmount) {
        this.orderID = ID;
        this.items = new ArrayList<>();
        for (CartItem item : items)
            this.items.add(new CartItem(item.getProduct(), item.getQuantity()));//deep copy
        this.totalAmount = totalAmount;
        this.status = OrderStatus.NEW;
    }

    /*** Returns a string representation of the order.
     * @return String representation of the order.
     */
    @Override
    public String toString(){
        return "Order ID: " + orderID + "\nItems: " + items.toString() + "\nTotal Amount: " + totalAmount + "\nStatus: " + status;
    }

    /*** Compares this order with another object for equality based on order ID.
     * @param o Object to compare with.
     * @return true if both orders have the same order ID, false otherwise.
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Order))
            return false;
        Order order = (Order) o;
        return this.orderID == order.orderID;
    }

    /*** Saves the order details to a file at the specified path.
     * @param path Path to the file where the order details should be saved.
     */
    @Override
    public void saveToFile(String path){
        // Implementation to save order details to a file
    }

    /*** Pays for the order, changing its status to PAID.
     * @return true if the payment was successful.
     */
    public boolean pay(){
        if(this.status != OrderStatus.NEW)
            return false;
        this.status = OrderStatus.PAID;
        return true;
    }

    /*** Ships the order if it is in PAID status, changing its status to SHIPPED.
     * @return true if the order was successfully shipped, false otherwise.
     */
    public boolean ship(){
        if(this.status != OrderStatus.PAID)
            return false;
        this.status = OrderStatus.SHIPPED;
        return true;
    }

    /*** Delivers the order if it is in SHIPPED status, changing its status to DELIVERED.
     * @return true if the order was successfully delivered, false otherwise.
     */
    public boolean deliver(){
        if (this.status != OrderStatus.SHIPPED)
            return false;
        this.status = OrderStatus.DELIVERED;
        return true;
    }



    /*** Gets the order ID.
     * @return The order ID.
     */
    public int getOrderID() {
        return orderID;
    }

    /*** Gets the total amount of the order.
     * @return The total amount.
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /*** Gets the list of items in the order.
     * @return List of cart items.
     */
    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        sb.append(orderID).append(",").append(totalAmount).append(status).append("\n");
        for (CartItem item : items)
            sb.append(item.toCSV()).append("\n");
        return sb.toString();
    }









}
