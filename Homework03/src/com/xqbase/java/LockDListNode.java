package com.xqbase.java;

/**
 * A LockDListNode is a node in a LockDList
 *
 * @author Tony He
 */
public class LockDListNode extends DListNode {

    private boolean locked;

    public LockDListNode(Object item, LockDListNode prev, LockDListNode next) {
        super(item, prev, next);
        this.locked = false;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
