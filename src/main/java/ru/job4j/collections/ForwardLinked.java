package ru.job4j.collections;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    private static class Node<T> {
        private T item;
        private Node<T> next;

        public Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(value, null);
        Node<T> currNode = head;
        if (head == null) {
            head = newNode;
        } else {
            while (currNode.next != null) {
                currNode = currNode.next;
            }
            currNode.next = newNode;
        }
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> node = head;
        int count = 0;
        while (count < index) {
            node = node.next;
            count++;
        }
        return node.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException("Queue is empty");
        }
        T del = head.item;
        Node<T> first = head.next;
        head.next = null;
        head.item = null;
        head = first;
        size--;
        modCount++;
        return del;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            Node<T> nextIt = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return nextIt != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<T> curIt = nextIt;
                nextIt = curIt.next;
                return curIt.item;
            }
        };
    }
}
