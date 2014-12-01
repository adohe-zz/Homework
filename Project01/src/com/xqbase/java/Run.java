package com.xqbase.java;

/* Run.java */

/**
 * Run is a class used by the RunLengthEncoding and is
 * the data value of the doubly-linked list
 *
 * @author Tony He
 */
public class Run {

    private int rgb;
    private int frequency;

    public Run(int rgb, int frequency) {
        this.rgb = rgb;
        this.frequency = frequency;
    }

    public int getRgb() {
        return rgb;
    }

    public void setRgb(int rgb) {
        this.rgb = rgb;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "{" + rgb + "," + frequency + "}";
    }
}
