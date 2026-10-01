
package store.gui.view;

import store.gui.controllers.StoreController;
import store.products.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.net.URL;

public class ProductDetailsPanel extends JPanel {

    /* Panel to display product details and an add to cart button
     */
    public ProductDetailsPanel(Product product, StoreController controller){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JLabel name = new JLabel("Name: " + product.getName());
        JLabel description = new JLabel("Description: " + product.getDescription());
        JLabel price = new JLabel("Price: " + product.getPrice());
        JLabel stock = new JLabel("Stock: " + product.getStock());

        String imageMame = product.getImagePath();
        URL imageURL = getClass().getResource("/images/" + imageMame);
        JLabel imageLabel;
        if (imageURL != null)
            imageLabel = new JLabel(new ImageIcon(imageURL));
        else
            imageLabel = new JLabel("No Image");

        JButton addToCart = new JButton("Add to Cart");
        addToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel successLabel = new JLabel();
                successLabel.setForeground(Color.GREEN);
                successLabel.setVisible(false);

                boolean succ = controller.addToCart(product);
                if (succ){
                    successLabel.setText("Product added to cart!");
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
                else {
                    successLabel.setText("Failed to add product to cart.");
                    successLabel.setForeground(Color.RED);
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
                controller.refreshAfterChangeInCart();
            }
        });
        add(name);
        add(description);
        add(price);
        add(stock);
        add(imageLabel);
        add(addToCart);
    }
}
