
package store.gui.view;

import store.gui.controllers.StoreController;
import store.products.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class ProductPanel extends JPanel {


    /*  Panel to display a product in the catalog
     */
    public ProductPanel(Product product, StoreController controller){
        setLayout(new BorderLayout());
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        setPreferredSize(new Dimension(400, 120));


        String img = product.getImagePath();
        img = img.trim();
        img = img.replace("\"", "");

        URL imageUrl = getClass().getResource("/images/" + img);

        JLabel imageLabel;

        try {
            BufferedImage buffered = ImageIO.read(imageUrl);
            ImageIcon scaledIcon = createScaledIcon(buffered, 100, 100);
            imageLabel = new JLabel(scaledIcon);
        } catch (Exception e) {
            System.out.println("FAILED TO LOAD IMAGE: [" + img + "]");
            imageLabel = new JLabel("No Image");
        }


        imageLabel.setPreferredSize(new Dimension(100, 100));
        imageLabel.setToolTipText(product.toString());



        if (controller.isInCart(product)){
            setBackground(new Color(230, 255, 230));
            setOpaque(true);
        }
        else{
            setOpaque(true);
            setBackground(Color.WHITE);
        }
        JLabel nameLabel = new JLabel(product.getName(), SwingConstants.CENTER);
        JLabel priceLabel = new JLabel("$" + product.getPrice(), SwingConstants.CENTER);


        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.add(nameLabel);
        textPanel.add(priceLabel);

        add(imageLabel, BorderLayout.WEST);
        add(textPanel, BorderLayout.CENTER);

        imageLabel.setToolTipText(product.toString());


        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.refreshAfterChangeInCart();
                controller.showProductDetails(product);
            }
        });

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));



    }

    /*  Creates a scaled ImageIcon from a BufferedImage
     */
    private ImageIcon createScaledIcon(BufferedImage img, int maxW, int maxH) {
        int originalW = img.getWidth();
        int originalH = img.getHeight();

        double scale = Math.min(
                (double) maxW / originalW,
                (double) maxH / originalH
        );
        if (scale > 1.0) {
            scale = 1.0;
        }
        int newW = (int) (originalW * scale);
        int newH = (int) (originalH * scale);

        Image scaled = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

}
