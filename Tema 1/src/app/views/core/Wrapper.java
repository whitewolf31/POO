package app.views.core;

import javax.swing.*;
import java.awt.*;

public class Wrapper extends JFrame {
    private static Wrapper instance = null;

    private Wrapper() {
        setMinimumSize(new Dimension(1000, 1000));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static Wrapper getInstance() {
        if (instance == null) instance = new Wrapper();

        return instance;
    }

    public void changeComponent(Component component, String title) {
        getContentPane().removeAll();
        getContentPane().revalidate();
        getContentPane().repaint();
        add(component);
        setTitle(title);
        pack();
        setVisible(true);
    }
}
