package com.xqbase.java;

import com.xqbase.java.future.FutureCallback;

import java.util.concurrent.Future;

/**
 * <tt>ConnPool</tt> represents a shared pool connections can be leased from
 * and released back to.
 *
 * @param <T> the route type that represents the opposite endpoint of a pooled
 *   connection.
 * @param <E> the type of the pool entry containing a pooled connection.
 * @since 4.2
 */
public interface ConnPool<T, E> {

    /**
     * Attempts to lease a connection for the given route and with the given
     * state from the pool.
     *
     * @param route route of the connection.
     * @param state arbitrary object that represents a particular state
     *  (usually a security principal or a unique token identifying
     *  the user whose credentials have been used while establishing the connection).
     *  May be <code>null</code>.
     * @param callback operation completion callback.
     *
     * @return future for a leased pool entry.
     */
    Future<E> lease(final T route, final Object state, final FutureCallback<E> callback);

    /**
     * Releases the pool entry back to the pool.
     *
     * @param entry pool entry leased from the pool
     * @param reusable flag indicating whether or not the released connection
     *   is in a consistent state and is safe for further use.
     */
    void release(E entry, boolean reusable);

}