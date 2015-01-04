package com.xqbase.java;

/**
 * Adds visit operation when traversal
 * the tree nodes.
 *
 * @author Tony He
 */
public interface Visitor<E> {

    /**
     * Visits the node.
     */
    void visit(Position<E> p);
}
