package com.xqbase.java.util;

import com.xqbase.java.Position;
import com.xqbase.java.Tree;
import com.xqbase.java.exceptions.BoundaryViolationException;
import com.xqbase.java.exceptions.InvalidPositionException;

/**
 * A Tree Utility class performs util methods.
 *
 * @author Tony He
 */
public class TreeUtil {

    public static <E> int depth(Tree<E> tree, Position<E> p) throws InvalidPositionException,
      BoundaryViolationException {
        if (tree.isRoot(p))
            return 0;
        else
            return 1 + depth(tree, tree.parent(p));
    }
}
