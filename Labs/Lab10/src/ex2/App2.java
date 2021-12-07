package ex2;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class App2 {
    JFrame frame;
    ButtonGroup answerGroup;

    public App2() {
        frame = new JFrame("Exercitiul 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 200));
        frame.setLayout(new FlowLayout());
        answerGroup = new ButtonGroup();
        JRadioButton radio1 = new JRadioButton();
        radio1.setText("Bucuresti");
        JRadioButton radio2 = new JRadioButton();
        radio2.setText("Sofia");
        JRadioButton radio3 = new JRadioButton();
        radio3.setText("Budapesta");
        JRadioButton radio4 = new JRadioButton();
        radio4.setText("Chisinau");
        answerGroup.add(radio1);
        answerGroup.add(radio2);
        answerGroup.add(radio3);
        answerGroup.add(radio4);
        JLabel label = new JLabel("Care este capitala Romaniei?");
        JButton submit = new JButton();
        submit.setText("Submit");
        submit.addActionListener(e -> checkAnswer());
        frame.add(label);
        frame.add(radio1);
        frame.add(radio2);
        frame.add(radio3);
        frame.add(radio4);
        frame.add(submit);
        frame.show();
        frame.pack();
    }

    public void checkAnswer() {
        Enumeration<AbstractButton> groupEnum = answerGroup.getElements();
        while (groupEnum.hasMoreElements()) {
            JRadioButton currentRadio = (JRadioButton) groupEnum.nextElement();
            if (currentRadio.isSelected() && currentRadio.getText().equals("Bucuresti")) {
                currentRadio.setOpaque(true);
                currentRadio.setBackground(Color.GREEN);
            }
            if (currentRadio.isSelected() && !currentRadio.getText().equals("Bucuresti")) {
                currentRadio.setOpaque(true);
                currentRadio.setBackground(Color.RED);
            }
        }
    }

    public static void main(String[] args) {
        App2 test = new App2();
    }
}
