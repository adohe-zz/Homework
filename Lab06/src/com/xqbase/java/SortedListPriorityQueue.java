package com.xqbase.java;

import com.xqbase.java.list.NodePositionList;
import com.xqbase.java.list.PositionList;

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
        return null;
    }

    @Override
    public Entry<K, V> removeMin() {
        return null;
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
}
