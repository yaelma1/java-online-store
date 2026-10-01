
package store.gui.view;

import store.gui.controllers.StoreController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import store.core.Customer;
import store.engine.StoreEngine;

/**
 * Main window of the store application.
 * Allows users to log in as a customer or an admin.
 */
public class MainWindow extends JFrame {

    private static StoreWindow managerWindow = null;

    public MainWindow() {
        setTitle("Welcome to the Store");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton costumerButton = new JButton("login as Customer");
        JButton adminButton = new JButton("login as Admin");

        costumerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    StoreEngine engine = StoreEngine.getInstance();
                    Customer customer = new Customer("Default Customer", "email");
                    StoreController controller = new StoreController(engine, customer);
                    StoreWindow storeWindow = new StoreWindow(controller, false);
                    controller.setView(storeWindow);
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (managerWindow != null){
                    managerWindow.toFront();
                    return;
                }
                StoreEngine engine = StoreEngine.getInstance();
                Customer admin = new Customer("Admin", "email");
                StoreController controller = new StoreController(engine, admin);
                StoreWindow storeWindow = new StoreWindow(controller, true);
                managerWindow = storeWindow;
                controller.setView(storeWindow);
            }

        });


        setLayout(new FlowLayout());
        add(costumerButton);
        add(adminButton);
        setVisible(true);
        }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow();
        });
    }
}
