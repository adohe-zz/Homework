package com.xqbase.java;/* ListSorts.java */

import com.xqbase.java.list.LinkedQueue;
import com.xqbase.java.list.QueueEmptyException;

import java.util.Random;

public class ListSorts {

    private final static int SORTSIZE = 10;

    /**
     * makeQueueOfQueues() makes a queue of queues, each containing one item
     * of q.  Upon completion of this method, q is empty.
     *
     * @param q is a LinkedQueue of objects.
     * @return a LinkedQueue containing LinkedQueue objects, each of which
     * contains one object from q.
     */
    public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
        LinkedQueue queue = new LinkedQueue();
        try {
            while (!q.isEmpty()) {
                LinkedQueue newQueue = new LinkedQueue();
                newQueue.enqueue(q.dequeue());
                queue.enqueue(newQueue);
            }
        } catch (QueueEmptyException e) {
            System.out.println("Empty queue exception");
        }
        return queue;
    }

    /**
     * mergeSortedQueues() merges two sorted queues into a third.  On completion
     * of this method, q1 and q2 are empty, and their items have been merged
     * into the returned queue.
     *
     * @param q1 is LinkedQueue of Comparable objects, sorted from smallest
     *           to largest.
     * @param q2 is LinkedQueue of Comparable objects, sorted from smallest
     *           to largest.
     * @return a LinkedQueue containing all the Comparable objects from q1
     * and q2 (and nothing else), sorted from smallest to largest.
     */
    public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
        if (q1 == null || q1.isEmpty())
            return q2;
        if (q2 == null || q2.isEmpty())
            return q1;


        try {
            LinkedQueue q3 = new LinkedQueue();

            if (((Comparable) q2.front()).compareTo(q1.nth(q1.size())) > 0) {
                q3.append(q1);
                q3.append(q2);
                return q3;
            }
            if (((Comparable) q1.front()).compareTo(q2.nth(q2.size())) > 0) {
                q3.append(q2);
                q3.append(q1);
                return q3;
            }

            while (!q1.isEmpty() && !q2.isEmpty()) {
                Object o1 = q1.front();
                Object o2 = q2.front();

                int c = ((Comparable) o1).compareTo(o2);
                if (c < 0) {
                    q3.enqueue(q1.dequeue());
                } else if (c > 0) {
                    q3.enqueue(q2.dequeue());
                } else {
                    q3.enqueue(q1.dequeue());
                    q3.enqueue(q2.dequeue());
                }
            }

            while (!q1.isEmpty()) {
                q3.enqueue(q1.dequeue());
            }
            while (!q2.isEmpty()) {
                q3.enqueue(q2.dequeue());
            }

            return q3;
        } catch (QueueEmptyException e) {
            System.out.println("Queue is empty");
            return null;
        }
    }

    /**
     * partition() partitions qIn using the pivot item.  On completion of
     * this method, qIn is empty, and its items have been moved to qSmall,
     * qEquals, and qLarge, according to their relationship to the pivot.
     *
     * @param qIn     is a LinkedQueue of Comparable objects.
     * @param pivot   is a Comparable item used for partitioning.
     * @param qSmall  is a LinkedQueue, in which all items less than pivot
     *                will be enqueued.
     * @param qEquals is a LinkedQueue, in which all items equal to the pivot
     *                will be enqueued.
     * @param qLarge  is a LinkedQueue, in which all items greater than pivot
     *                will be enqueued.
     */
    public static void partition(LinkedQueue qIn, Comparable pivot,
                                 LinkedQueue qSmall, LinkedQueue qEquals,
                                 LinkedQueue qLarge) {
        try {
            while (!qIn.isEmpty()) {
                Object o = qIn.dequeue();
                if (pivot.compareTo(o) > 0) {
                    qSmall.enqueue(o);
                } else if (pivot.compareTo(o) == 0) {
                    qEquals.enqueue(o);
                } else {
                    qLarge.enqueue(o);
                }
            }
        } catch (QueueEmptyException e) {
            System.out.println("Queue empty exception");
        }
    }

    /**
     * mergeSort() sorts q from smallest to largest using mergesort.
     *
     * @param q is a LinkedQueue of Comparable objects.
     */
    public static void mergeSort(LinkedQueue q) {
        LinkedQueue queueOfQueues = makeQueueOfQueues(q);
        try {
            while (queueOfQueues.size() != 1) {
                LinkedQueue q1 = (LinkedQueue) queueOfQueues.dequeue();
                LinkedQueue q2 = (LinkedQueue) queueOfQueues.dequeue();
                queueOfQueues.enqueue(mergeSortedQueues(q1, q2));
            }

            q.enqueue(queueOfQueues.front());
        } catch (QueueEmptyException e) {
            System.out.println("Queue is empty");
        }
    }

    /**
     * quickSort() sorts q from smallest to largest using quicksort.
     *
     * @param q is a LinkedQueue of Comparable objects.
     */
    public static void quickSort(LinkedQueue q) {
        if (q == null || q.isEmpty())
            return;

        Random random = new Random();
        Object pivot = q.nth(random.nextInt(q.size()) + 1);

        LinkedQueue smallerQueue = new LinkedQueue();
        LinkedQueue equalQueue = new LinkedQueue();
        LinkedQueue largeQueue = new LinkedQueue();

        partition(q, (Comparable) pivot, smallerQueue, equalQueue, largeQueue);

        quickSort(smallerQueue);
        quickSort(largeQueue);

        q.append(smallerQueue);
        q.append(equalQueue);
        q.append(largeQueue);
    }

    /**
     * makeRandom() builds a LinkedQueue of the indicated size containing
     * Integer items.  The items are randomly chosen between 0 and size - 1.
     *
     * @param size is the size of the resulting LinkedQueue.
     */
    public static LinkedQueue makeRandom(int size) {
        LinkedQueue q = new LinkedQueue();
        for (int i = 0; i < size; i++) {
            q.enqueue(new Integer((int) (size * Math.random())));
        }
        return q;
    }

    /**
     * main() performs some tests on mergesort and quicksort.  Feel free to add
     * more tests of your own to make sure your algorithms works on boundary
     * cases.  Your test code will not be graded.
     */
    public static void main(String[] args) {

        /**
         * Test mergeSort&quickSort
         */
        LinkedQueue q = makeRandom(SORTSIZE);
        System.out.println(q.toString());
        mergeSort(q);
        System.out.println(q.toString());

        q = makeRandom(SORTSIZE);
        System.out.println(q.toString());
        quickSort(q);
        System.out.println(q.toString());

        q = makeRandom(1);
        System.out.println(q.toString());
        mergeSort(q);
        System.out.println(q.toString());

        q = makeRandom(1);
        System.out.println(q.toString());
        quickSort(q);
        System.out.println(q.toString());

        q = makeRandom(0);
        System.out.println(q.toString());
        mergeSort(q);
        System.out.println(q.toString());

        q = makeRandom(0);
        System.out.println(q.toString());
        quickSort(q);
        System.out.println(q.toString());

        /**
         * Performance testing.
         */
        Timer stopWatch = new Timer();
        q = makeRandom(SORTSIZE);
        stopWatch.start();
        mergeSort(q);
        stopWatch.stop();
        System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                           stopWatch.elapsed() + " msec.");

        stopWatch.reset();
        q = makeRandom(SORTSIZE);
        stopWatch.start();
        quickSort(q);
        stopWatch.stop();
        System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                           stopWatch.elapsed() + " msec.");

        /**
         * The quickSort&mergeSort we implement are both stable. Since
         * there is no equal items position change.
         */
    }

}
