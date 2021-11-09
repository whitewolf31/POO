package ex4;

import java.io.*;
import java.util.Vector;

public class Filter implements FileFilter {

    RandomAccessFile reader;
    Vector<String> extensionsWhitelist;
    Vector<String> namesWhitelist;

    public Filter() {
        try {
            reader = new RandomAccessFile("src/ex4/extension.in", "r");
            extensionsWhitelist = new Vector<String>();
            namesWhitelist = new Vector<String>();
            for (String currentLine = reader.readLine(); currentLine != null; currentLine = reader.readLine()) {
                extensionsWhitelist.add(currentLine);
            }
            reader.close();
            reader = new RandomAccessFile("src/ex4/words.in", "r");
            for (String currentLine = reader.readLine(); currentLine != null; currentLine = reader.readLine()) {
                namesWhitelist.add(currentLine);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Exiting...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("There was a problem reading the file");
            e.printStackTrace();
        }
    }

    @Override
    public boolean accept(File pathname) {
        String[] splitName = pathname.getName().split("\\.");
        String fileName = splitName[0];
        String extension = "." + splitName[splitName.length - 1];
        boolean isContained = false;
        for (int i = 0; i < namesWhitelist.size(); i++) {
            if (fileName.contains(namesWhitelist.get(i))) {
                isContained = true;
                break;
            }
        }
        if (!isContained || (!extensionsWhitelist.contains(extension) && !pathname.isDirectory())) return false;

        return true;
    }

    public static void main(String[] args) {
        Filter filter = new Filter();
        Filter.list(new File("src/director"), filter);
    }

    public static void list(File currentFile, Filter filter) {
        File[] files = currentFile.listFiles(filter);
        for (File f: files) {
            if (f.isDirectory()) Filter.list(f, filter);
            else System.out.println(f.getName());
        }
    }
}
