package ru.job4j.collections;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private final SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean rsl = true;
        for (T t : set) {
            if (Objects.equals(value, t)) {
                rsl = false;
                break;
            }
        }
        if (rsl) {
            set.add(value);
        }
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T t : set) {
            if (Objects.equals(value, t)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

}
