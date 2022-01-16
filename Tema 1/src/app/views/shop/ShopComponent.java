package app.views.shop;

import app.Game;
import app.entities.Shop;
import app.errors.InsufficientCoinsException;
import app.errors.InsufficientInventoryWeightException;
import app.player.Inventory;
import app.player.inventory.Potion;
import app.views.game.GameComponent;
import app.views.shared.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ShopComponent extends JPanel {
    private static ShopComponent instance = null;

    private Game game;

    private JLabel coinLabel;

    private JLabel errorMsg;

    public ArrayList<JPanel> potionCards;

    private ShopComponent() {
        game = Game.getInstance();
        setLayout(null);
        initialize();
    }

    public static ShopComponent getInstance() {
        if (instance == null) instance = new ShopComponent();

        return instance;
    }

    private void initialize() {
        JPanel titlePanel = new JPanel();
        titlePanel.setSize(new Dimension(980, 50));
        JLabel title = new JLabel("Buy something from the shop");
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(title);
        titlePanel.setBounds(10, 10, 980, 50);
        JButton backButton = new JButton("Go Back");
        backButton.setBounds(10, 65, 100, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setCurrentView(GameComponent.getInstance(), "World of Marcel");
            }
        });
        coinLabel = new JLabel();
        coinLabel.setFont(new Font(title.getFont().getName(), Font.ITALIC, 20));
        coinLabel.setForeground(Color.GREEN);
        coinLabel.setSize(new Dimension(980, 50));
        coinLabel.setBounds(10, 120, 980, 50);
        errorMsg = new JLabel();
        errorMsg.setFont(new Font(title.getFont().getName(), Font.ITALIC, 20));
        errorMsg.setForeground(Color.RED);
        errorMsg.setSize(new Dimension(980, 50));
        errorMsg.setBounds(10, 175, 980, 50);
        errorMsg.setVisible(false);
        resetTitle();
        add(titlePanel);add(backButton);add(coinLabel);add(errorMsg);
    }

    private void resetTitle() {
        Inventory playerInventory = game.selectedCharacter.getInventory();
        coinLabel.setText("You currently have " + playerInventory.getCoins() + " coins and " + playerInventory.getRemainingWeight() + " remaining weight!");
    }

    public void setShopItems(Shop linkedShop) {
        removeAll();
        initialize();
        potionCards = new ArrayList<JPanel>();
        int yAxis = 230;
        for (Potion potion: linkedShop.getPotionList()) {
            JPanel currentPanel = new JPanel();
            GridLayout gridLayout = new GridLayout(4, 0);
            currentPanel.setLayout(gridLayout);
            gridLayout.setVgap(5);
            currentPanel.setMaximumSize(new Dimension(1000, 100));
            JLabel name = new JLabel(potion.getClass().getSimpleName());
            JLabel costLabel = new JLabel("Cost: " + potion.getPrice());
            JLabel weightLabel = new JLabel("Weight: " + potion.getWeight());
            JLabel regenLabel = new JLabel("Regen Value: " + potion.getRegenValue());
            currentPanel.add(name);currentPanel.add(costLabel);currentPanel.add(weightLabel);currentPanel.add(regenLabel);
            Color initialBg = currentPanel.getBackground();
            currentPanel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        errorMsg.setVisible(false);
                        game.selectedCharacter.buyPotion(potion);
                        linkedShop.buyPotion(linkedShop.getPotionList().indexOf(potion));
                        remove(currentPanel);
                        repositionCards(currentPanel);
                        resetTitle();
                    } catch (Exception err) {
                        if (err instanceof InsufficientCoinsException) {
                            errorMsg.setText("You don't have enough coin for this potion!");
                            errorMsg.setVisible(true);
                        } else if (err instanceof InsufficientInventoryWeightException) {
                            errorMsg.setText("You don't have enough space for this potion!");
                            errorMsg.setVisible(true);
                        } else {
                            errorMsg.setText("Unknown error thrown. View console for details");
                            errorMsg.setVisible(true);
                            err.printStackTrace();
                        }
                    }
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
            potionCards.add(currentPanel);
        }
    }

    public void repositionCards(JPanel panelToRemove) {
        int indexToRemove = potionCards.indexOf(panelToRemove);
        int yAxis = 230 + indexToRemove * 110;
        for (int i = indexToRemove + 1; i < potionCards.size(); i++) {
            potionCards.get(i).setBounds(10, yAxis, 980, 100);
            yAxis += 110;
        }
        potionCards.remove(indexToRemove);
        revalidate();
        repaint();
    }
}
