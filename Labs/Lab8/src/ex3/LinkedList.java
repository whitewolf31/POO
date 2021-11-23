package ex3;

import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {
    public Node<E> firstElement;
    public Node<E> lastElement;

    public LinkedList() {
        firstElement = null;
        lastElement = null;
    }

    public void addFirst(E data) {
        Node<E> firstNode = new Node<E>(data, firstElement);
        firstElement = firstNode;
        if (lastElement == null) lastElement = firstElement;
    }

    public void add(E data) {
        Node<E> newNode = new Node<E>(data);
        if (lastElement == null) {
            firstElement = newNode;
            lastElement = newNode;

            return;
        }

        lastElement.setNextNode(newNode);
        lastElement = lastElement.nextNode;
    }

    public E getNode() {
        return firstElement.getValue();
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<E>(this);
    }

    private static class Node<E> {
        private E value;
        private Node<E> nextNode;

        public Node(E value, Node<E> nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }

        public Node(E value) {
            this(value, null);
        }

        public E getValue() {
            return value;
        }

        public void setNextNode(Node<E> next) {
            nextNode = next;
        }
    }

    class ListIterator<E> implements Iterator<E> {
        Node<E> currentElement;

        public ListIterator(LinkedList<E> list) {
            currentElement = list.firstElement;
        }

        @Override
        public boolean hasNext() {
            return currentElement != null;
        }

        @Override
        public E next() {
            E data = currentElement.getValue();
            currentElement = currentElement.nextNode;

            return data;
        }
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        Node<E> looper = firstElement;
        while (looper != null) {
            s.append(looper.getValue() + ", ");
            looper = looper.nextNode;
        }

        return s.toString();
    }
}
