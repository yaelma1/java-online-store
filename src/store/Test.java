package store;
import store.core.Customer;
import store.core.Persistable;
import store.core.StoreEntity;
import store.engine.StoreEngine;
import store.orders.OrderStatus;
import store.products.Category;
import store.products.ElectronicsProduct;
import store.products.Product;

import java.awt.*;

public class Test {
    public static void main(String [] args) {
        StoreEngine engine = StoreEngine.getInstance();
        ElectronicsProduct e = new ElectronicsProduct("Laptop", 2010.5, 1500, "high quality description", Category.BOOKS, Color.black, "ghjkl" ,7, "dell");
        engine.addProduct(e);
        Customer customer = new Customer("John Doe", "yael@");
        customer.addToCart(e, 5);
        System.out.println(customer.getOrderHistory());
        customer.addToCart(e,200);
        customer.checkout();
        System.out.println(customer.getOrderHistory());
        System.out.println("cust11111111111111111111111" + customer.toString());
        System.out.println("engineeeeee2222222222222222222222" + engine.toString() + "\n\n\n\n\n");
        System.out.println("orders\n" + engine.getAllOrders());
        Customer customer2 = new Customer("Jane Smith", "jane@");
        customer2.addToCart(e, 3);
        customer2.checkout();
        System.out.println("cust22222222222222222222222" + customer2.toString());
        System.out.println("engineeeeee33333333333333333333" + engine.toString());
        System.out.println("orders\n" + engine.getAllOrders());





    }
}