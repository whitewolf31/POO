package ex4;

import java.util.Collections;
import java.util.Vector;

public class Table {
    private Vector<Vector<Object>> table;

    public Table(Object[][] rows) {
        table = new Vector<Vector<Object>>();
        for (int i = 0; i < rows.length; i++) {
            table.add(new Vector<Object>());
            Collections.addAll(table.get(i), rows[i]);
        }
    }

    public Vector<Vector<Object>> getTable() {
        return table;
    }

    public void print(Print printer) {
        printer.printTable(this);
    }

    class AsciiPrinter implements Print {
        int[] tableLengths;

        public AsciiPrinter(int[] tableLengths) {
            this.tableLengths = tableLengths;
        }

        public void printTable(Table table) {
            StringBuffer tableFormat = new StringBuffer();
            for (int i = 0; i < tableLengths.length; i++) {
                tableFormat.append("%-" + (tableLengths[i]) + "s|");
            }
            tableFormat.append("%n");
            for (Vector<Object> row: table.getTable()) {
                System.out.format(tableFormat.toString(), row.toArray());
            }
        }
    }

    class CsvPrinter implements Print {
        String delimiter;

        public CsvPrinter(String delimiter) {
            this.delimiter = delimiter;
        }

        public CsvPrinter() {
            this(",");
        }

        public void printTable(Table table) {
            StringBuffer s = new StringBuffer();
            for (Vector<Object> row: table.getTable()) {
                for (Object obj: row) {
                    s.append(obj);
                    if (row.indexOf(obj) != row.size() - 1) s.append(delimiter);
                }
                s.append("\n");
            }
            System.out.println(s);
        }
    }

    public String toString() {
        return table.toString();
    }
}
