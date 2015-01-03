/* Tree234Node.java */

package com.xqbase.java;

/**
 * A Tree234Node is a node in a 2-3-4 tree (Tree234 class).
 * <p/>
 * DO NOT CHANGE ANYTHING IN THIS FILE.
 * You may add helper methods and additional constructors, though.
 */
class Tree234Node {

    /**
     * keys is the number of keys in this node.  Always 1, 2, or 3.
     * key1 through key3 are the keys of this node.  If keys == 1, the value
     * of key2 doesn't matter.  If keys < 3, the value of key3 doesn't matter.
     * parent is this node's parent; null if this is the root.
     * child1 through child4 are the children of this node.  If this is a leaf
     * node, they must all be set to null.  If this node has no third and/or
     * fourth child, child3 and/or child4 must be set to null.
     */
    int keys;
    int key1;
    int key2;
    int key3;
    Tree234Node parent;
    Tree234Node child1;
    Tree234Node child2;
    Tree234Node child3;
    Tree234Node child4;

    Tree234Node() {
    }

    Tree234Node(Tree234Node p, int key) {
        keys = 1;
        key1 = key;
        parent = p;
        child1 = null;
        child2 = null;
        child3 = null;
        child4 = null;
    }

    /**
     * toString() recursively prints this Tree234Node and its descendants as
     * a String.  Each node is printed in the form such as (for a 3-key node)
     * <p/>
     * (child1)key1(child2)key2(child3)key3(child4)
     * <p/>
     * where each child is a recursive call to toString, and null children
     * are printed as a space with no parentheses.  Here's an example.
     * ((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))
     * <p/>
     * DO NOT CHANGE THIS METHOD.
     */
    public String toString() {
        String s = "";

        if (child1 != null) {
            s = "(" + child1.toString() + ")";
        }
        s = s + key1;
        if (child2 != null) {
            s = s + "(" + child2.toString() + ")";
        } else if (keys > 1) {
            s = s + " ";
        }
        if (keys > 1) {
            s = s + key2;
            if (child3 != null) {
                s = s + "(" + child3.toString() + ")";
            } else if (keys > 2) {
                s = s + " ";
            }
        }
        if (keys > 2) {
            s = s + key3;
            if (child4 != null) {
                s = s + "(" + child4.toString() + ")";
            }
        }
        return s;
    }

    /**
     * printSubtree() recursively prints this Tree234Node and its descendants as
     * a tree (albeit sideways).
     * <p/>
     * You're welcome to change this method if you like.  It won't be tested.
     */
    public void printSubtree(int spaces) {
        if (child4 != null) {
            child4.printSubtree(spaces + 5);
        }
        if (keys == 3) {
            for (int i = 0; i < spaces; i++) {
                System.out.print(" ");
            }
            System.out.println(key3);
        }

        if (child3 != null) {
            child3.printSubtree(spaces + 5);
        }
        if (keys > 1) {
            for (int i = 0; i < spaces; i++) {
                System.out.print(" ");
            }
            System.out.println(key2);
        }

        if (child2 != null) {
            child2.printSubtree(spaces + 5);
        }
        for (int i = 0; i < spaces; i++) {
            System.out.print(" ");
        }
        System.out.println(key1);

        if (child1 != null) {
            child1.printSubtree(spaces + 5);
        }
    }


    /**
     * Inserts a new key.
     * @param key
     */
    private void insert(int key) {
        // need split this node.
        if (keys == 4) {
            split();

            if (parent != null) {
                parent.insert(key);
                return;
            }
        }

        if (isLeafNode()) {
            addKeyToSelf(key);
        } else {
            addKeyToChildren(key);
        }
    }

    /**
     * Splits this node.
     */
    private void split() {
        if (isRootNode()) {
            splitRootNode();
        } else {
            splitNonRootNode();
        }
    }

    /**
     * Splits the node in case the node is the root node.
     */
    private void splitRootNode() {
        Tree234Node newChild1 = new Tree234Node();
        Tree234Node newChild2 = new Tree234Node();

        newChild1.key1 = key1;
        newChild1.key1 = 1;
        newChild1.addChildNode(child1, 1);
        newChild1.addChildNode(child2, 2);
        addChildNode(newChild1, 1);

        newChild2.key1 = key3;
        newChild2.keys = 1;
        newChild2.addChildNode(child3, 1);
        newChild2.addChildNode(child4, 2);
        addChildNode(newChild2, 2);

        key1 = key2;
        keys = 1;
        child3 = null;
        child4 = null;
    }

    /**
     * Splits the non-root node.
     */
    private void splitNonRootNode() {

    }

    /**
     * Whether the node is the root node of not.
     */
    private boolean isRootNode() {
        return parent == null;
    }

    /**
     * Whether the node is leaf node or not.
     */
    private boolean isLeafNode() {
        if (child1 != null || child2 != null || child3 != null || child4 != null)
            return false;
        return true;
    }

    /**
     * Inserts this key to the node itself.(the node is a non-full leaf node)
     */
    private void addKeyToSelf(int key) {
        if (key < key1) {
            key3 = key2;
            key2 = key1;
            key1 = key;
        } else if (key == key1) {
            // do nothing.
            return;
        } else if (key < key2) {
            key3 = key2;
            key2 = key;
        } else if (key == key2) {
            // do nothing.
            return;
        } else {
            key3 = key;
        }
    }

    /**
     * Inserts this key to children nodes.
     */
    private void addKeyToChildren(int key) {
        Tree234Node node = null;
        int index = 0;
        if (key < key1) {
            node = child1;
            index = 1;
        } else if (key == key1) {
            // do nothing
            return;
        } else if ((keys == 1) || (key < key2)) {
            node = child2;
            index = 2;
        } else if (key == key2) {
            // do nothing
            return;
        } else if ((keys == 2) || (key < key3)) {
            node = child3;
            index = 3;
        } else if (key == key3) {
            // do nothing
            return;
        } else {
            node = child4;
            index = 4;
        }

        if (node == null) {
            node = new Tree234Node();
            node.addKeyToSelf(key);
            addChildNode(node, index);
        } else {
            node.insert(key);
        }
    }

    /**
     * Adds a new child node.
     */
    private void addChildNode(Tree234Node childNode, int index) {
        switch (index) {
            case 1:
                child1 = childNode;
                break;
            case 2:
                child2 = childNode;
                break;
            case 3:
                child3 = childNode;
                break;
            case 4:
                child4 = childNode;
                break;
        }

        if (childNode != null) {
            childNode.parent = this;
        }
    }
}
