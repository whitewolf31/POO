package app.views.end;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndComponent extends JPanel {

    public EndComponent(String endMessage) {
        setSize(new Dimension(1000, 1000));
        setLayout(null);
        initialize(endMessage);
    }

    private void initialize(String endMessage) {
        JPanel messageContainer = new JPanel();
        messageContainer.setBounds(0, 475, 1000, 50);
        add(messageContainer);

        JLabel endMessageLabel = new JLabel(endMessage);
        endMessageLabel.setFont(new Font(endMessageLabel.getFont().getName(), Font.BOLD, 24));
        endMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageContainer.add(endMessageLabel);

        JButton close = new JButton("Close");
        close.setBounds(450, 530, 100, 30);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(close);
    }
}
