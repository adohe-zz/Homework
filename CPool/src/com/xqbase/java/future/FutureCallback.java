package com.xqbase.java.future;

/**
 * A callback interface that gets invoked upon completion of
 * a {@link java.util.concurrent.Future}.
 *
 * @param <T> the future result type returned by this callback.
 * @since 4.2
 */
public interface FutureCallback<T> {

    void completed(T result);

    void failed(Exception ex);

    void cancelled();

}