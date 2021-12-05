package ex3;

import java.util.ArrayList;
import java.util.ListIterator;

public class IntegerMatrix extends AMatrix<Integer>{

    public static void main(String[] args) {
        ArrayList<Integer> line1 = new ArrayList();
        line1.add(1);
        line1.add(2);
        line1.add(3);
        ArrayList<Integer> line2 = new ArrayList();
        line2.add(4);
        line2.add(5);
        line2.add(6);
        ArrayList<Integer> line3 = new ArrayList();
        line3.add(7);
        line3.add(8);
        line3.add(9);
        IntegerMatrix matrix = new IntegerMatrix();
        matrix.add(line1);
        matrix.add(line2);
        matrix.add(line3);
        IntegerMatrix matrix2 = (IntegerMatrix) matrix.clone();
        System.out.println(matrix.addition(matrix2));

    }

    @Override
    public AMatrix<Integer> addition(AMatrix<Integer> m1) {
        AMatrix<Integer> resultMatrix = new IntegerMatrix();
        ListIterator<ArrayList<Integer>> listIterator = m1.listIterator();
        ListIterator<ArrayList<Integer>> resultIterator = resultMatrix.listIterator();
        for (ArrayList<Integer> line: this) {
            ArrayList<Integer> m1Line = listIterator.next();
            ListIterator<Integer> nextIterator = line.listIterator();
            ArrayList<Integer> newList = new ArrayList<Integer>();
            while (nextIterator.hasNext()) {
                int idx = nextIterator.nextIndex();
                newList.add(sum(nextIterator.next(), m1Line.get(idx)));
            }
            resultMatrix.add(newList);
        }

        return resultMatrix;
    }

    @Override
    public Integer sum(Integer obj1, Integer obj2) {
        return obj1 + obj2;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        for (ArrayList<Integer> line: this) {
            s.append(line);
            s.append("\n");
        }

        return s.toString();
    }
}
