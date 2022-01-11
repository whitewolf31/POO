package app.core;

import app.shared.Coords;
import app.types.CellEnum;

public class Cell {
    private Coords coords;

    private CellEnum type;

    private CellElement cell;

    private Boolean visited;

    public Cell(Coords coords) {
        this.coords = coords;
        type = CellEnum.EMPTY;
        visited = false;
    }

    public void setCellElement(CellElement cell, CellEnum type) {
        this.type = type;
        this.cell = cell;
    }

    public void setType(CellEnum type) {
        this.type = type;
    }

    public void visit() { visited = true; }

    public CellEnum getType() { return type; }

    public Coords getCoords() { return coords; }

    public CellElement getCellElement() { return cell; }

    public Boolean getVisited() { return visited; }

    @Override
    public String toString() {
        if (type == CellEnum.EMPTY) return "N";
        if (type == CellEnum.FINISH) return "F";
        return cell.toCharacter();
    }
}
