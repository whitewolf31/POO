package app.views.fight.components;

import app.views.shared.RoundedBorder;
import app.views.shared.RoundedPanel;

import javax.swing.*;
import java.awt.*;

public class BarComponent extends RoundedPanel {
    private int currentValue;

    private int maxValue;

    private Color color;

    private JPanel innerPanel;

    public BarComponent(int currentValue, int maxValue, Color color) {
        super(null, 10);
        this.currentValue = currentValue;
        this.maxValue = maxValue;
        this.color = color;
        initialize();
    }

    private void initialize() {
        innerPanel = new RoundedPanel(null, 10, color);
        int innerPanelWidth = (int) Math.floor((currentValue * 300.0) / maxValue);
        innerPanel.setBounds(0, 0, innerPanelWidth, 30);
        add(innerPanel);
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
        int innerPanelWidth = (int) Math.floor((currentValue * 300.0) / maxValue);
        innerPanel.setBounds(0, 0, innerPanelWidth, 30);
    }
}
