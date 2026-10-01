package store.gui.view;

import store.gui.controllers.StoreController;

import javax.swing.*;

public class AdminWindow extends JFrame {

    public AdminWindow(StoreController controller){
        setTitle("Admin page");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        StoreWindow storeWindow = new StoreWindow(controller, true);
        add(storeWindow.getContentPane());
        setVisible(true);

    }

}
