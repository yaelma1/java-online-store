
package store.gui.view;

import store.cart.CartItem;
import store.gui.controllers.StoreController;
import store.orders.Order;

import javax.swing.*;
import java.awt.*;

public class OrderPanel extends JPanel {

    /*  Panel to display individual order details
     */
    public OrderPanel(Order order, StoreController controller){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JLabel idLabel = new JLabel("Order" + order.getOrderID());
        JLabel totalLabel = new JLabel("Total: " + order.getTotalAmount() + "$");

        add(idLabel);
        add(totalLabel);

        add(Box.createVerticalStrut(5));
        add(new JSeparator());

        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setPreferredSize(new Dimension(400, order.getItems().size() * 30));// each item = 30 height

        JLabel itemLabel = new JLabel();

        for (CartItem item : order.getItems()) {
            itemLabel.setText(item.getProduct().getName() + " | quantity: " + item.getQuantity() + " | price: " + item.getProduct().getPrice() + "$");
            itemPanel.add(itemLabel);
        }
        add(itemPanel);
    }

}
