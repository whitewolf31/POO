package ex4;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;
import java.util.Vector;

public class App4 {
    JFrame frame;
    JTree tree;
    JTable table;
    JTextField rowField;
    JTextField columnField;
    JPanel panel;
    DefaultTableModel tableModel;

    public void generateTree(FileTreeModel node) {
        File currentNode = (File) node.getUserObject();
        File[] childFiles = currentNode.listFiles();
        if (childFiles == null) return;

        for (File childFile: childFiles) {
            FileTreeModel childNode = new FileTreeModel(childFile);
            node.add(childNode);
            generateTree(childNode);
        }
    }

    public App4() {
        frame = new JFrame("Exercitiul 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setLayout(new GridLayout(0, 2));
        FileTreeModel rootDir = new FileTreeModel(new File("./"));
        generateTree(rootDir);
        rowField = new JTextField();
        columnField = new JTextField();
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        tree = new JTree(rootDir);
        tableModel = new DefaultTableModel();
        tableModel.addColumn("File Name");
        tableModel.addColumn("Dimension");
        tableModel.addColumn("Last Modified");
        tableModel.addColumn("Type");
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                if (tree.isSelectionEmpty()) return;

                File selectedFile = (File) ((FileTreeModel) tree.getLastSelectedPathComponent()).getUserObject();
                tableModel.setRowCount(0);
                if (selectedFile.isFile()) return;
                for (File childFile: selectedFile.listFiles()) {
                    Vector<Object> rowData = new Vector();
                    rowData.add(childFile.getName());
                    rowData.add(childFile.length());
                    rowData.add(childFile.lastModified());
                    rowData.add(childFile.isDirectory() ? "Folder" : "File");
                    tableModel.addRow(rowData);
                }
            }
        });
        table = new JTable(tableModel);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                rowField.setText(String.valueOf(table.getSelectedRow()));
            }
        });
        table.getColumnModel().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                columnField.setText(String.valueOf(table.getSelectedColumn()));
            }
        });
        JScrollPane tableScrollPane = new JScrollPane(table);
        panel.add(tableScrollPane);
        panel.add(rowField);
        panel.add(columnField);
        frame.add(tree);
        frame.add(panel);
        frame.show();
        frame.pack();
    }

    public static void main(String[] args) {
        App4 app4 = new App4();
    }
}

class FileTreeModel extends DefaultMutableTreeNode {
    public FileTreeModel(File file) {
        userObject = file;
    }

    @Override
    public String toString() {
        File file = (File) userObject;
        return file.getName();
    }
}
