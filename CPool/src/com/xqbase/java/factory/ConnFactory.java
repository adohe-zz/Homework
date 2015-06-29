package com.xqbase.java.factory;

import java.io.IOException;

/**
 * Factory for poolable blocking connections.
 *
 * @param <T> the route type that represents the opposite endpoint of a pooled
 *   connection.
 * @param <C> the connection type.
 * @since 4.2
 */
public interface ConnFactory<T, C> {

    C create(T route) throws IOException;

}
