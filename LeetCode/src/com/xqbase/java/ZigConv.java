package com.xqbase.java;

/**
 * LeetCodeSix -- Zigzag Conversion.
 *
 * @author Tony He
 */
public class ZigConv {

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
    }

    public static String convert(String s, int numRows) {

        if (numRows <= 1 || s.length() == 0)
            return s;

        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len && i < numRows; ++i)
        {
            int index = i;
            sb.append(s.charAt(index));

            for (int k = 1; index < len; ++k)
            {
                if (i == 0 || i == numRows - 1)
                {
                    index += 2 * numRows - 2;
                } else
                {
                    if (k % 2 != 0)
                        index += 2 * (numRows - 1 - i);
                    else index += 2 * i;
                }

                if (index < len)
                {
                    sb.append(s.charAt(index));
                }
            }
        }
        return sb.toString();
    }
}
