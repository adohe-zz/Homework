package com.xqbase.java.tree;

/**
 * A binary tree position interface for the tree nodes.
 *
 * @author Tony He
 */
public interface BTPosition<E> extends Position<E> {

    /**
     * Sets the element stored at this position.
     */
    void setElement(E element);

    /**
     * Returns the left child of this position.
     */
    BTPosition<E> getLeft();

    /**
     * Sets the left child of this position.
     */
    void setLeft(BTPosition<E> left);

    /**
     * Returns the right child of this position.
     */
    BTPosition<E> getRight();

    /**
     * Sets the right child of this position.
     */
    void setRight(BTPosition<E> right);

    /**
     * Returns the parent of this position.
     */
    BTPosition<E> getParent();

    /**
     * Sets the parent of this position.
     */
    void setParent(BTPosition<E> parent);
}
