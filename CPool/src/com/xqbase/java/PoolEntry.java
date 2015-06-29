package com.xqbase.java;

import com.xqbase.java.annotation.GuardedBy;
import com.xqbase.java.annotation.ThreadSafe;

import java.util.concurrent.TimeUnit;

/**
 * Pool entry containing a pool connection object along with its route.
 * <p/>
 * The connection contained by the pool entry may have an expiration time which
 * can be either set upon construction time or updated with
 * the {@link #updateExpiry(long, java.util.concurrent.TimeUnit)}.
 * <p/>
 * Pool entry may also have an object associated with it that represents
 * a connection state (usually a security principal or a unique token identifying
 * the user whose credentials have been used while establishing the connection).
 *
 * @param <T> the route type that represents the opposite endpoint of a pooled
 *   connection.
 * @param <C> the connection type.
 * @since 4.2
 */
@ThreadSafe
public abstract class PoolEntry<T, C> {

    private final String id;
    private final T route;
    private final C conn;
    private final long created;
    private final long validUnit;

    @GuardedBy("this")
    private long updated;

    @GuardedBy("this")
    private long expiry;

    private volatile Object state;

    /**
     * Creates new <tt>PoolEntry</tt> instance.
     *
     * @param id unique identifier of the pool entry. May be <code>null</code>.
     * @param route route to the opposite endpoint.
     * @param conn the connection.
     * @param timeToLive maximum time to live. May be zero if the connection
     *   does not have an expiry deadline.
     * @param tunit time unit.
     */
    public PoolEntry(final String id, final T route, final C conn,
                     final long timeToLive, final TimeUnit tunit) {
        super();
        this.id = id;
        this.route = route;
        this.conn = conn;
        this.created = System.currentTimeMillis();
        if (timeToLive > 0) {
            this.validUnit = this.created + tunit.toMillis(timeToLive);
        } else {
            this.validUnit = Long.MAX_VALUE;
        }
        this.expiry = this.validUnit;
    }

    /**
     * Creates new <tt>PoolEntry</tt> instance without an expiry deadline.
     *
     * @param id unique identifier of the pool entry. May be <code>null</code>.
     * @param route route to the opposite endpoint.
     * @param conn the connection.
     */
    public PoolEntry(final String id, final T route, final C conn) {
        this(id, route, conn, 0, TimeUnit.MILLISECONDS);
    }

    public String getId() {
        return this.id;
    }

    public T getRoute() {
        return this.route;
    }

    public C getConnection() {
        return this.conn;
    }

    public long getCreated() {
        return this.created;
    }

    public long getValidUnit() {
        return this.validUnit;
    }

    public Object getState() {
        return this.state;
    }

    public void setState(final Object state) {
        this.state = state;
    }

    public synchronized long getUpdated() {
        return this.updated;
    }

    public synchronized long getExpiry() {
        return this.expiry;
    }

    public synchronized void updateExpiry(final long time, final TimeUnit tunit) {
        this.updated = System.currentTimeMillis();
        final long newExpiry;
        if (time > 0) {
            newExpiry = this.updated + tunit.toMillis(time);
        } else {
            newExpiry = Long.MAX_VALUE;
        }
        this.expiry = Math.min(newExpiry, this.validUnit);
    }

    public synchronized boolean isExpired(final long now) {
        return now >= this.expiry;
    }

    /**
     * Invalidates the pool entry and closes the pooled connection associated
     * with it.
     */
    public abstract void close();

    /**
     * Returns <code>true</code> if the pool entry has been invalidated.
     */
    public abstract boolean isClosed();

    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder();
        buffer.append("[id:");
        buffer.append(this.id);
        buffer.append("][route:");
        buffer.append(this.route);
        buffer.append("][state:");
        buffer.append(this.state);
        buffer.append("]");
        return buffer.toString();
    }

}
