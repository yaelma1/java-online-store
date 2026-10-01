
package store.gui.view;

import store.gui.controllers.StoreController;
import store.orders.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderHistoryWindow extends JFrame {

    private StoreController controller;
    private JPanel ordersPanel;

    /*  Window to display order history
     */
    public OrderHistoryWindow(StoreController controller){
        super("Order History");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.controller = controller;
        setLayout(new BorderLayout());
        ordersPanel = new JPanel();
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(ordersPanel);
        add(scrollPane, BorderLayout.CENTER);
        List<Order> orders = controller.getOrderHistory();
        for (Order order : orders) {
            OrderPanel orderPanel = new OrderPanel(order, controller);
            ordersPanel.add(orderPanel);
        }

        this.setVisible(true);

    }
}
