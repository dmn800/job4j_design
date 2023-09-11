package ru.job4j.collections.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        boolean rsl = table[iKey(key)] == null;
        if (rsl) {
            table[iKey(key)] = new MapEntry<>(key, value);
            modCount++;
            count++;
        }
        return rsl;
    }

    private boolean check(K key) {
        MapEntry<K, V> element = table[iKey(key)];
        return element != null
                && Objects.hashCode(element.key) == Objects.hashCode(key)
                && Objects.equals(element.key, key);
    }

    @Override
    public V get(K key) {
        V rsl = null;
        if (check(key)) {
            rsl = table[iKey(key)].value;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int iKey (K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                newTable[hash(Objects.hashCode(entry.key)) & (capacity - 1)] = entry;
            }
        }
        table = newTable;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = check(key);
        if (rsl) {
            table[iKey(key)] = null;
            modCount++;
            count--;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
