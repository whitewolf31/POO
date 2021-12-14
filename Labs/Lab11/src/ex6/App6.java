package ex6;

import ex2.App2;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class App6 {
    private JFrame frame;

    public App6(DefaultListModel<Book> books) {
        frame = new JFrame("Exercitiul 6");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setLayout(new GridLayout());
        JList list = new JList(books);
        list.setCellRenderer(new BookRenderer());
        frame.add(list);
        frame.show();
        frame.pack();
    }

    public static void main(String[] args) {
        DefaultListModel<Book> listModel = new DefaultListModel();
        listModel.addElement(new Book("Harry Potter", "J.K. Rowling", "cpp"));
        listModel.addElement(new Book("Game of Thrones", "George RR Martin", "cs"));
        listModel.addElement(new Book("Lord of the Rings", "J RR Tolkien", "android"));
        listModel.addElement(new Book("asdajsnfasf", "afs RR Martin", "cs"));
        listModel.addElement(new Book("Gafsafasfs", "George RR fsafasfas", "cpp"));
        App6 app2 = new App6(listModel);
    }
}
