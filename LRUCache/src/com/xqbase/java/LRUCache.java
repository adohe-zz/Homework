package com.xqbase.java;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU Cache implementation based on HashMap and
 * doubly-linked list.
 *
 * @author Tony He
 */
public class LRUCache<T> {

    private int capacity;
    private int num;
    private Map<String, CacheNode> map;
    private CacheNode head;
    private CacheNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.num = 0;
        this.map = new HashMap<String, CacheNode>();
        this.head = new CacheNode();
        this.tail = new CacheNode();
        this.head.next = tail;
        this.tail.prev = head;
    }

    public T get(String key) {
        CacheNode node = map.get(key);
        if (null == node)
            return null;

        detachNode(node);
        attachNode(node);
        return node.item;
    }

    public void set(String key, T value) {
        CacheNode node = map.get(key);
        if (node != null) {
            detachNode(node);
            node.item = value;
            attachNode(node);
        } else {
            if (num == capacity) {
                node = tail.prev;
                detachNode(node);
                map.remove(node.key);
            } else {
                num ++;
                node = new CacheNode();
            }
            node.key = key;
            node.item = value;
            map.put(key, node);
            attachNode(node);
        }
    }

    /**
     * Detach the node from the list.
     */
    private void detachNode(CacheNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * Re-Add the node to the head of the list.
     */
    private void attachNode(CacheNode node) {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }

    private class CacheNode {
        String key;
        T item;
        CacheNode prev;
        CacheNode next;

        public CacheNode() {
        }

        public CacheNode(T item) {
            this.item = item;
        }
    }

    public static void main(String[] args) {
        LRUCache<String> cache = new LRUCache<String>(1);
        cache.set("a", "beep");
        System.out.println(cache.get("a"));
    }
}
