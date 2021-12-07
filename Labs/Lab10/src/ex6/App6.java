package ex6;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App6 {
    JFrame frame;
    JTextField field;
    JScrollPane scrollPane;
    JTextArea result;

    public App6() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1000, 500));
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));
        result = new JTextArea(30, 30);
        scrollPane = new JScrollPane(result);
        field = new JTextField(15);
        scrollPane.setVisible(false);
        JButton button = new JButton("Submit");
        button.addActionListener(e -> getResult());
        frame.add(field);
        frame.add(button);
        frame.add(scrollPane);
        frame.show();
        frame.pack();
    }

    public void getResult() {
        String path = field.getText();
        File f = new File(path);
        try {
            Scanner sc = new Scanner(f);
            StringBuffer s = new StringBuffer();
            while (sc.hasNextLine()) {
                s.append(sc.nextLine());
                s.append("\n");
            }
            scrollPane.setVisible(true);
            result.setText(s.toString());
        } catch (FileNotFoundException e) {
            field.setText("");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        App6 test = new App6();
    }
}
