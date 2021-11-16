package ex4;

import ex2.LinkedSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TreeMapList extends TreeMap {

    public static void main(String[] args) {
        TreeMapList map = new TreeMapList();
        try {
            File file = new File("test01.txt");
            Scanner scanner = new Scanner(file);
            int lineIdx = 1;
            while (scanner.hasNextLine()) {
                String currentLine= scanner.nextLine();
                String[] splitLine = currentLine.split("[^a-zA-Z]+");
                for (int i = 0; i < splitLine.length; i++) {
                    map.add(splitLine[i], lineIdx);
                }
                lineIdx++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        System.out.println(map);
    }
    
    public void add(Object key, Object toAdd) {
        if (!containsKey(key)) {
            LinkedSet list = new LinkedSet();
            list.add(toAdd);
            put(key, list);
            return;
        };

        LinkedSet currentList = (LinkedSet) get(key);
        currentList.add(toAdd);
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        for (Object entry : entrySet()) {
            Map.Entry e = (Map.Entry) entry;
            s.append(e.getKey().toString() + "\n");
            s.append(e.getValue().toString() + "\n");
            LinkedSet list = (LinkedSet) e.getValue();
            s.append(list.size() + "\n");
        }

        return s.toString();
    }
}
