package app.views.init;

import app.Game;
import app.core.Entity;
import app.entities.Character;
import app.views.game.GameComponent;
import app.views.shared.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class SelectCharacterComponent extends JPanel {
    private List<Entity> availableCharacters;

    private Game game;

    private static SelectCharacterComponent instance = null;

    private SelectCharacterComponent() {
        availableCharacters = Game.getInstance().selectedAccount.getCharacters();
        setLayout(null);
        game = Game.getInstance();

        initialize();
    }

    public static SelectCharacterComponent getInstance() {
        if (instance == null) instance = new SelectCharacterComponent();

        return instance;
    }

    public SelectCharacterComponent reinitialize() {
        instance = null;

        return getInstance();
    }

    private void initialize() {
        JPanel titlePanel = new JPanel();
        titlePanel.setSize(new Dimension(980, 50));
        JLabel title = new JLabel("Select Character from list below");
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(title);
        titlePanel.setBounds(10, 10, 980, 50);
        add(titlePanel);
        int yAxis = 70;
        for (Entity character: availableCharacters) {
            JPanel currentPanel = new JPanel();
            GridLayout gridLayout = new GridLayout(4, 0);
            currentPanel.setLayout(gridLayout);
            gridLayout.setVgap(5);
            currentPanel.setMaximumSize(new Dimension(1000, 100));
            Character castCharacter = (Character) character;
            JLabel name = new JLabel(castCharacter.getName());
            JLabel levelLabel = new JLabel("Current Level: " + castCharacter.getCurrentLevel());
            JLabel xpLabel = new JLabel("Current XP: " + castCharacter.getCurrentXp());
            JLabel otherInfoLabel = new JLabel("HP: " + castCharacter.getCurrentHP() + ", Mana: " + castCharacter.getCurrentMana());
            currentPanel.add(name);currentPanel.add(levelLabel);currentPanel.add(xpLabel);currentPanel.add(otherInfoLabel);
            Color initialBg = currentPanel.getBackground();
            currentPanel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    game.selectedCharacter = castCharacter;
                    game.setCurrentView(GameComponent.getInstance(), "World of Marcel");
                }

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {
                    currentPanel.setBackground(new Color(196, 196, 196));
                    currentPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    currentPanel.setBackground(initialBg);
                    currentPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });
            currentPanel.setBounds(10, yAxis, 980, 100);
            currentPanel.setBorder(new RoundedBorder(5));
            yAxis += 110;
            add(currentPanel);
        }
    }
}
