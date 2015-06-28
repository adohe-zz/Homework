package com.xqbase.java.future;

import com.xqbase.java.PoolEntry;

/**
 * Pool entry callabck.
 *
 * @param <T> the route type that represents the opposite endpoint of a pooled
 *   connection.
 * @param <C> the connection type.
 * @since 4.3
 */
public interface PoolEntryCallback<T, C> {

    void process(PoolEntry<T, C> entry);

}
