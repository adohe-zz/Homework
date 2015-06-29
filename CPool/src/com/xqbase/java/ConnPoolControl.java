package com.xqbase.java;

import com.xqbase.java.stats.PoolStats;

/**
 * Interface to control runtime properties of a {@link ConnPool} such as
 * maximum total number of connections or maximum connections per route
 * allowed.
 *
 * @param <T> the route type that represents the opposite endpoint of a pooled
 *   connection.
 * @since 4.2
 */
public interface ConnPoolControl<T> {

    void setMaxTotal(int max);

    int getMaxTotal();

    void setDefaultMaxPerRoute(int max);

    int getDefaultMaxPerRoute();

    void setMaxPerRoute(final T route, int max);

    int getMaxPerRoute(final T route);

    PoolStats getTotalStats();

    PoolStats getStats(final T route);

}
