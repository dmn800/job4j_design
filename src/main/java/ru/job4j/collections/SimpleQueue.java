package ru.job4j.collections;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T delete;
        try {
            delete = in.pop();
        } catch (NoSuchElementException n) {
            throw new NoSuchElementException("Queue is empty");
        }
        return delete;
    }

    public void push(T value) {
        swap(in, out);
        out.push(value);
        swap(out, in);
    }

    private void swap(SimpleStack<T> firstStack, SimpleStack<T> secondStack) {
        while (true) {
            try {
                secondStack.push(firstStack.pop());
            } catch (NoSuchElementException n) {
                break;
            }
        }
    }
}
