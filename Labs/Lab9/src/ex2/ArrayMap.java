package ex2;

import java.util.*;

public class ArrayMap<K, V> extends AbstractMap<K, V> {
    Vector<K> keys;
    Vector<V> values;

    public static void main(String args[]) {
        ArrayMap<String, String> testMap = new ArrayMap();
        testMap.put("test1", "Laur");
        testMap.put("test2", "Andrei");
        testMap.put("test3", "Tudor");
        testMap.put("test4", "Alex");
        System.out.println(testMap);
        for (Map.Entry<String, String> entry: testMap.entrySet()) {
            System.out.println(entry);
        }
    }

    public ArrayMap() {
        keys = new Vector<K>();
        values = new Vector<V>();
    }

    @Override
    public V put(K key, V value) {
        V valueToReturn = null;
        if (keys.contains(key)) {
            valueToReturn = get(key);
            int idx = keys.indexOf(key);
            values.set(idx, value);
        } else {
            keys.add(key);
            values.add(value);
        }

        return valueToReturn;
    }

    @Override
    public V get(Object a) {
        if (keys.contains(a)) {
            int idx = keys.indexOf(a);

            return values.get(idx);
        }

        return null;
    }

    @Override
    public Set<K> keySet() {
        return new HashSet<K>(keys);
    }

    @Override
    public Collection<V> values() {
        return values;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("[");
        ListIterator<K> keyIterator = keys.listIterator();
        while (keyIterator.hasNext()) {
            int idx = keyIterator.nextIndex();
            s.append("{" + keyIterator.next().toString() + ": " + values.get(idx).toString() + "}");
            if (keyIterator.hasNext()) s.append(", ");
        }
        s.append("]");

        return s.toString();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entrySet = new HashSet<Map.Entry<K, V>>();
        ListIterator<K> keyIterator = keys.listIterator();
        while (keyIterator.hasNext()) {
            int idx = keyIterator.nextIndex();
            entrySet.add(new SimpleEntry<K, V>(keyIterator.next(), values.get(idx)));
        }

        return entrySet;
    }

    class SimpleEntry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;

        public SimpleEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V prevValue = this.value;
            this.value = value;

            return prevValue;
        }

        @Override
        public String toString() {
            return key + " => " + value;
        }
    }
}
