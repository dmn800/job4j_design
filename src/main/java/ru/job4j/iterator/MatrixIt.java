package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (row == data.length) {
            return false;
        }
        while (data[row].length == 0) {
            if (row + 1 == data.length) {
                break;
            }
            row++;
        }
        return column < data[row].length;
    }

    @Override
    public Integer next() {
        if  (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rsl = data[row][column];
        if (column < data[row].length) {
            column++;
        }
        if (column == data[row].length) {
            column = 0;
            row++;
        }
        return rsl;
    }

    public static void main(String[] args) {
        int[][] input = {
                {},
                {}
        };
        MatrixIt iterator = new MatrixIt(input);
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
    }
}
