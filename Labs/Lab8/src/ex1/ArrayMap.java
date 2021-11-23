package ex1;

import java.util.*;

public class ArrayMap<K, V> extends AbstractMap<K, V> {
    private ArrayList<ArrayMapEntry<K, V>> arrayList;

    public ArrayMap() {
        arrayList = new ArrayList<ArrayMapEntry<K, V>>();
    }

    public Set entrySet() {
        return new HashSet<ArrayMapEntry<K, V>>(arrayList);
    }

    public int size() {
        return super.size();
    }

    public V put(K key, V value) {
        if (containsKey(key)) {
            V oldValue = get(key);
            for (ArrayMapEntry<K, V> entry: arrayList) {
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                }
            }

            return oldValue;
        }
        arrayList.add(new ArrayMapEntry<K, V>(key, value));

        return value;
    }

    class ArrayMapEntry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        public ArrayMapEntry(K key, V value) {
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
            V oldValue = this.value;
            this.value = value;

            return oldValue;
        }

        @Override
        public String toString() {
            return key.toString() + " => " + value.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof ArrayMapEntry) {
                ArrayMapEntry<?, ?> castValue = (ArrayMapEntry<?, ?>) o;
                if (castValue.getValue().equals(value) && castValue.getKey().equals(key))
                    return true;
            }

            return false;
        }

        @Override
        public int hashCode() {
            return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
        }
    }
}
