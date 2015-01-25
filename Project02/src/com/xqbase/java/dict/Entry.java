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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj instanceof Entry) {
            if (this.key.equals(((Entry) obj).key)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
