package app.core;

import app.entities.Character;
import app.entities.Enemy;
import app.entities.Shop;
import app.shared.Coords;
import app.types.CellEnum;
import app.types.DirectionEnum;

import java.util.ArrayList;

public class Grid extends ArrayList<ArrayList<Cell>> {
    private Character player;

    private Cell currentCell;

    private Grid(Integer length, Integer width, Character player) {
        this.player = player;
        for (int i = 0; i < length; i++) {
            ArrayList<Cell> currentRow = new ArrayList<Cell>();
            add(currentRow);
            for (int j = 0; j < width; j++) {
                currentRow.add(new Cell(new Coords(i, j)));
            }
        }
        currentCell = get(0).get(0);
        generateHardcoded();
    }

    private void generateHardcoded() {
        get(0).get(3).setCellElement(new Shop(), CellEnum.SHOP);
        get(1).get(3).setCellElement(new Shop(), CellEnum.SHOP);
        get(2).get(0).setCellElement(new Shop(), CellEnum.SHOP);
        get(3).get(4).setCellElement(new Enemy(), CellEnum.ENEMY);
        get(4).get(4).setType(CellEnum.FINISH);
    }

    public Integer getBoardLength() {
        return size();
    }

    public Integer getBoardWidth() {
        return isEmpty() ? 0 : get(0).size();
    }

    public Cell getCurrentCell() { return currentCell; }

    public Character getPlayer() { return player; }

    public static Grid generateGrid(Integer length, Integer width, Character player) {
        return new Grid(length, width, player);
    }

    public Boolean navigate(DirectionEnum direction) {
        Coords currentCoords = currentCell.getCoords();
        if (direction == DirectionEnum.NORTH) {
            if (currentCoords.getX() - 1 < 0) return false;
            currentCell = get(currentCoords.getX() - 1).get(currentCoords.getY());
        } else if (direction == DirectionEnum.SOUTH) {
            if (currentCoords.getX() + 1 >= size()) return false;
            currentCell = get(currentCoords.getX() + 1).get(currentCoords.getY());
        } else if (direction == DirectionEnum.WEST) {
            if (currentCoords.getY() - 1 < 0) return false;
            currentCell = get(currentCoords.getX()).get(currentCoords.getY() - 1);
        } else if (direction == DirectionEnum.EAST) {
            if (currentCoords.getY() + 1 >= getBoardWidth()) return false;
            currentCell = get(currentCoords.getX()).get(currentCoords.getY() + 1);
        }
        player.getCoords().setCoords(currentCell.getCoords());

        return true;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ArrayList<Cell> row : this) {
            for (Cell loopCell: row) {
                Boolean isCurrentCell = false;
                if (loopCell.getCoords().equals(currentCell.getCoords())) isCurrentCell = true;
                if (isCurrentCell) {
                    sb.append("P");
                    if (loopCell.getType() != CellEnum.EMPTY) {
                        sb.append(loopCell).append(" ");
                    } else sb.append("  ");
                } else {
                    if (loopCell.getVisited()) sb.append(loopCell);
                    else sb.append("?");
                    sb.append("  ");
                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
