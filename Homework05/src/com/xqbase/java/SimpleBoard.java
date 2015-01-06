package com.xqbase.java;/* SimpleBoard.java */

/**
 * Simple class that implements an 8x8 game board with three possible values
 * for each cell:  0, 1 or 2.
 * <p/>
 * DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 */

public class SimpleBoard {

    private final static int DIMENSION = 8;
    private int[][] grid;

    /**
     * Cache the hash code of this SimpleBoard.
     */
    private int hash;

    /**
     *  Invariants:
     *  (1) grid.length == DIMENSION.
     *  (2) for all 0 <= i < DIMENSION, grid[i].length == DIMENSION.
     *  (3) for all 0 <= i, j < DIMENSION, grid[i][j] >= 0 and grid[i][j] <= 2.
     **/

    /**
     * Construct a new board in which all cells are zero.
     */

    public SimpleBoard() {
        grid = new int[DIMENSION][DIMENSION];
    }

    /**
     * Set the cell (x, y) in the board to the given value mod 3.
     *
     * @param value to which the element should be set (normally 0, 1, or 2).
     * @param x     is the x-index.
     * @param y     is the y-index.
     * @throws ArrayIndexOutOfBoundsException is thrown if an invalid index
     *                                        is given.
     */

    public void setElementAt(int x, int y, int value) {
        grid[x][y] = value % 3;
        if (grid[x][y] < 0) {
            grid[x][y] = grid[x][y] + 3;
        }
    }

    /**
     * Get the valued stored in cell (x, y).
     *
     * @param x is the x-index.
     * @param y is the y-index.
     * @return the stored value (between 0 and 2).
     * @throws ArrayIndexOutOfBoundsException is thrown if an invalid index
     *                                        is given.
     */

    public int elementAt(int x, int y) {
        return grid[x][y];
    }

    /**
     * Returns true if "this" SimpleBoard and "board" have identical values in
     * every cell.
     *
     * @param board is the second SimpleBoard.
     * @return true if the boards are equal, false otherwise.
     */

    public boolean equals(Object board) {
        if (this == board) {
            return true;
        }

        if (!(board instanceof SimpleBoard)) {
            return false;
        }
        SimpleBoard otherBoard = (SimpleBoard) board;
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (this.elementAt(i, j) != otherBoard.elementAt(i, j))
                    return false;
            }
        }

        return true;
    }

    /**
     * Returns a hash code for this SimpleBoard.
     *
     * @return a number between Integer.MIN_VALUE and Integer.MAX_VALUE.
     */

    public int hashCode() {
        int index = 0;
        int h = hash;

        if (h == 0) {
            int[][] g = grid;
            for (int i = 0; i < DIMENSION; i++) {
                for (int j = 0; j < DIMENSION; j++) {
                    h += Math.pow(3, index) * g[i][j];
                    index++;
                }
            }
        }

        return h;
    }

}
