package com.xqbase.java.tree;

import java.util.Iterator;

/**
 * An interface for a tree where nodes can have an arbitrary number of children.
 *
 * @author Tony He
 */
public interface Tree<E> {

    /**
     * Returns the number of nodes in the tree.
     */
    int size();

    /**
     * Returns whether the tree is empty.
     */
    boolean isEmpty();

    /**
     * Returns an iterator of elements stored in the tree.
     */
    Iterator<E> iterator();

    /**
     * Returns an iterable collection of
     */
    Iterable<Position<E>> positions();

    /**
     * Returns the root of the tree.
     */
    Position<E> root();

    /**
     * Returns the parent of a given node.
     */
    Position<E> parent(Position<E> v);

    /**
     * Returns an iterable collection of the children of a given node.
     */
    Iterable<Position<E>> children(Position<E> v);

    /**
     * Returns whether a give node is leaf node.
     */
    boolean isLeaf(Position<E> v);

    /**
     * Returns whether a given node is the root.
     */
    boolean isRoot(Position<E> v);
}
