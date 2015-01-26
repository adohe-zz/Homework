package com.xqbase.java.queue;


import com.xqbase.java.queue.list.NodePositionList;
import com.xqbase.java.queue.list.Position;
import com.xqbase.java.queue.list.PositionList;

import java.util.Comparator;

public class SortedListPriorityQueue<K, V> implements PriorityQueue<K, V>{

    private PositionList<Entry<K, V>> entries;
    private Comparator<K> c;

    public SortedListPriorityQueue() {
        entries = new NodePositionList<Entry<K, V>>();
        c = new DefaultComparator<K>();
    }

    public SortedListPriorityQueue(Comparator<K> comparator) {
        entries = new NodePositionList<Entry<K, V>>();
        c = comparator;
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    @Override
    public Entry<K, V> min() {
        if (isEmpty())
            return null;
        return entries.first().element();
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
        checkKey(key);
        Entry<K, V> entry = new MyEntry<K, V>(key, value);
        insertEntry(entry);
        return entry;
    }

    /**
     * Inserts the new entry into the sorted list
     * and this takes O(n) time
     */
    private void insertEntry(Entry<K, V> entry) {
        if (entries.isEmpty()) {
            entries.addFirst(entry);
        } else if (c.compare(entry.getKey(), entries.last().element().getKey()) > 0) {
            entries.addLast(entry);
        } else {
            Position<Entry<K, V>> curr = entries.first();
            while (c.compare(entry.getKey(), curr.element().getKey()) > 0) {
                curr = entries.next(curr);
            }
            entries.addBefore(curr, entry);
        }
    }

    private void checkKey(K key) {
        // default do nothing
    }

    @Override
    public Entry<K, V> removeMin() {
        if (entries.isEmpty())
            return null;
        return entries.remove(entries.first());
    }

    protected static class MyEntry<K, V> implements Entry<K, V> {

        protected K key;
        protected V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 8, 0, 1, 5, 9};
        SortedListPriorityQueue<Integer, Integer> queue = new SortedListPriorityQueue<Integer, Integer>();
        for (int anArr : arr) {
            queue.insert(anArr, anArr);
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.removeMin().getValue());
        }
    }
}
