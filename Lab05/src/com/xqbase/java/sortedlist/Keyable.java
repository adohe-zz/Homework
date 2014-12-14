/* Keyable.java */

package com.xqbase.java.sortedlist;

public interface Keyable {
    public int getKey();

    public boolean lessThan(Keyable x);
}
