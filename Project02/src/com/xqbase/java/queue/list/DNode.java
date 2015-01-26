package com.xqbase.java.queue.list;

/**
 * A DNode represents the node in the doubly-linked
 * list which implements the {@code Position} interface.
 * @param <E>
 */
public class DNode<E> implements Position<E> {

    private E element;
    private DNode<E> prev;
    private DNode<E> next;

    // Constructor
    public DNode(DNode<E> prev, DNode<E> next, E element) {
        this.prev = prev;
        this.next = next;
        this.element = element;
    }

    @Override
    public E element() {
        if (prev == null && next == null)
            return null;
        return element;
    }

    // Accessor Methods
    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public DNode<E> getPrev() {
        return prev;
    }

    public void setPrev(DNode<E> prev) {
        this.prev = prev;
    }

    public DNode<E> getNext() {
        return next;
    }

    public void setNext(DNode<E> next) {
        this.next = next;
    }
}
