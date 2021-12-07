package ex3;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class App3 {
    JFrame frame;
    ArrayList<JCheckBox> answerGroup;

    public App3() {
        frame = new JFrame("Exercitiul 3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 200));
        frame.setLayout(new FlowLayout());
        answerGroup = new ArrayList();
        JCheckBox box1 = new JCheckBox();
        box1.setText("Ceapa");
        JCheckBox box2 = new JCheckBox();
        box2.setText("Mar");
        JCheckBox box3 = new JCheckBox();
        box3.setText("Para");
        JCheckBox box4 = new JCheckBox();
        box4.setText("Castravete");
        answerGroup.add(box1);
        answerGroup.add(box2);
        answerGroup.add(box3);
        answerGroup.add(box4);
        JLabel label = new JLabel("Care din urmatoarele sunt fructe?");
        JButton submit = new JButton();
        submit.setText("Submit");
        submit.addActionListener(e -> checkAnswer());
        frame.add(label);
        frame.add(box1);
        frame.add(box2);
        frame.add(box3);
        frame.add(box4);
        frame.add(submit);
        frame.show();
        frame.pack();
    }

    public void checkAnswer() {
        ArrayList<String> correctAnswers = new ArrayList();
        correctAnswers.add("Mar");
        correctAnswers.add("Para");
        for (JCheckBox box: answerGroup) {
            if (correctAnswers.contains(box.getText())) {
                box.setOpaque(true);
                box.setBackground(Color.GREEN);
            }
            if (box.isSelected() && !correctAnswers.contains(box.getText())) {
                box.setOpaque(true);
                box.setBackground(Color.RED);
            }
        }
    }

    public static void main(String[] args) {
        App3 test = new App3();
    }

}
