package com.xqbase.java;

/* Run.java */

/**
 * Run is a class used by the RunLengthEncoding and is
 * the data value of the doubly-linked list
 *
 * @author Tony He
 */
public class Run {

    public int rgb;
    public int frequency;

    public Run(int rgb, int frequency) {
        this.rgb = rgb;
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "{" + rgb + "," + frequency + "}";
    }
}
