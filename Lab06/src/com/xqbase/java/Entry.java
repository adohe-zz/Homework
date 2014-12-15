package com.xqbase.java;

/**
 * An Entry is actually an association between a key {@code key}
 * and a value {@code value}, that is, an entry is simply a key-value
 * pair. A typical Composition Pattern use case.
 *
 * @author Tony He
 */
public interface Entry<K, V> {

    K getKey();

    V getValue();
}
