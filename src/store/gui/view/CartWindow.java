
package store.gui.view;

import store.cart.CartItem;
import store.engine.StoreEngine;
import store.gui.controllers.StoreController;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CartWindow extends JFrame {

    private StoreController controller;
    private JPanel itemsPanel;
    private JLabel totalLabel;
    private JButton checkoutButton;



    /* Displays the cart window with items, total price, and checkout option
     */
    public CartWindow(StoreController controller){
        super("Cart");
        this.controller = controller;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,600);
        setLayout(new BorderLayout());

        initItemsPanel();
        initBottomPanel();
        refresh();

        setVisible(true);

    }

    /**
     * Initializes the panel that displays cart items
     */
    private void initItemsPanel(){
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.PAGE_AXIS));
        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Initializes the bottom panel with total price and checkout button
     */
    private void initBottomPanel(){
        JPanel bottomPanel = new JPanel(new BorderLayout());
        totalLabel = new JLabel("Total: $0.00");

        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.checkout();
                refresh();
                JLabel message = new JLabel("Checkout successful!");
                JOptionPane.showMessageDialog(CartWindow.this, message);
                new OrderHistoryWindow(controller);
            }
        });
        bottomPanel.add(totalLabel, BorderLayout.WEST);
        bottomPanel.add(checkoutButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Refreshes the cart display with current items and total price
     */
    public void refresh(){
        itemsPanel.removeAll();
        List<CartItem> items = controller.getCartItems();

        if (items.isEmpty())
            itemsPanel.add(new JLabel("Your cart is empty."));
        else
            for (CartItem item : items)
                itemsPanel.add(new CartItemPanel(item, controller, this));

        totalLabel.setText("Total: " + controller.getTotalCartPrice() + "$");
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    public static void main(String[] args){
        StoreEngine engine = StoreEngine.getInstance();
        StoreController controller = new StoreController(engine, null);
        SwingUtilities.invokeLater(() -> new CartWindow(controller));

    }


}
