
package store.gui.view;

import store.cart.CartItem;
import store.gui.controllers.StoreController;
import store.products.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class CartItemPanel extends JPanel {

    /* Displays a single item in the cart with its details and a remove button
     */
    public CartItemPanel(CartItem item, StoreController controller, CartWindow cartWindow){

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 80));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        Product product = item.getProduct();
        JLabel nameLabel = new JLabel("Name: " + product.getName());
        JLabel priceLabel = new JLabel("Price: " + product.getPrice());
        JLabel quantityLabel = new JLabel("Quantity: " + item.getQuantity());


        String imageMame = product.getImagePath();
        URL imageURL = getClass().getResource("/images/" + imageMame);

        JLabel imageLabel;
        if (imageURL != null) {
            imageLabel = new JLabel(new ImageIcon(imageURL));
            imageLabel.setPreferredSize(new Dimension(60, 60));


        }
        else
            imageLabel = new JLabel("No Image");
        imageLabel.setPreferredSize(new Dimension(60, 60));


        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.removeFromCart(product);
                cartWindow.refresh();
            }
        });

        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.PAGE_AXIS));
        dataPanel.add(nameLabel);
        dataPanel.add(priceLabel);
        dataPanel.add(quantityLabel);
        add(imageLabel, BorderLayout.WEST);
        add(dataPanel, BorderLayout.CENTER);
        add(removeButton, BorderLayout.EAST);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel successLabel = new JLabel();
                successLabel.setForeground(Color.GREEN);
                successLabel.setVisible(false);

                if (controller.checkout()){
                    successLabel.setText("Checkout successful!");
                    successLabel.setVisible(true);
                    add(successLabel);
                    revalidate();
                    Timer timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            successLabel.setVisible(false);
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
                else{
                    successLabel.setText("checkout failed");
                    successLabel.setBackground(Color.RED);
                    successLabel.setVisible(true);
                    add(successLabel);
                    revalidate();
                    Timer timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            successLabel.setVisible(false);
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }

                cartWindow.refresh();
            }
        });





    }

}
