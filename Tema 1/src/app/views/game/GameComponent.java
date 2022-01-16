package app.views.game;

import app.Game;
import app.core.Cell;
import app.core.Grid;
import app.entities.Enemy;
import app.entities.Shop;
import app.shared.JSONReader;
import app.types.CellEnum;
import app.views.end.EndComponent;
import app.views.fight.FightComponent;
import app.views.game.components.DirectionPanelComponent;
import app.views.game.components.MapComponent;
import app.views.shop.ShopComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class GameComponent extends JPanel {
    private static GameComponent instance = null;

    private Game game;

    private MapComponent mapComponent;

    private DirectionPanelComponent directionPanelComponent;

    private JButton actionButton;

    private JPanel storyPanel;

    private JTextArea storyField;

    private JPanel alertPanel;

    private JTextArea alertField;

    private GameComponent() {
        game = Game.getInstance();
        initialize();
    }

    public static GameComponent getInstance() {
        if (instance == null) instance = new GameComponent();

        return instance;
    }

    private void initialize() {
        JSONReader jsonReader = new JSONReader();
        try {
            game.cellList = jsonReader.readStories();
        } catch (Exception e) {
            e.printStackTrace();
        }
        game.gameGrid = Grid.generateGrid(5, 5, game.selectedCharacter, false);
        game.gameGrid.getCurrentCell().visit();
        setLayout(null);
        setSize(new Dimension(1000, 750));
        mapComponent = MapComponent.getInstance();
        mapComponent.setGameComponent(this);
        add(mapComponent);
        directionPanelComponent = DirectionPanelComponent.getInstance();
        directionPanelComponent.setGameComponent(this);
        add(directionPanelComponent);
        initStoryPanel();
        initActionButton();
        changeStory();
        initAlertPanel();
    }

    private void initStoryPanel() {
        storyPanel = new JPanel();
        storyPanel.setBounds(250, 600, 500, 100);
        storyField = new JTextArea();
        storyField.setLineWrap(true);
        storyField.setSize(new Dimension(500, 100));
        storyField.setBackground(storyPanel.getBackground());
        storyField.setEditable(false);
        storyField.setFont(new Font(storyField.getFont().getName(), Font.ITALIC, 20));
        storyPanel.add(storyField);
        add(storyPanel);
    }

    private void initActionButton() {
        actionButton = new JButton();
        actionButton.setSize(new Dimension(100, 50));
        actionButton.setBounds(450, 700, 100, 50);
        actionButton.setVisible(false);
        add(actionButton);
    }

    public void changeState() {
        changeStory();
        CellEnum cellType = game.gameGrid.getCurrentCell().getType();
        if (cellType == CellEnum.EMPTY || cellType == CellEnum.FINISH) {
            actionButton.setVisible(false);
            if (cellType == CellEnum.FINISH) {
                game.setCurrentView(new EndComponent("GG. You won."), "World of Marcel");
            }
        } else {
            if (cellType == CellEnum.SHOP) {
                actionButton.setVisible(true);
                actionButton.setText("Visit Shop");
                actionButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ShopComponent shopComponent = ShopComponent.getInstance();
                        shopComponent.setShopItems((Shop) game.gameGrid.getCurrentCell().getCellElement());
                        game.setCurrentView(ShopComponent.getInstance(), "Shop");
                    }
                });
            } else {
                actionButton.setVisible(false);
                Enemy enemy = (Enemy) game.gameGrid.getCurrentCell().getCellElement();
                if (enemy.getCurrentHP() > 0) game.setCurrentView(FightComponent.getInstance(), "Fight");
            }
        }
    }

    private void changeStory() {
        CellEnum cellType = game.gameGrid.getCurrentCell().getType();
        List<String> storyList = game.cellList.get(cellType);
        int storyListSize = storyList.size();
        Random rand = new Random();
        int selectedStoryIndex = rand.nextInt(storyListSize);
        storyField.setText(storyList.get(selectedStoryIndex));
    }

    private void initAlertPanel() {
        alertPanel = new JPanel();
        alertPanel.setBounds(250, 750, 500, 100);
        alertField = new JTextArea();
        alertField.setLineWrap(true);
        alertField.setSize(new Dimension(500, 100));
        alertField.setBackground(alertPanel.getBackground());
        alertField.setEditable(false);
        alertField.setFont(new Font(storyField.getFont().getName(), Font.ITALIC, 20));
        alertField.setForeground(Color.GREEN);
        alertPanel.add(alertField);
        add(alertPanel);
    }

    public void setAlert(String alertText) {
        alertField.setText(alertText);
    }
}
