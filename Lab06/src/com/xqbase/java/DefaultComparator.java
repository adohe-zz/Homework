package com.xqbase.java;

import java.util.Comparator;

/**
 * A default comparator implementation using natural ordering.
 * @param <E>
 */
public class DefaultComparator<E> implements Comparator<E> {

    @Override
    public int compare(E o1, E o2) {
        return ((Comparable) o1).compareTo(o2);
    }
}
