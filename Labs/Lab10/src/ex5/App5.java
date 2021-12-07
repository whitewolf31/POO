package ex5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class App5 {
    JFrame frame;
    Color[] colorsButton = {Color.GREEN, Color.BLUE, Color.RED, Color.YELLOW, Color.BLACK, Color.WHITE, Color.GRAY, Color.MAGENTA, Color.ORANGE};
    Color[] colorsText = {Color.ORANGE, Color.GREEN, Color.BLUE, Color.RED, Color.YELLOW, Color.BLACK, Color.WHITE, Color.GRAY, Color.MAGENTA};

    public App5() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(100, 100));
        frame.setLayout(new FlowLayout());
        JButton button = new JButton();
        button.setText("Colors");
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setMnemonic(KeyEvent.VK_C);
        button.setPreferredSize(new Dimension(100, 100));
        button.addActionListener(e -> setColors((JButton) e.getSource()));
        frame.add(button);
        frame.show();
        frame.pack();
    }

    public void setColors(JButton button) {
        int randomNum1 = (int) Math.floor(Math.random() * colorsButton.length);
        int randomNum2 = (int) Math.floor(Math.random() * colorsText.length);
        button.setBackground(colorsButton[randomNum1]);
        button.setForeground(colorsButton[randomNum2]);
    }

    public static void main(String[] args) {
        App5 test = new App5();
    }
}
