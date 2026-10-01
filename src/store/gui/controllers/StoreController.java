
package store.gui.controllers;

import store.cart.CartItem;
import store.engine.StoreEngine;
import store.gui.view.StoreWindow;
import store.products.Category;
import store.products.Product;
import store.orders.Order;
import store.core.Customer;
import store.products.ProductFactory;

import java.io.File;
import java.util.List;

/** * StoreController class to manage interactions between the StoreEngine and StoreWindow view.
 */
public class StoreController {

    private final StoreEngine engine;
    private StoreWindow view;
    private final Customer customer;


    /**     * Constructs a StoreController with the specified StoreEngine.
      * @param engine The StoreEngine to use for store operations.
      */
    public StoreController(StoreEngine engine, Customer customer) {
        this.engine = engine;
        this.customer = customer;
    }

    /**     * Sets the view for the controller.
      * @param view The StoreWindow view to set.
      */
    public void setView(StoreWindow view) {
        this.view = view;
    }

    /**     * Loads products from a file and refreshes the product list in the view.
      */
    public void loadProduct(){
        File file = view.openFileChooser();
        if (file != null){
            engine.loadProductsFromFile(file);
            view.refreshProductList(engine.getProducts());
        }
    }

    /**     * Displays the details of the specified product in the view.
      * @param product The product whose details to display.
      */
    public void showProductDetails(Product product){
        view.updateDetailsPanel(product);
    }

    /**     * Adds the specified product to the cart.
      * @param product The product to add to the cart.
      * @return true if the product was added successfully, false otherwise.
      */
    public boolean addToCart(Product product){
        return engine.addToCart(product, customer);
    }

    /**     * Saves the current list of products to a file.
      * @return true if the products were saved successfully, false otherwise.
      */
    public boolean saveProduct(){
        File file = view.openFileChooser();
        if (file != null){
            synchronized (this) {
                engine.saveProductsToFile(file);
            }
            return true;
    }
        return false;
    }

    /**     * Filters products by the specified category and refreshes the product list in the view.
      * @param category The category to filter products by.
      */
    public void filterByCategory(Category category){
        List<Product> filteredProducts = engine.getFilteredProducts(category);
        view.refreshProductList(filteredProducts);

    }

    /**     * Proceeds to checkout the items in the cart.
      * @return true if the checkout was successful, false otherwise.
      */
    public boolean checkout(){
        if (engine.checkout(customer)){
            saveProduct();
            return true;
        }
        return false;
    }

    /**     * Retrieves the items in the registered customer's cart.
      * @return List of cart items.
      */
    public List<CartItem> getCartItems(){
        return engine.getRegisteredCustomerCartItems(customer);
    }

    /**     * Retrieves the total price of items in the cart.
      * @return Total cart price.
      */
    public double getTotalCartPrice(){
        return engine.getRegisteredCustomerCartTotal(customer);
    }


    /**     * Removes the specified product from the cart.
      * @param product The product to remove from the cart.
      * @return true if the product was removed successfully, false otherwise.
      */
    public boolean removeFromCart(Product product){
        return engine.removeFromCart(product, customer);
    }

    /**     * Retrieves the order history of the registered customer.
      * @return List of orders in the order history.
      */
    public List<Order> getOrderHistory(){
        return engine.getRegisteredCustomerOrderHistoryStrings(customer);
    }

    /**     * Checks if the specified product is in the cart.
      * @param product The product to check.
      * @return true if the product is in the cart, false otherwise.
      */
    public boolean isInCart(Product product){
        return engine.isProductInCart(product, customer);
    }

    /**     * Refreshes the product list in the view after changes in the cart.
      */
    public void refreshAfterChangeInCart(){
        List<Product> Products = engine.getProducts();
        view.refreshProductList(Products);
    }

    /**     * Searches for products matching the specified search text and refreshes the product list in the view.
      * @param searchText The text to search for in product names and descriptions.
      */
    public void searchProducts(String searchText){
        view.refreshProductList(engine.searchProducts(searchText));
    }

    /**     * Adds a new product to the store based on the provided product details.
      * @param wholeProduct An array of strings containing the product details.
      */
    public void addNewProduct(String[] wholeProduct){
        Product product = ProductFactory.create(wholeProduct);
        engine.addProduct(product);
    }

    public List<String> getExtraFieldsByCategory(Category category){
        return category.getExtraFields();
    }

    public String getProductTypeByCategory(Category category){
        return category.getProductType();
    }


}
