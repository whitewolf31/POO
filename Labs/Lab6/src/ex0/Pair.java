package ex0;

public class Pair {
    Object value1, value2;

    public Pair(Object value1, Object value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public boolean equals(Object objToCompare) {
        if (objToCompare instanceof Pair) {
            Pair castedToPair = (Pair) objToCompare;

            return value1.equals(castedToPair.value1) && value2.equals(castedToPair.value2);
        }

        return false;
    }

    @Override
    public String toString() {
        return value1.toString() + " -> " + value2.toString();
    }
}
