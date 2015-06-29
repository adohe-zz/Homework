package com.xqbase.java;

/**
 * LeetCodeSeven -- Reverse Int.
 *
 * @author Tony He
 */
public class ReverseInt {

    public static void main(String[] args) {
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(0));
        System.out.println(reverse(1534236469));
    }

    public static int reverse(int x) {
        long y = 0;
        while (x != 0) {
            y = y * 10 + x%10;
            if (y > Integer.MAX_VALUE || y < Integer.MIN_VALUE)
                return 0;
            x = x/10;
        }
        return Long.valueOf(y).intValue();
    }
}
