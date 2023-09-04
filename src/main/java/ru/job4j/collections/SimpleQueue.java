package ru.job4j.collections;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size = 0;
    private int count = 0;

    public T poll() {
        if (size == 0 && count == 0) {
            throw new NoSuchElementException("Queue is empty");
        } else if (count == 0) {
            while (size > 0) {
                out.push(in.pop());
                count++;
                size--;
            }
        }
        count--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}
