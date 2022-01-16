package app.views.fight.components;

import app.views.interfaces.SwingLogger;

import javax.swing.*;
import java.awt.*;

public class FightLoggerComponent extends JPanel implements SwingLogger {
    public FightLoggerComponent() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        setBackground(Color.WHITE);
        setSize(new Dimension(300, 200));
    }

    public void addLog(String log, Color textColor, Font font) {
        JLabel currentLabel = new JLabel(log);
        currentLabel.setForeground(textColor);
        currentLabel.setFont(font);
        add(currentLabel);
    }
}
