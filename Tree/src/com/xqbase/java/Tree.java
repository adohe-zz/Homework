package com.xqbase.java;

import com.xqbase.java.exceptions.BoundaryViolationException;
import com.xqbase.java.exceptions.EmptyTreeException;
import com.xqbase.java.exceptions.InvalidPositionException;

import java.util.Iterator;

/**
 * An interface for a tree where nodes can have
 * arbitrary number of children.
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
    Position<E> root() throws EmptyTreeException;

    /**
     * Returns the parent of a given node.
     */
    Position<E> parent(Position<E> v) throws BoundaryViolationException;

    /**
     * Returns an iterable collection of the children of a given node.
     */
    Iterable<Position<E>> children(Position<E> v);

    /**
     * Returns whether a give node is leaf node.
     */
    boolean isLeaf(Position<E> v) throws InvalidPositionException;

    /**
     * Returns whether a given node is the root.
     */
    boolean isRoot(Position<E> v) throws InvalidPositionException;

    /**
     * Returns the depth of a given node in this tree.
     */
    int depth(Position<E> v) throws InvalidPositionException;
}
