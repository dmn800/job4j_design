package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {
    private final Integer[] data;
    private int index = 0;

    public NonNullIterator(Integer[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (index < data.length && data[index] == null) {
            index++;
        }
        return index < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

    public static void main(String[] args) {
        Iterator<Integer> iterator = new NonNullIterator(
                new Integer[]{4, 2, null, null}
        );
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
