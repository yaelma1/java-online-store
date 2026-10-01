
package store.engine;

import store.cart.CartItem;
import store.core.Customer;
import store.products.Category;
import store.products.Product;
import store.orders.Order;
import store.cart.Cart;
import store.products.ProductCreator;
import store.products.ProductFactory;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Scanner;

/** * StoreEngine class implementing the singleton pattern to manage store operations.
 */
public class StoreEngine {

    static private volatile StoreEngine instance = null;

    private List<Product> products;
    private List<Order> allOrders;
    private List<Customer> customers;
    private static int nextOrderId;


    /**     * Private constructor to prevent instantiation from outside the class.
      */
    private StoreEngine(){
        products = new ArrayList<>();
        allOrders = new ArrayList<>();
        customers = new ArrayList<>();
        nextOrderId = 0;
    }

    /**     * Returns the singleton instance of StoreEngine.
      * @return The singleton instance of StoreEngine.
      */
    public static StoreEngine getInstance() {
        if (instance == null)
            synchronized (StoreEngine.class) {
                if (instance == null)
                    instance = new StoreEngine();
            }
        return instance;
    }

    /**     * Returns a string representation of the StoreEngine and its products.
      * @return String representation of the StoreEngine.
      */
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("Store Products: ");
        for (Product p : products)
            str.append(p.toString()).append("\n");
        return "StoreEngine{all products: {" + str.toString() + "}\n}";
    }

    /**     * Adds a product to the store's product list.
      * @param p The product to add.
      */
    public synchronized void addProduct(Product p){
        products.add(p);
    }

    /**     * Retrieves a list of all available products in stock.
      * @return List of available products.
      */
    public List<Product> getAvailableProducts(){
        List<Product> availableProducts = new ArrayList<>();
        for (Product p : products)
            if (p.getStock() > 0)
                availableProducts.add(p);

        return availableProducts;
    }

    /**     * Registers a new customer in the store.
      * @param c The customer to register.
      * @return true if registration is successful, false if username already exists.
      */
    public boolean registerCustomer(Customer c){
        for (Customer customer : customers)
            if (customer.getUsername().equals(c.getUsername()))
                return false;
        customers.add(c);
        return true;
    }

    /**     * Creates an order from the given cart.
      * @param cart The cart to create an order from.
      * @return The created Order object.
      */
    public Order createOrderFromCart(Cart cart){
        double totalAmount = cart.calculateTotal();
        synchronized (StoreEngine.class) {
            nextOrderId += 1;
        }
        Order newOrder = new Order(nextOrderId, new ArrayList<>(cart.getItems()), totalAmount);
        allOrders.add(newOrder);
        return newOrder;

    }

    /**     * Retrieves a list of all orders in the store.
      * @return List of all orders.
      */
    public List<Order> getAllOrders() {
        return allOrders;
    }

    /**     * Retrieves a list of all products in the store.
      * @return List of all products.
      */
    public List<Product> getProducts() {
        return products;
    }



    /**     * Sets the currently registered customer.
      *  The customer to set as registered.
      * @return true if the customer is set successfully, false if the customer is null.

    public boolean setRegisteredCustomer(Customer customer){
        if (customer == null)
            return false;
        this.registeredCustomer = customer;
        return true;
    }
    /*

    /**     * Saves the current list of products to a file in CSV format.
      * @param file The file to save the products to.
      */
    public void saveProductsToFile(File file){
        try (PrintWriter pw = new PrintWriter(file)){
            for (Product p : products)
                pw.println(p.toCSV());
        }
        catch (Exception e){
            System.out.println("error in saving product");
        }
    }

    /**     * Loads products from a file in CSV format and adds them to the store's product list.
      * @param file The file to load products from.
      */
    public void loadProductsFromFile(File file){
        List<Product> products_temp = new ArrayList<>();

        try (Scanner sc = new Scanner(file)){
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] args = line.split(",");
                Product p = ProductFactory.create(args);
                products_temp.add(p);
            }

        } catch (IOException e){
            System.out.println("error loading a product");
        }
        synchronized (products) {
            products.clear();
            products.addAll(products_temp);
        }
    }

    /**     * Adds a product to the registered customer's cart.
      * @param product The product to add to the cart.
      * @return true if the product is added successfully, false otherwise.
      */
    public boolean addToCart(Product product, Customer registeredCustomer){
        if (registeredCustomer == null)
            return false;
        synchronized (this){
            return registeredCustomer.addToCart(product, 1);
        }
    }

    /**     * Retrieves a list of products filtered by the specified category.
      * @param category The category to filter products by.
      * @return List of filtered products.
      */
    public List<Product> getFilteredProducts(Category category){
        if (category == null)
            return products;
        List<Product> filteredProducts = new ArrayList<>();
        for (Product p : products)
            if (p.getCategory() == category)
                filteredProducts.add(p);
        return filteredProducts;
    }

    /**     * Removes a product from the registered customer's cart.
      * @param product The product to remove from the cart.
      * @return true if the product is removed successfully, false otherwise.
      */
    public boolean removeFromCart(Product product, Customer registeredCustomer){
        if (registeredCustomer == null)
            return false;
        synchronized (this){
            return registeredCustomer.removeFromCart(product);

        }
    }

    /**     * Performs checkout for the registered customer.
      * @return true if checkout is successful, false otherwise.
      */
    public boolean checkout(Customer registeredCustomer){
        if (registeredCustomer == null)
            return false;
        synchronized (this){
            return registeredCustomer.checkout();
        }
    }

    /**     * Retrieves the items in the registered customer's cart.
      * @return List of cart items.
      */
    public List<CartItem> getRegisteredCustomerCartItems(Customer registeredCustomer){
        if (registeredCustomer == null)
            return Collections.emptyList();
        return registeredCustomer.getCart().getItems();
    }

    /**     * Calculates the total amount of the registered customer's cart.
      * @return Total amount of the cart.
      */
    public double getRegisteredCustomerCartTotal(Customer registeredCustomer){
        if (registeredCustomer == null)
            return 0.0;
        return registeredCustomer.getCart().calculateTotal();
    }

    /**     * Retrieves the order history of the registered customer.
      * @return List of orders in the customer's order history.
      */
    public List<Order> getRegisteredCustomerOrderHistoryStrings(Customer registeredCustomer){
        if (registeredCustomer == null)
            return Collections.emptyList();
        List<Order> orders = registeredCustomer.getOrderHistory();
        return orders;
    }

    /**     * Checks if a product is in the registered customer's cart.
      * @param product The product to check.
      * @return true if the product is in the cart, false otherwise.
      */
    public boolean isProductInCart(Product product, Customer registeredCustomer){
        if (registeredCustomer == null)
            return false;
        return registeredCustomer.getCart().isContainsProduct(product);
    }

    /**     * Searches for products by name or description containing the given search text.
      * @param searchText The text to search for.
      * @return List of products matching the search criteria.
      */
    public List<Product> searchProducts(String searchText){
        List<Product> result = new ArrayList<>();
        String lowerSearchText = searchText.toLowerCase();
        for (Product p : products){
            if (p.getName().toLowerCase().contains(lowerSearchText) ||
                p.getDescription().toLowerCase().contains(lowerSearchText)){
                result.add(p);
            }
        }
        return result;
    }



}
