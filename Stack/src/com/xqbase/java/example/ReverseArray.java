package com.xqbase.java.example;

import com.xqbase.java.Stack;
import com.xqbase.java.impl.ArrayStack;

import java.util.Arrays;

/**
 * Reverse array using a stack.
 *
 * @author Tony He
 */
public class ReverseArray {

    private static <E> void reverse(E[] a) {
        Stack<E> stack = new ArrayStack<E>(a.length);
        for (int i = 0; i < a.length; i++)
            stack.push(a[i]);
        for (int i = 0; i < a.length; i++)
            a[i] = stack.pop();
    }

    public static void main(String[] args) {
        Integer[] a = {4, 8, 15, 16, 23, 42};
        String[] s = {"Jack", "Kate", "Hurley", "Jin", "Boone"};
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("s = " + Arrays.toString(s));
        System.out.println("Reversing...");
        reverse(a);
        reverse(s);
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("s = " + Arrays.toString(s));
    }
}
