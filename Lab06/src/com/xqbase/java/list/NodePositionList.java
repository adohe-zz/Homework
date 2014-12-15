package com.xqbase.java.list;

/**
 * A Node List Implementation using a doubly linked list.
 *
 * @author Tony He
 */
public class NodePositionList<E> implements PositionList<E> {

    private DNode<E> head;
    private DNode<E> tail;
    private int size;

    public NodePositionList() {
        size = 0;
        head = new DNode<E>(null, null, null);
        tail = new DNode<E>(head, null, null);
        head.setNext(tail);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Position<E> first() {
        return null;
    }

    @Override
    public Position<E> last() {
        return null;
    }

    @Override
    public Position<E> next(Position<E> p) {
        return null;
    }

    @Override
    public Position<E> prev(Position<E> p) {
        return null;
    }

    @Override
    public void addFirst(E element) {

    }

    @Override
    public void addLast(E element) {

    }

    @Override
    public void addAfter(Position<E> p, E element) {

    }

    @Override
    public void addBefore(Position<E> p, E element) {

    }

    @Override
    public E remove(Position<E> p) {
        return null;
    }

    @Override
    public E set(Position<E> p, E element) {
        return null;
    }
}
