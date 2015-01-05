package com.xqbase.java.impl.binarytree;

import com.xqbase.java.Position;
import com.xqbase.java.Tree;
import com.xqbase.java.exceptions.BoundaryViolationException;
import com.xqbase.java.exceptions.InvalidPositionException;

/**
 * An interface for a binary tree, where each node can have zero, one or
 * two children .
 *
 * @author Tony He
 */
public interface BinaryTree<E> extends Tree<E> {

    /**
     * Returns the left child of Position p.
     */
    Position<E> left(Position<E> p) throws InvalidPositionException, BoundaryViolationException;

    /**
     * Returns the right child of Position p.
     */
    Position<E> right(Position<E> p) throws InvalidPositionException, BoundaryViolationException;

    /**
     * Whether the Position p has left child or not.
     */
    boolean hasLeft(Position<E> p) throws InvalidPositionException;

    /**
     * Whether the Position p has left child or not.
     */
    boolean hasRight(Position<E> p) throws InvalidPositionException;
}
