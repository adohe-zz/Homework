package com.xqbase.java.list;

/**
 * PositionList is an interface for the Node List ADT.
 */
public interface PositionList<E> {

    /**
     * Returns the number of elements in this list.
     */
    int size();

    /**
     * Returns whether the list is empty.
     */
    boolean isEmpty();

    /**
     * Returns the first node in the list.
     */
    Position<E> first();

    /**
     * Returns the last node in the list.
     */
    Position<E> last();

    /**
     * Returns the node after the given in the list.
     */
    Position<E> next(Position<E> p);

    /**
     * Returns the node before the given node in the list.
     */
    Position<E> prev(Position<E> p);

    /**
     * Inserts an element at the front of the list.
     */
    void addFirst(E element);

    /**
     * Inserts an element at the tail of the list.
     */
    void addLast(E element);

    /**
     * Inserts an element after the given node in the list.
     */
    void addAfter(Position<E> p, E element);

    /**
     * Inserts an element before the given node in the list.
     */
    void addBefore(Position<E> p, E element);

    /**
     * Removes a node from the list, returning the element stored there.
     */
    E remove(Position<E> p);

    /**
     * Replaces the element stored at the given node, returning the old element.
     */
    E set(Position<E> p, E element);
}
