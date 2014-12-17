package com.xqbase.java.tree;

/**
 * Class implementing a node of a binary tree.
 *
 * @author Tony He
 */
public class BTNode<E> implements BTPosition<E> {

    private E element;
    private BTPosition left;
    private BTPosition right;
    private BTPosition parent;

    @Override
    public void setElement(E element) {
        this.element = element;
    }

    @Override
    public BTPosition<E> getLeft() {
        return left;
    }

    @Override
    public void setLeft(BTPosition<E> left) {
        this.left = left;
    }

    @Override
    public BTPosition<E> getRight() {
        return right;
    }

    @Override
    public void setRight(BTPosition<E> right) {
        this.right = right;
    }

    @Override
    public BTPosition<E> getParent() {
        return parent;
    }

    @Override
    public void setParent(BTPosition<E> parent) {
        this.parent = parent;
    }

    @Override
    public E element() {
        return element;
    }
}
