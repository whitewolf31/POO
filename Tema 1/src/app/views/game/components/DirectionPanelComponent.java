package app.views.game.components;

import app.Game;
import app.shared.Coords;
import app.types.DirectionEnum;
import app.views.game.GameComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DirectionPanelComponent extends JPanel {
    private static DirectionPanelComponent instance = null;

    private Game game;

    private MapComponent mapComponent;

    private GameComponent gameComponent;

    private DirectionPanelComponent() {
        game = Game.getInstance();
        mapComponent = MapComponent.getInstance();
        setLayout(new GridLayout(1, 4));
        setSize(new Dimension(350, 30));
        setBounds(325, 500, 350, 30);
        initialize();
    }

    public static DirectionPanelComponent getInstance() {
        if (instance == null) instance = new DirectionPanelComponent();

        return instance;
    }

    public void setGameComponent(GameComponent gameComponent) {
        this.gameComponent = gameComponent;
    }

    private void initialize() {
        JButton northButton = new JButton("North");
        JButton eastButton = new JButton("East");
        JButton southButton = new JButton("South");
        JButton westButton = new JButton("West");
        add(northButton);add(southButton);add(westButton);add(eastButton);
        northButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coords prevCoords = new Coords(game.selectedCharacter.getCoords().getX(), game.selectedCharacter.getCoords().getY());
                if (game.gameGrid.navigate(DirectionEnum.NORTH)) {
                    mapComponent.navigated(prevCoords);
                    gameComponent.changeState();
                }
            }
        });
        eastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coords prevCoords = new Coords(game.selectedCharacter.getCoords().getX(), game.selectedCharacter.getCoords().getY());
                if (game.gameGrid.navigate(DirectionEnum.EAST)) {
                    mapComponent.navigated(prevCoords);
                    gameComponent.changeState();
                }
            }
        });
        southButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coords prevCoords = new Coords(game.selectedCharacter.getCoords().getX(), game.selectedCharacter.getCoords().getY());
                if (game.gameGrid.navigate(DirectionEnum.SOUTH)) {
                    mapComponent.navigated(prevCoords);
                    gameComponent.changeState();
                }
            }
        });
        westButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coords prevCoords = new Coords(game.selectedCharacter.getCoords().getX(), game.selectedCharacter.getCoords().getY());
                if (game.gameGrid.navigate(DirectionEnum.WEST)) {
                    mapComponent.navigated(prevCoords);
                    gameComponent.changeState();
                }
            }
        });
    }
}
