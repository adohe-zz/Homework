package com.xqbase.java.impl.binarytree;

import com.xqbase.java.Position;
import com.xqbase.java.Tree;

/**
 * An interface for a binary tree, where each node can have zero, one or
 * two children .
 *
 * @author Tony He
 */
public interface BTree<E> extends Tree<E> {

    Position<E> left(Position<E> p);

    Position<E> right(Position<E> p);

    boolean hasLeft(Position<E> p);

    boolean hasRight(Position<E> p);
}
