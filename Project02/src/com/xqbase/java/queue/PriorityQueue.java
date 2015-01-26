package com.xqbase.java.queue;

/**
 * A Java Priority Queue Implementation Using Sorted List
 *
 * @author Tony He
 */
public interface PriorityQueue<K, V> {

    /**
     * Returns the number of items in the priority queue.
     */
    int size();

    /**
     * Returns whether the queue is empty.
     */
    boolean isEmpty();

    /**
     * Returns but does not remove an entry with minimum key.
     */
    Entry<K, V> min();

    /**
     * Inserts a key-value pair and returns the created.
     */
    Entry<K, V> insert(K key, V value);

    /**
     * Returns and removes the entry with minimum key.
     */
    Entry<K, V> removeMin();
}
