package com.xqbase.java.impl.binarytree;

import com.xqbase.java.Position;

/**
 * Interface for Binary Tree Position.
 *
 * @author Tony He
 */
public interface BTPosition<E> extends Position<E> {

    /**
     * Sets element located at this position.
     */
    void setElement(E e);

    /**
     * Gets the left child of this position.
     */
    BTPosition<E> getLeft();

    /**
     * Sets the left child of this position.
     */
    void setLeft(BTPosition<E> leftChild);

    /**
     * Gets the right child of this position.
     */
    BTPosition<E> getRight();

    /**
     * Sets the right child of this postion.
     */
    void setRight(BTPosition<E> rightChild);

    /**
     * Gets the parent of this position.
     */
    BTPosition<E> getParent();

    /**
     * Sets the parent of this position.
     */
    void setParent(BTPosition<E> parent);
}
