package com.xqbase.java;

/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes
 *  a PixImage object.  Descriptions of the methods you must implement appear
 *  below.  They include constructors of the form
 *
 *      public RunLengthEncoding(int width, int height);
 *      public RunLengthEncoding(int width, int height, int[] red, int[] green,
 *                               int[] blue, int[] runLengths) {
 *      public RunLengthEncoding(PixImage image) {
 *
 *  that create a run-length encoding of a PixImage having the specified width
 *  and height.
 *
 *  The first constructor creates a run-length encoding of a PixImage in which
 *  every pixel is black.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts a PixImage object into a run-length encoding of that image.
 *
 *  See the README file accompanying this project for additional details.
 */

import java.util.Iterator;

public class RunLengthEncoding implements Iterable {

    /**
     *  Define any variables associated with a RunLengthEncoding object here.
     *  These variables MUST be private.
     */
    private DList list = new DList();
    private final int width;
    private final int height;

    /**
     *  The following methods are required for Part II.
     */

    /**
     *  RunLengthEncoding() (with two parameters) constructs a run-length
     *  encoding of a black PixImage of the specified width and height, in which
     *  every pixel has red, green, and blue intensities of zero.
     *
     *  @param width the width of the image.
     *  @param height the height of the image.
     */

    public RunLengthEncoding(int width, int height) {
        this.width = width;
        this.height = height;
        list.insertBack(new Run(0, width * height));
    }

    /**
     *  RunLengthEncoding() (with six parameters) constructs a run-length
     *  encoding of a PixImage of the specified width and height.  The runs of
     *  the run-length encoding are taken from four input arrays of equal length.
     *  Run i has length runLengths[i] and RGB intensities red[i], green[i], and
     *  blue[i].
     *
     *  @param width the width of the image.
     *  @param height the height of the image.
     *  @param red is an array that specifies the red intensity of each run.
     *  @param green is an array that specifies the green intensity of each run.
     *  @param blue is an array that specifies the blue intensity of each run.
     *  @param runLengths is an array that specifies the length of each run.
     *
     *  NOTE:  All four input arrays should have the same length (not zero).
     *  All pixel intensities in the first three arrays should be in the range
     *  0...255.  The sum of all the elements of the runLengths array should be
     *  width * height.  (Feel free to quit with an error message if any of these
     *  conditions are not met--though we won't be testing that.)
     */

    public RunLengthEncoding(int width, int height, int[] red, int[] green,
                             int[] blue, int[] runLengths) {
        this.width = width;
        this.height = height;
        for (int i = 0; i < runLengths.length; i++) {
            list.insertBack(new Run(PixImage.rgb((short)red[i], (short)green[i], (short)blue[i]), runLengths[i]));
        }
    }

    /**
     *  getWidth() returns the width of the image that this run-length encoding
     *  represents.
     *
     *  @return the width of the image that this run-length encoding represents.
     */

    public int getWidth() {
        return this.width;
    }

    /**
     *  getHeight() returns the height of the image that this run-length encoding
     *  represents.
     *
     *  @return the height of the image that this run-length encoding represents.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     *  iterator() returns a newly created RunIterator that can iterate through
     *  the runs of this RunLengthEncoding.
     *
     *  @return a newly created RunIterator object set to the first run of this
     *  RunLengthEncoding.
     */
    public RunIterator iterator() {
        return new RunIterator(new DListNode(null, null, list.front()));
    }

    /**
     *  toPixImage() converts a run-length encoding of an image into a PixImage
     *  object.
     *
     *  @return the PixImage that this RunLengthEncoding encodes.
     */
    public PixImage toPixImage() {
        PixImage image = new PixImage(this.width, this.height);

        RunIterator iterator = iterator();
        int x = 0;
        int y = 0;
        int index = 0;
        while (iterator.hasNext()) {
            int[] run = iterator.next();
            for (int i = 0; i < run[0]; i++) {
                x = getX(index);
                y = getY(index);
                image.setPixel(x, y, (short)run[1], (short)run[2], (short)run[3]);
                index ++;
            }
        }

        return image;
    }

    private int getX(int index) {
        return index % this.width;
    }

    private int getY(int index) {
        return index / this.width;
    }

    /**
     *  toString() returns a String representation of this RunLengthEncoding.
     *
     *  This method isn't required, but it should be very useful to you when
     *  you're debugging your code.  It's up to you how you represent
     *  a RunLengthEncoding as a String.
     *
     *  @return a String representation of this RunLengthEncoding.
     */
    public String toString() {
        RunIterator iterator = iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            int[] run = iterator.next();
            sb.append("Frequency: ").append(run[0]).append(",")
                    .append("RGB:{").append(run[1]).append(",").append(run[2])
                    .append(",").append(run[3]).append("}").append("-->");
        }

        return sb.toString();
    }


    /**
     *  The following methods are required for Part III.
     */

    /**
     *  RunLengthEncoding() (with one parameter) is a constructor that creates
     *  a run-length encoding of a specified PixImage.
     *
     *  Note that you must encode the image in row-major format, i.e., the second
     *  pixel should be (1, 0) and not (0, 1).
     *
     *  @param image is the PixImage to run-length encode.
     */
    public RunLengthEncoding(PixImage image) {
        this.width = image.getWidth();
        this.height = image.getHeight();

        for (int j = 0; j < this.height; j++) {
            for (int i = 0; i < this.width; i++) {

                int rgb = ImageUtils.rgb(image.getRed(i, j), image.getGreen(i, j), image.getBlue(i, j));
                if (list.isEmpty()) {
                    list.insertBack(new Run(rgb, 1));
                } else {
                    DListNode back = list.back();
                    if (back.run.rgb == rgb) {
                        // update the back node
                        list.insertBefore(new Run(rgb, back.run.frequency + 1), back);
                        list.remove(back);
                    } else {
                        list.insertBack(new Run(rgb, 1));
                    }
                }
            }
        }

        check();
    }

    /**
     *  check() walks through the run-length encoding and prints an error message
     *  if two consecutive runs have the same RGB intensities, or if the sum of
     *  all run lengths does not equal the number of pixels in the image.
     */
    public void check() {
        System.out.println(this);
        int sum = 0;
        DListNode node = list.front();

        while (node != null) {
            Run currRun = node.run;
            DListNode next = node.next;
            int frequency = currRun.frequency;
            if (frequency < 1) {
                System.err.println("Frequency is less than 1");
            }
            if (next != null && (node.run.rgb == next.run.rgb)) {
                System.err.println("Two consecutive runs have the same RGB intensities");
            }
            sum += frequency;

            node = node.next;
        }

        if (sum != this.width * this.height) {
            System.err.println("The sum of all run lengths does not equal the number of pixels in the image");
        }
    }


    /**
     *  The following method is required for Part IV.
     */

    /**
     *  setPixel() modifies this run-length encoding so that the specified color
     *  is stored at the given (x, y) coordinates.  The old pixel value at that
     *  coordinate should be overwritten and all others should remain the same.
     *  The updated run-length encoding should be compressed as much as possible;
     *  there should not be two consecutive runs with exactly the same RGB color.
     *
     *  @param x the x-coordinate of the pixel to modify.
     *  @param y the y-coordinate of the pixel to modify.
     *  @param red the new red intensity to store at coordinate (x, y).
     *  @param green the new green intensity to store at coordinate (x, y).
     *  @param blue the new blue intensity to store at coordinate (x, y).
     */
    public void setPixel(int x, int y, short red, short green, short blue) {
        // first find the loc of the pixel
        int loc = x + y * this.width;
        int total = 0;

        // find the right node within the list
        DListNode node = list.front();
        while (node != null) {
            Run run = node.run;
            total += run.frequency;
            if (total > loc)
                break;

            node = node.next;
        }

        Run currRun = node.run;
        int currRgb = currRun.rgb;
        int currFrequency = currRun.frequency;
        int newRGB = ImageUtils.rgb(red, green, blue);
        if (currRgb != newRGB) {
            // need to update the doubly-liked list
            if (currFrequency == 1) {
                DListNode prev = node.prev;
                DListNode next = node.next;

                if (prev == null && next == null) {
                    // by default impossible
                } else if (prev == null) {
                    Run nextRun = next.run;
                    if (newRGB == nextRun.rgb) {
                        list.insertAfter(new Run(newRGB, nextRun.frequency + 1), node);
                        list.remove(next);
                    } else {
                        list.insertAfter(new Run(newRGB, 1), node);
                    }
                    list.remove(node);
                } else if (next == null) {
                    Run prevRun = prev.run;
                    if (newRGB == prevRun.rgb) {
                        list.insertBefore(new Run(newRGB, prevRun.frequency + 1), node);
                        list.remove(prev);
                    } else {
                        list.insertBefore(new Run(newRGB, 1), node);
                    }
                    list.remove(node);
                } else {
                    Run prevRun = prev.run;
                    Run nextRun = next.run;

                    if (prevRun.rgb == newRGB && nextRun.rgb == newRGB) {
                        list.insertBefore(new Run(newRGB, prevRun.frequency + nextRun.frequency + 1), prev);
                        list.remove(prev);
                        list.remove(node);
                        list.remove(next);
                    } else if (prevRun.rgb == newRGB) {
                        list.insertBefore(new Run(newRGB, prevRun.frequency + 1), prev);
                        list.remove(prev);
                        list.remove(node);
                    } else if (nextRun.rgb == newRGB) {
                        list.insertAfter(new Run(newRGB, nextRun.frequency + 1), next);
                        list.remove(node);
                        list.remove(next);
                    } else {
                        list.insertBefore(new Run(newRGB, 1), node);
                        list.remove(node);
                    }
                }
            } else {
                if (total - currFrequency == loc) {
                    // the pixel is at the run head
                    DListNode prev = node.prev;
                    if (prev == null) {
                        // update the list head
                        list.insertFront(new Run(newRGB, 1));
                    } else {
                        if (prev.run.rgb == newRGB) {
                            // merge with prev
                            list.remove(prev);
                            list.insertBefore(new Run(newRGB, prev.run.frequency + 1), node);
                        } else {
                            list.insertBefore(new Run(newRGB, 1), node);
                        }
                    }
                    int frequency = currFrequency - 1;
                    node.run.frequency = frequency;
                    if (frequency == 0)
                        list.remove(node);
                } else if (total - 1 == loc && currFrequency != 1) {
                    // the pixel is at the run tail
                    DListNode next = node.next;
                    if (next == null) {
                        // update the list tail
                        list.insertBack(new Run(newRGB, 1));
                    } else {
                        if (next.run.rgb == newRGB) {
                            // merge with the next
                            list.remove(node.next);
                            list.insertAfter(new Run(newRGB, next.run.frequency + 1), node);
                        } else {
                            list.insertAfter(new Run(newRGB, 1), node);
                        }
                    }
                    int frequency = currFrequency - 1;
                    node.run.frequency = frequency;
                    if (frequency == 0)
                        list.remove(node);
                } else {
                    // in the middle of the run
                    // we need to divide the node
                    int i = loc - (total - currFrequency);
                    int j = currFrequency - i - 1;
                    list.insertBefore(new Run(currRgb, i), node);
                    list.insertAfter(new Run(currRgb, j), node);
                    list.remove(node);
                    DListNode prev = node.prev;
                    list.insertAfter(new Run(newRGB, 1), prev);
                }
            }
        }
    }


    /**
     * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
     * You are welcome to add tests, though.  Methods below this point will not
     * be tested.  This is not the autograder, which will be provided separately.
     */


    /**
     * doTest() checks whether the condition is true and prints the given error
     * message if it is not.
     *
     * @param b the condition to check.
     * @param msg the error message to print if the condition is false.
     */
    private static void doTest(boolean b, String msg) {
        if (b) {
            System.out.println("Good.");
        } else {
            System.err.println(msg);
        }
    }

    /**
     * array2PixImage() converts a 2D array of grayscale intensities to
     * a grayscale PixImage.
     *
     * @param pixels a 2D array of grayscale intensities in the range 0...255.
     * @return a new PixImage whose red, green, and blue values are equal to
     * the input grayscale intensities.
     */
    private static PixImage array2PixImage(int[][] pixels) {
        int width = pixels.length;
        int height = pixels[0].length;
        PixImage image = new PixImage(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                        (short) pixels[x][y]);
            }
        }

        return image;
    }

    /**
     * setAndCheckRLE() sets the given coordinate in the given run-length
     * encoding to the given value and then checks whether the resulting
     * run-length encoding is correct.
     *
     * @param rle the run-length encoding to modify.
     * @param x the x-coordinate to set.
     * @param y the y-coordinate to set.
     * @param intensity the grayscale intensity to assign to pixel (x, y).
     */
    private static void setAndCheckRLE(RunLengthEncoding rle,
                                       int x, int y, int intensity) {
        rle.setPixel(x, y,
                (short) intensity, (short) intensity, (short) intensity);
        rle.check();
    }

    /**
     * main() runs a series of tests of the run-length encoding code.
     */
    public static void main(String[] args) {
        // Be forwarned that when you write arrays directly in Java as below,
        // each "row" of text is a column of your image--the numbers get
        // transposed.
        PixImage image1 = array2PixImage(new int[][] { { 0, 3, 6 },
                { 1, 4, 7 },
                { 2, 5, 8 } });

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                "on a 3x3 image.  Input image:");
        System.out.println(image1);
        RunLengthEncoding rle1 = new RunLengthEncoding(image1);
        rle1.check();
        System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
        doTest(rle1.getWidth() == 3 && rle1.getHeight() == 3,
                "RLE1 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 3x3 encoding.");
        doTest(image1.equals(rle1.toPixImage()),
                "image1 -> RLE1 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 0, 0, 42);
        image1.setPixel(0, 0, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[0][0] = 42 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 1, 0, 42);
        image1.setPixel(1, 0, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[1][0] = 42 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 0, 1, 2);
        image1.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[0][1] = 2 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 0, 0, 0);
        image1.setPixel(0, 0, (short) 0, (short) 0, (short) 0);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[0][0] = 0 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 2, 2, 7);
        image1.setPixel(2, 2, (short) 7, (short) 7, (short) 7);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[2][2] = 7 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 2, 2, 42);
        image1.setPixel(2, 2, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[2][2] = 42 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 1, 2, 42);
        image1.setPixel(1, 2, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[1][2] = 42 fails.");


        PixImage image2 = array2PixImage(new int[][] { { 2, 3, 5 },
                { 2, 4, 5 },
                { 3, 4, 6 } });

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                "on another 3x3 image.  Input image:");
        System.out.print(image2);
        RunLengthEncoding rle2 = new RunLengthEncoding(image2);
        rle2.check();
        System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
        doTest(rle2.getWidth() == 3 && rle2.getHeight() == 3,
                "RLE2 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 3x3 encoding.");
        doTest(rle2.toPixImage().equals(image2),
                "image2 -> RLE2 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle2, 0, 1, 2);
        image2.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
        doTest(rle2.toPixImage().equals(image2),
                "Setting RLE2[0][1] = 2 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle2, 2, 0, 2);
        image2.setPixel(2, 0, (short) 2, (short) 2, (short) 2);
        doTest(rle2.toPixImage().equals(image2),
                "Setting RLE2[2][0] = 2 fails.");


        PixImage image3 = array2PixImage(new int[][] { { 0, 5 },
                { 1, 6 },
                { 2, 7 },
                { 3, 8 },
                { 4, 9 } });

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                "on a 5x2 image.  Input image:");
        System.out.print(image3);
        RunLengthEncoding rle3 = new RunLengthEncoding(image3);
        rle3.check();
        System.out.println("Testing getWidth/getHeight on a 5x2 encoding.");
        doTest(rle3.getWidth() == 5 && rle3.getHeight() == 2,
                "RLE3 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 5x2 encoding.");
        doTest(rle3.toPixImage().equals(image3),
                "image3 -> RLE3 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 5x2 encoding.");
        setAndCheckRLE(rle3, 4, 0, 6);
        image3.setPixel(4, 0, (short) 6, (short) 6, (short) 6);
        doTest(rle3.toPixImage().equals(image3),
                "Setting RLE3[4][0] = 6 fails.");

        System.out.println("Testing setPixel() on a 5x2 encoding.");
        setAndCheckRLE(rle3, 0, 1, 6);
        image3.setPixel(0, 1, (short) 6, (short) 6, (short) 6);
        doTest(rle3.toPixImage().equals(image3),
                "Setting RLE3[0][1] = 6 fails.");

        System.out.println("Testing setPixel() on a 5x2 encoding.");
        setAndCheckRLE(rle3, 0, 0, 1);
        image3.setPixel(0, 0, (short) 1, (short) 1, (short) 1);
        doTest(rle3.toPixImage().equals(image3),
                "Setting RLE3[0][0] = 1 fails.");


        PixImage image4 = array2PixImage(new int[][] { { 0, 3 },
                { 1, 4 },
                { 2, 5 } });

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                "on a 3x2 image.  Input image:");
        System.out.print(image4);
        RunLengthEncoding rle4 = new RunLengthEncoding(image4);
        rle4.check();
        System.out.println("Testing getWidth/getHeight on a 3x2 encoding.");
        doTest(rle4.getWidth() == 3 && rle4.getHeight() == 2,
                "RLE4 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 3x2 encoding.");
        doTest(rle4.toPixImage().equals(image4),
                "image4 -> RLE4 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 3x2 encoding.");
        setAndCheckRLE(rle4, 2, 0, 0);
        image4.setPixel(2, 0, (short) 0, (short) 0, (short) 0);
        doTest(rle4.toPixImage().equals(image4),
                "Setting RLE4[2][0] = 0 fails.");

        System.out.println("Testing setPixel() on a 3x2 encoding.");
        setAndCheckRLE(rle4, 1, 0, 0);
        image4.setPixel(1, 0, (short) 0, (short) 0, (short) 0);
        doTest(rle4.toPixImage().equals(image4),
                "Setting RLE4[1][0] = 0 fails.");

        System.out.println("Testing setPixel() on a 3x2 encoding.");
        setAndCheckRLE(rle4, 1, 0, 1);
        image4.setPixel(1, 0, (short) 1, (short) 1, (short) 1);
        doTest(rle4.toPixImage().equals(image4),
                "Setting RLE4[1][0] = 1 fails.");
    }
}