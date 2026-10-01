
package store.gui.view;

import store.gui.controllers.StoreController;
import store.products.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A window for adding a new product to the store.
 */
public class AddProductWindow extends JFrame {


    private final JButton addButton;
    private final JTextField nameField;
    private final JTextField priceField;
    private final JTextField stockField;
    private final JTextField descriptionField;

    private final JComboBox<Category> categoryComboBox;
    private Category selectedCategory;

    private final JTextField colorField;
    private final JTextField imageUrlField;

    private JPanel extraFieldsPanel;


    private Map<String, JTextField> extraFields = new HashMap<>();


    public AddProductWindow(StoreController controller) {
        setTitle("Add new Product");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Category:"));
        categoryComboBox = new JComboBox<>(Category.values());
        add(categoryComboBox);
        selectedCategory = (Category) categoryComboBox.getSelectedItem();

        add(new JLabel("Product Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Price:"));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel("Stock Quantity:"));
        stockField = new JTextField();
        add(stockField);

        add(new JLabel("Description:"));
        descriptionField = new JTextField();
        add(descriptionField);


        add(new JLabel("Color:"));
        colorField = new JTextField();
        add(colorField);

        add(new JLabel("Image URL:"));
        imageUrlField = new JTextField();
        add(imageUrlField);

        extraFieldsPanel = new JPanel();
        extraFieldsPanel.setLayout(new BoxLayout(extraFieldsPanel, BoxLayout.Y_AXIS));
        add(extraFieldsPanel);

        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCategory = (Category) categoryComboBox.getSelectedItem();

                extraFields.clear();
                extraFieldsPanel.removeAll();

                List<String> fields = controller.getExtraFieldsByCategory(selectedCategory);

                for (String field : fields){
                    extraFieldsPanel.add(new JLabel(field + ":"));
                    JTextField XField = new JTextField();
                    extraFieldsPanel.add(XField);

                    extraFields.put(field, XField);
                }
                extraFieldsPanel.revalidate();
                extraFieldsPanel.repaint();
            }
        });

        JScrollPane scrollPane = new JScrollPane(extraFieldsPanel);
        add(scrollPane);

        addButton = new JButton("Add Product");
        add(addButton);
        setVisible(true);



        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] productData = new String[8 + extraFields.size()];
                if (selectedCategory == null)
                    System.out.println( "no category selected");
                String productType = controller.getProductTypeByCategory(selectedCategory);
                productData[0] = productType;
                productData[1] = nameField.getText();
                productData[2] = priceField.getText();
                productData[3] = stockField.getText();
                productData[4] = descriptionField.getText();
                productData[5] = selectedCategory.name();
                productData[6] = colorField.getText();
                productData[7] = imageUrlField.getText();
                int index = 8;
                for (String key : extraFields.keySet()) {
                    productData[index] = extraFields.get(key).getText();
                    index++;
                }
                try {
                    controller.addNewProduct(productData);
                    controller.refreshAfterChangeInCart();
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(AddProductWindow.this,
                            "Error adding product");
                    return;
                }
                dispose();
            }
        });
    }
}

