package com.xqbase.java;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode01 -- TwoSum
 *
 * @author Tony He
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] numbers = {2, 7 , 11, 15};
        int target = 9;

        int[] indices = twoSum(numbers, target);
        System.out.println("index1=" + indices[0] + ", index2=" + indices[1]);
    }

    private static int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0)
            throw new IllegalArgumentException("");

        int[] indices = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(numbers.length);

        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i])) {
                int index = map.get(numbers[i]);
                indices[0] = index + 1;
                indices[1] = i + 1;
                break;
            } else {
                map.put(target - numbers[i], i);
            }
        }

        return indices;
    }
}
