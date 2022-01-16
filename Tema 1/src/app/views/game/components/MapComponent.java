package app.views.game.components;

import app.Game;
import app.core.Cell;
import app.shared.Coords;
import app.types.CellEnum;
import app.views.game.GameComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MapComponent extends JPanel {
    private static MapComponent instance = null;

    private Game game;

    private ArrayList<ArrayList<CellComponent>> map;

    private GameComponent gameComponent;

    private MapComponent() {
        game = Game.getInstance();
        int width = game.gameGrid.getBoardWidth();
        int length = game.gameGrid.getBoardLength();
        setLayout(new GridLayout(length, width));
        setSize(length * 70, width * 70);
        setBounds((1000 - length * 70) / 2, (500 - width * 70) / 2, length * 70, width * 70);
        initialize();
    }

    public static MapComponent getInstance() {
        if (instance == null) instance = new MapComponent();

        return instance;
    }

    public void setGameComponent(GameComponent gameComponent) {
        this.gameComponent = gameComponent;
    }

    public void initialize() {
        map = new ArrayList<ArrayList<CellComponent>>();
        for (ArrayList<Cell> currentRow: game.gameGrid) {
            ArrayList<CellComponent> mapRow = new ArrayList<CellComponent>();
            map.add(mapRow);
            for (Cell currentCell: currentRow) {
                CellComponent cellComponent = new CellComponent(currentCell);
                cellComponent.drawCell();
                mapRow.add(cellComponent);
                add(cellComponent);
            }
        }
    }

    public void navigated(Coords prevCoords) {
        Cell currentCell = game.gameGrid.getCurrentCell();
        if (!currentCell.getVisited() && currentCell.getType() == CellEnum.EMPTY) {
            Random rand = new Random();
            int randNum = rand.nextInt(5);
            if (randNum < 3) {
                randNum = rand.nextInt(5) + 1;
                game.selectedCharacter.getInventory().giveCoins(randNum);
                String str = "You have found " + randNum + " coins!";
                gameComponent.setAlert(str);
            } else {
                gameComponent.setAlert("");
            }
        } else {
            gameComponent.setAlert("");
        }
        currentCell.visit();
        map.get(prevCoords.getX()).get(prevCoords.getY()).reDrawCell();
        Coords characterCoords = game.selectedCharacter.getCoords();
        map.get(characterCoords.getX()).get(characterCoords.getY()).reDrawCell();

    }
}
