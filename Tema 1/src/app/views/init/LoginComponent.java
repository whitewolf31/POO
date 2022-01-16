package app.views.init;

import app.Game;
import app.auth.Account;
import app.shared.JSONReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class LoginComponent extends JPanel {

    private static LoginComponent instance = null;

    private Game game;

    private LoginComponent() {
        setLayout(null);
        game = Game.getInstance();
        initialize();
    }

    public static LoginComponent getInstance() {
        if (instance == null) instance = new LoginComponent();

        return instance;
    }

    public LoginComponent reinitialize() {
        instance = null;

        return getInstance();
    }

    private void initialize() {
        JLabel logoLabel = null;
        try {
            BufferedImage logo = ImageIO.read(new File("assets/logo.png"));
            logoLabel = new JLabel(new ImageIcon(logo));
            logoLabel.setBounds(250, 50, 500, 206);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(325, 300, 100, 30);
        JTextField emailTextField = new JTextField();
        emailTextField.setBounds(425, 300, 250, 30);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(325, 350, 100, 30);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(425, 350, 250, 30);
        JButton submitButton = new JButton("Log In");
        submitButton.setBounds(320, 400, 360, 30);
        add(logoLabel);add(emailLabel);add(emailTextField);add(passwordLabel);add(passwordField);add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailTextField.getText();
                String password = String.valueOf(passwordField.getPassword());
                JSONReader jsonReader = new JSONReader();
                try {
                    game.accounts = jsonReader.readAccounts();
                    for (Account account : game.accounts) {
                        if (account.getEmail().equals(email)) {
                            if (account.login(email, password)) game.selectedAccount = account;
                        }
                    }
                } catch (Exception err) {
                    err.printStackTrace();
                }
                if (game.selectedAccount == null) {
                    JLabel errorMsg = new JLabel("Wrong Email/Password");
                    errorMsg.setForeground(Color.RED);
                    errorMsg.setBounds(325, 450, 360, 30);
                    add(errorMsg);
                } else game.setCurrentView(SelectCharacterComponent.getInstance(), "Select Character");
            }
        });
    }
}
