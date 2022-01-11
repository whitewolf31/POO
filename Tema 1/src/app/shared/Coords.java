package app.shared;

public class Coords {
    private Integer x;

    private Integer y;

    public Coords(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() { return x; }

    public Integer getY() { return y; }

    public Coords setCoords(Coords c) {
        x = c.getX();
        y = c.getY();

        return this;
    }

    public Coords setX(Integer x) {
        this.x = x;

        return this;
    }

    public Coords setY(Integer y) {
        this.y = y;

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coords) {
            Coords castedObj = (Coords) o;

            return x.equals(castedObj.getX()) && y.equals(castedObj.getY());
        }

        return false;
    }

}
