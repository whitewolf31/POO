package ex5;

import ex2.LinkedSet;

import java.io.File;
import java.util.*;

public class Problema5 {
    public static void main(String[] args) {
        HashMap<String, Integer> sizeMap = new HashMap<String, Integer>();
        TreeMap<String, Integer> sortedByName = new TreeMap<String, Integer>();
        File directory = new File(".");
        File[] subFiles = directory.listFiles();
        for (File file: subFiles) {
            if (!file.isDirectory()) {
                sizeMap.put(file.getName(), (int) file.length());
                sortedByName.put(file.getName(), (int) file.length());
            }
        }
        LinkedList<Map.Entry<String, Integer>> entryList = new LinkedList<Map.Entry<String, Integer>>(sizeMap.entrySet());
        entryList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        LinkedHashMap<String, Integer> sortedBySize = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry: entryList) {
            sortedBySize.put(entry.getKey(), entry.getValue());
        }
        System.out.println(sortedByName);
        System.out.println(sortedBySize);
    }
}
