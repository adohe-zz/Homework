package com.xqbase.java.tree;

/**
 * A tree position interface for the internal tree nodes.
 *
 * @author Tony he
 */
public interface Position<E> {

    /**
     * Return the object stored at this position.
     */
    E element();
}
