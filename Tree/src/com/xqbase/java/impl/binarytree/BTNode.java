package com.xqbase.java.impl.binarytree;

/**
 * BTNode represents nodes in the {@link com.xqbase.java.impl.binarytree.BinaryTree}
 *
 * @author Tony He
 */
public class BTNode<E> implements BTPosition<E> {

    // Element stored at this node
    private E element;

    private BTPosition<E> left, right, parent; // adjacent nodes.

    // Constructor
    public BTNode(E element, BTPosition<E> left, BTPosition<E> right, BTPosition<E> parent) {
        setElement(element);
        setLeft(left);
        setRight(right);
        setParent(parent);
    }

    @Override
    public void setElement(E e) {
        this.element = e;
    }

    @Override
    public BTPosition<E> getLeft() {
        return this.left;
    }

    @Override
    public void setLeft(BTPosition<E> leftChild) {
        this.left = leftChild;
    }

    @Override
    public BTPosition<E> getRight() {
        return this.right;
    }

    @Override
    public void setRight(BTPosition<E> rightChild) {
        this.right = rightChild;
    }

    @Override
    public BTPosition<E> getParent() {
        return this.parent;
    }

    @Override
    public void setParent(BTPosition<E> parent) {
        this.parent = parent;
    }

    @Override
    public E element() {
        return this.element;
    }
}
