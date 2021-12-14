package ex3;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App3 {
    private JFrame frame;
    private JTextField titleField;
    private JTextField authorField;
    private DefaultListModel<Book> listModel;

    public App3(DefaultListModel<Book> books) {
        frame = new JFrame("Exercitiul 3");
        listModel = books;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setLayout(new GridLayout(0, 2));
        JList list = new JList(books);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (list.isSelectionEmpty()) return;

                Book selectedBook = (Book) list.getSelectedValue();
                titleField.setText(selectedBook.getName());
                authorField.setText(selectedBook.getAuthor());
            }
        });
        JLabel labelForTitle = new JLabel("Title");
        JLabel labelForAuthor = new JLabel("Author");
        titleField = new JTextField();
        authorField = new JTextField();
        JButton deleteButton = new JButton("Sterge");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.isSelectionEmpty()) return;
                listModel.remove(list.getSelectedIndex());
                titleField.setText("");
                authorField.setText("");
            }
        });
        JPanel panel = new JPanel();
        panel.setMinimumSize(new Dimension(250, 500));
        panel.setLayout(new GridLayout(5, 1));
        panel.add(labelForTitle);
        panel.add(titleField);
        panel.add(labelForAuthor);
        panel.add(authorField);
        panel.add(deleteButton);
        frame.add(list);
        frame.add(panel);
        frame.show();
        frame.pack();
    }

    public static void main(String[] args) {
        DefaultListModel<Book> listModel = new DefaultListModel();
        listModel.addElement(new Book("Harry Potter", "J.K. Rowling", ""));
        listModel.addElement(new Book("Game of Thrones", "George RR Martin", ""));
        listModel.addElement(new Book("Lord of the Rings", "J RR Tolkien", ""));
        listModel.addElement(new Book("asdajsnfasf", "afs RR Martin", ""));
        listModel.addElement(new Book("Gafsafasfs", "George RR fsafasfas", ""));
        App3 app2 = new App3(listModel);
    }
}
