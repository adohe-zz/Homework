package com.xqbase.java.util;

import com.xqbase.java.Position;
import com.xqbase.java.Tree;
import com.xqbase.java.Visitor;
import com.xqbase.java.exceptions.BoundaryViolationException;
import com.xqbase.java.exceptions.InvalidPositionException;

/**
 * A Tree Utility class performs util methods.
 *
 * @author Tony He
 */
public class TreeUtil {

    /**
     * Computes the depth of a node p in a Tree tree.
     *
     * O(dv) where dv denotes the depth of the node v in the tree.
     */
    public static <E> int depth(Tree<E> tree, Position<E> p) throws InvalidPositionException,
      BoundaryViolationException {
        if (tree.isRoot(p))
            return 0;
        else
            return 1 + depth(tree, tree.parent(p));
    }

    /**
     * Computes the height of the subtree of Tree tree rooted at a node p.
     * O(n) when called on the root, where n is the number of nodes in the tree.
     */
    public static <E> int height(Tree<E> tree, Position<E> p) throws InvalidPositionException {
        if (tree.isLeaf(p)) {
            return 0;
        } else {
            int h = 0;
            Iterable<Position<E>> children = tree.children(p);
            for (Position<E> position : children)
                h = Math.max(h, height(tree, position));

            return 1 + h;
        }
    }

    /**
     * Implements the pre-order traversal of the subtree rooted at
     * Position p.
     *
     * O(n)
     */
    public static <E> void preOrder(Tree<E> tree, Position<E> p, Visitor<E> v) {
        v.visit(p);
        Iterable<Position<E>> children = tree.children(p);
        for (Position<E> child : children)
            preOrder(tree, child, v);
    }
}
