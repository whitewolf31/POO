package ex4;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class App4 {
    JFrame frame;
    ArrayList<Integer> colors;
    ArrayList<JSlider> sliders;
    JTextField field;

    public App4() {
        frame = new JFrame();
        colors = new ArrayList();
        sliders = new ArrayList();
        colors.add(0);
        colors.add(0);
        colors.add(0);
        field = new JTextField();
        field.setOpaque(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 200));
        frame.setLayout(new FlowLayout());
        JSlider slider1 = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        slider1.setOpaque(true);
        slider1.setBackground(Color.RED);
        slider1.addChangeListener(l -> setColors());
        JSlider slider2 = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        slider2.setOpaque(true);
        slider2.addChangeListener(l -> setColors());
        slider2.setBackground(Color.GREEN);
        JSlider slider3 = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        slider3.setOpaque(true);
        slider3.setBackground(Color.BLUE);
        slider3.addChangeListener(l -> setColors());
        sliders.add(slider1);
        sliders.add(slider2);
        sliders.add(slider3);
        frame.add(field);
        frame.add(slider1);
        frame.add(slider2);
        frame.add(slider3);
        frame.show();
        frame.pack();


    }

    public void setColors() {
        for (int i = 0; i < 3; i++) {
            JSlider slider = sliders.get(i);
            colors.set(i, slider.getValue());
        }
        field.setBackground(new Color(colors.get(0), colors.get(1), colors.get(2)));
    }

    public static void main(String[] args) {
        App4 test = new App4();
    }

}
