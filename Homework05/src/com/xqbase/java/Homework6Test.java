package com.xqbase.java;/* Homework6Test.java */

import com.xqbase.java.dict.Entry;
import com.xqbase.java.dict.HashTableChained;

/**
 * Initializes a hash table, then stocks it with random SimpleBoards.
 */

public class Homework6Test {

    /**
     * Generates a random 8 x 8 SimpleBoard.
     */

    private static SimpleBoard randomBoard() {
        SimpleBoard board = new SimpleBoard();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                double fval = Math.random() * 12;
                int value = (int) fval;
                board.setElementAt(x, y, value);
            }
        }
        return board;
    }

    /**
     * Empties the given table, then inserts "numBoards" boards into the table.
     *
     * @param table     is the hash table to be initialized.
     * @param numBoards is the number of random boards to place in the table.
     */

    public static void initTable(HashTableChained table, int numBoards) {
        table.makeEmpty();
        for (int i = 0; i < numBoards; i++) {
            table.insert(randomBoard(), new Integer(i));
        }
    }

    public static double getExpectedCollisionNumber(int buckets, int keys) {
        double d = 1.0 - 1.0/buckets;
        d = Math.pow(d, keys);
        return keys - buckets + buckets * d;
    }

    /**
     * Creates a hash table and stores a certain number of SimpleBoards in it.
     * The number is 100 by default, but you can specify any number at the
     * command line.  For example:
     * <p/>
     * java Homework6Test 12000
     */

    public static void main(String[] args) {

        // Test the HashTableChained
        HashTableChained hashTable = new HashTableChained();
        if (!hashTable.isEmpty()) {
            System.out.println("HashTable should be empty!!!");
        }
        hashTable.insert("Tony", "China");
        hashTable.insert("John", "US");
        hashTable.insert("Allen", "UK");
        hashTable.insert("Bob", "UA");
        hashTable.insert("Sakula", "Ck");
        if (hashTable.size() != 5) {
            System.out.println("HashTable should just has 5 entries");
        }
        if (hashTable.find("Tony").value() != "China") {
            System.out.println("Tony belongs to China");
        }
        if (hashTable.find("John").value() != "US") {
            System.out.println("John belongs to US");
        }
        if (hashTable.find("Allen").value() != "UK") {
            System.out.println("Allen belongs to UK");
        }
        if (hashTable.find("Bob").value() != "UA") {
            System.out.println("Bob belongs to UA");
        }
        if (hashTable.find("Sakula").value() != "Ck") {
            System.out.println("Sakula belongs  to Ck");
        }
        Entry entry = hashTable.remove("Tony");
        if (entry.value() != "China") {
            System.out.println("Value should be China");
        }
        entry = hashTable.find("Tony");
        if (entry != null) {
            System.out.println("HashTable should not contain Tony");
        }
        if (hashTable.size() != 4) {
            System.out.println("HashTable should just has 4 entries");
        }
        entry = hashTable.remove("John");
        if (entry.value() != "US") {
            System.out.println("Value should be US");
        }
        entry = hashTable.find("John");
        if (entry != null) {
            System.out.println("HashTable should not contain John");
        }
        if (hashTable.size() != 3) {
            System.out.println("HashTable should just has 3 entries");
        }
        hashTable.makeEmpty();
        if (hashTable.size() != 0) {
            System.out.println("HashTable should be empty");
        }
        entry = hashTable.find("Tony");
        if (entry != null) {
            System.out.println("HashTable should not contain any entry");
        }

        System.out.println("Everything is ok...");

        int numBoards;

        if (args.length == 0) {
            numBoards = 100;
        } else {
            numBoards = Integer.parseInt(args[0]);
        }
        HashTableChained table = new HashTableChained(numBoards);
        initTable(table, numBoards);

        System.out.println("Actual Collision Count: " + table.getCollisionCount());
        System.out.println("Expected Collision Count" + getExpectedCollisionNumber(table.initCapacity, numBoards));
        // To test your hash function, add a method to your HashTableChained class
        // that counts the number of collisions--or better yet, also prints
        // a histograph of the number of entries in each bucket.  Call this method
        // from here.
    }

}
