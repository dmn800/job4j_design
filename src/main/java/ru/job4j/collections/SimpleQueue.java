package ru.job4j.collections;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int size = 0;
    int count = 0;

    public T poll() {
        if (count == 0) {
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
