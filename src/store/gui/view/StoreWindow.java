
package store.gui.view;

import store.gui.controllers.StoreController;
import store.products.Product;
import store.products.Category;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class StoreWindow extends JFrame {

    private JPanel topPanel;
    private JPanel catalogPanel;
    private JPanel detailsPanel;
    private StoreController controller;
    private boolean isAdmin;

    /*  Main window for the online store application
     */
    public StoreWindow(StoreController controller, boolean isAdmin){
        super("Online Store");
        this.controller = controller;
        this.isAdmin = isAdmin;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000,600);
        setLayout(new BorderLayout());


        initTopPanel();
        initCatalogPanel();
        initDetailsPanel();

        setVisible(true);

    }

    /*  Initializes the top panel with search, load, save, cart, and category filter
     */
    private void initTopPanel(){
        topPanel = new JPanel(new FlowLayout());
        JTextField search = new JTextField(20);
        JButton loadButton = new JButton("Load Products from File");
        JButton saveButton = new JButton("Save Products to File");
        JButton cartButton = new JButton("View Cart");
        JButton addProductButton = new JButton("Add New Product");
        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CartWindow(controller);
            }
        });

        if (!isAdmin) // Only show cart button for non-admin users
            topPanel.add(cartButton);

        if (isAdmin) // Only show add product button for admin users
            topPanel.add(addProductButton);
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddProductWindow(controller);

            }
        });




        topPanel.add(search);
        topPanel.add(loadButton);
        topPanel.add(saveButton);

        add(topPanel, BorderLayout.NORTH);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loadProduct();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.saveProduct();
            }
        });

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = search.getText();
                controller.searchProducts(searchText);
            }
        });

        topPanel.add(new JLabel("Filter:"));

        JComboBox<Category> categoryCombo = new JComboBox<>(Category.values());
        categoryCombo.insertItemAt(null, 0);
        categoryCombo.setSelectedIndex(0);
        categoryCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Category selectedCategory = (Category) categoryCombo.getSelectedItem();
                controller.filterByCategory(selectedCategory);
            }
        });
    topPanel.add(categoryCombo);

    }

    /*  Initializes the catalog panel to display products
     */
    private void initCatalogPanel(){
        catalogPanel = new JPanel();
        catalogPanel.setLayout(new BoxLayout(catalogPanel, BoxLayout.Y_AXIS));
        catalogPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JScrollPane scroll = new JScrollPane(catalogPanel);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);
    }

    /*  Initializes the details panel to show selected product details
     */
    private void initDetailsPanel(){
        detailsPanel = new JPanel();
        detailsPanel.setPreferredSize(new Dimension(300, 600));
        add(detailsPanel, BorderLayout.EAST);
    }

    /*  Opens a file chooser dialog and returns the selected file
     */
    public File openFileChooser(){
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION)
            return chooser.getSelectedFile();

        return null;
    }

    /*  Refreshes the product catalog display with the given list of products
     */
    public void refreshProductList(List<Product> products){
        catalogPanel.removeAll();
        for (Product p : products)
            catalogPanel.add(new ProductPanel(p, controller));


        catalogPanel.revalidate();
        catalogPanel.repaint();
    }

    /*  Updates the details panel to show information about the specified product
     */
    public void updateDetailsPanel(Product product){
        detailsPanel.removeAll();
        detailsPanel.add(new ProductDetailsPanel(product, controller));

        detailsPanel.revalidate();
        detailsPanel.repaint();

    }

}
