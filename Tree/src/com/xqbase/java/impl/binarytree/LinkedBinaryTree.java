package com.xqbase.java.impl.binarytree;

import com.xqbase.java.Position;
import com.xqbase.java.exceptions.BoundaryViolationException;
import com.xqbase.java.exceptions.EmptyTreeException;
import com.xqbase.java.exceptions.InvalidPositionException;

import java.util.Iterator;

/**
 * An implementation of the {@link com.xqbase.java.impl.binarytree.BinaryTree}
 * by means of linked structure.
 *
 * @author Tony He
 */
public class LinkedBinaryTree<E> implements BinaryTree<E> {

    // Reference to the root.
    private BTPosition<E> root;

    // Number of nodes.
    private int size;

    // Constructor. (create an empty tree)
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    @Override
    public Position<E> left(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
        return null;
    }

    @Override
    public Position<E> right(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
        return null;
    }

    @Override
    public boolean hasLeft(Position<E> p) throws InvalidPositionException {
        BTPosition<E> pp = checkPosition(p);
        return (pp.getLeft() != null);
    }

    @Override
    public boolean hasRight(Position<E> p) throws InvalidPositionException {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Iterable<Position<E>> positions() {
        return null;
    }

    @Override
    public Position<E> root() throws EmptyTreeException {
        return null;
    }

    @Override
    public Position<E> parent(Position<E> v) throws BoundaryViolationException {
        return null;
    }

    @Override
    public Iterable<Position<E>> children(Position<E> v) {
        return null;
    }

    @Override
    public boolean isLeaf(Position<E> v) throws InvalidPositionException {
        return false;
    }

    @Override
    public boolean isRoot(Position<E> v) throws InvalidPositionException {
        checkPosition(v);
        return (v == root);
    }

    /**
     * Casts to BTPosition if p is a good binary tree node.
     */
    protected BTPosition<E> checkPosition(Position<E> p) throws InvalidPositionException {
        if (p == null || !(p instanceof BTPosition))
            throw new InvalidPositionException("Position is valid");

        return (BTPosition<E>) p;
    }
}
