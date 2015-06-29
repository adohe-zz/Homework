package com.xqbase.java;

/**
 * LeetCodeEleven -- Container with most water.
 *
 * @author Tony He
 */
public class Container {

    public int maxArea(int[] height) {
        // first find the highest number
        int max = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i] > height[max]) {
                max = i;
            }
        }


        return 0;
    }

    public static void main(String[] args) {

    }
}
