/* Entry.java */

package com.xqbase.java.dict;

/**
 * A class for dictionary entries.
 *
 * @author Tony He
 */

public class Entry {

    protected Object key;
    protected Object value;

    public Entry() {

    }

    public Entry(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object key() {
        return key;
    }

    public Object value() {
        return value;
    }

}
