package app.views.shared;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class HoverMouseListener implements MouseListener {

    private Color initialBg;

    JComponent component;

    public HoverMouseListener(Color initialBg, JComponent component) {
        this.initialBg = initialBg;
        this.component = component;
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        component.setBackground(new Color(196, 196, 196));
        component.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        component.setBackground(initialBg);
        component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
}
