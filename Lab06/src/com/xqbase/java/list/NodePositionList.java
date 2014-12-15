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

    // TODO: Update the check logical
    private DNode<E> checkPosition(Position<E> p) {
        DNode<E> temp = (DNode<E>) p;
        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public Position<E> first() {
        if (isEmpty())
            return null;

        return head.getNext();
    }

    @Override
    public Position<E> last() {
        if (isEmpty())
            return null;

        return tail.getPrev();
    }

    @Override
    public Position<E> next(Position<E> p) {
        DNode<E> v = checkPosition(p);
        DNode<E> next = v.getNext();
        if (next  == tail)
            return null;
        return next;
    }

    @Override
    public Position<E> prev(Position<E> p) {
        DNode<E> v = checkPosition(p);
        DNode<E> prev = v.getPrev();
        if (prev == head)
            return null;
        return prev;
    }

    @Override
    public void addFirst(E element) {
        size++;
        DNode<E> newNode = new DNode<E>(head, head.getNext(), element);
        head.getNext().setPrev(newNode);
        head.setNext(newNode);
    }

    @Override
    public void addLast(E element) {
        size++;
        DNode<E> newNode = new DNode<E>(tail.getPrev(), tail, element);
        tail.getPrev().setNext(newNode);
        tail.setPrev(newNode);
    }

    @Override
    public void addAfter(Position<E> p, E element) {
        DNode<E> v = checkPosition(p);
        size++;
        DNode<E> newNode = new DNode<E>(v, v.getNext(), element);
        v.getNext().setPrev(newNode);
        v.setNext(newNode);
    }

    @Override
    public void addBefore(Position<E> p, E element) {
        DNode<E> v = checkPosition(p);
        size++;
        DNode<E> newNode = new DNode<E>(v.getPrev(), v, element);
        v.getPrev().setNext(newNode);
        v.setPrev(newNode);
    }

    @Override
    public E remove(Position<E> p) {
        DNode<E> v = checkPosition(p);
        size--;
        DNode<E> prev = v.getPrev();
        DNode<E> next = v.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        E vEle = v.getElement();
        v.setPrev(null);
        v.setNext(null);
        return vEle;
    }

    @Override
    public E set(Position<E> p, E element) {
        DNode<E> v = checkPosition(p);
        E oldEle = v.getElement();
        v.setElement(element);
        return oldEle;
    }
}
