package ex1;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class App1 {
    private JFrame frame;

    public App1(Vector<Book> books) {
        frame = new JFrame("Exercitiul 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setLayout(new GridLayout());
        JList list = new JList(books);
        JScrollPane scrollPane = new JScrollPane(list);
        frame.add(scrollPane);
        frame.show();
        frame.pack();
    }

    public static void main(String[] args) {
        Vector<Book> books = new Vector();
        books.add(new Book("Harry Potter", "J.K. Rowling", ""));
        books.add(new Book("Game of Thrones", "George RR Martin", ""));
        books.add(new Book("Lord of the Rings", "J RR Tolkien", ""));
        for (int i = 0; i < 15; i++) {
            books.add(new Book("asdewq", "rtyhgf", ""));
        }
        App1 app1 = new App1(books);
    }
}
