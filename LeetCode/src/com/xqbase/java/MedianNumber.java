package com.xqbase.java;

/**
 * LeetCodeFour -- find the median of two sorted array.
 *
 * @author Tony He
 */
public class MedianNumber {


    public static void main(String[] args) {

        int a[] = {5, 8, 9};
        int b[] = {1, 2, 15};

        System.out.println(findKthElement(a, b, 3, 0, 2, 0, 2));
        System.out.println(findMedianOfSortedArray(a, b));
    }


    private static double findMedianOfSortedArray(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        if ((m + n) % 2 == 0) {
            return (findKthElement(nums1, nums2, (m + n)/2, 0, m - 1, 0, n - 1) +
                    findKthElement(nums1, nums2, (m + n)/2 - 1, 0, m - 1, 0, n - 1)) * 0.5;
        } else {
            return findKthElement(nums1, nums2, (m + n)/2, 0, m - 1, 0, n - 1);
        }
    }

    // find the number located at k index.
    private static int findKthElement(int[] a, int[] b, int k, int aStart, int aEnd,
                            int bStart, int bEnd) {

        int aLen = aEnd - aStart + 1;
        int bLen = bEnd - bStart + 1;

        if (aLen == 0)
            return b[bStart + k];

        if (bLen == 0)
            return a[aStart + k];

        if (k == 0)
            return a[aStart] < b[bStart] ? a[aStart] : b[bStart];

        int aMid = aLen * k / (aLen + bLen);
        int bMid = k - aMid - 1;

        aMid = aMid + aStart;
        bMid = bMid + bStart;

        if (a[aMid] < b[bMid]) {
            k = k - (aMid - aStart + 1);
            bEnd = bMid;
            aStart = aMid + 1;
        } else {
            k = k - (bMid - bStart + 1);
            aEnd = aMid;
            bStart = bMid + 1;
        }

        return findKthElement(a, b, k, aStart, aEnd, bStart, bEnd);
    }
}
