package com.xqbase.java.tree;

/**
 * An interface for a binary tree, where each node can have
 * zero, one or two children.
 *
 * @author Tony He
 */
public interface BinaryTree<E> extends Tree<E> {

    /**
     * Returns the left child of a given node.
     */
    Position<E> left(Position<E> v);

    /**
     * Returns the right child of a given node.
     */
    Position<E> right(Position<E> v);

    /**
     * Returns whether a node has a left child.
     */
    boolean hasLeft(Position<E> v);

    /**
     * Returns whether a node has a right child.
     */
    boolean hasRight(Position<E> v);
}
