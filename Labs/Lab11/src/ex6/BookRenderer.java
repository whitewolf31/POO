package ex6;

import javax.swing.*;
import java.awt.*;

public class BookRenderer extends JPanel implements ListCellRenderer<Book> {

    @Override
    public Component getListCellRendererComponent(JList list, Book value, int index, boolean isSelected, boolean cellHasFocus) {
        removeAll();
        JLabel iconLabel = new JLabel();
        JLabel bookNameLabel = new JLabel();
        JLabel authorLabel = new JLabel();
        iconLabel.setIcon(new ImageIcon("./laborator11/Imagini/" + value.getIconName() + ".jpg"));
        bookNameLabel.setText(value.getName());
        authorLabel.setText(value.getAuthor());
        authorLabel.setForeground(Color.BLUE);
        setOpaque(true);
        if (isSelected || cellHasFocus) {
            setBackground(Color.RED);
        } else {
            if (index % 2 > 0) {
                setBackground(Color.YELLOW);
            } else setBackground(Color.GREEN);
        }
        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.add(bookNameLabel);
        textPanel.add(authorLabel);
        textPanel.setLayout(new GridLayout(2, 1));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(iconLabel);
        add(textPanel);
        return this;
    }
}
