/* Kruskal.java */

package com.xqbase.java.graphalg;


import com.xqbase.java.dict.HashTable;
import com.xqbase.java.graph.Neighbors;
import com.xqbase.java.graph.WUGraph;
import com.xqbase.java.queue.SortedListPriorityQueue;
import com.xqbase.java.set.DisjointSets;

import java.util.Comparator;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

    /**
     * minSpanTree() returns a WUGraph that represents the minimum spanning tree
     * of the WUGraph g.  The original WUGraph g is NOT changed.
     *
     * @param g The weighted, undirected graph whose MST we want to compute.
     * @return A newly constructed WUGraph representing the MST of g.
     */
    public static WUGraph minSpanTree(WUGraph g) {
        // First create a newly empty WUGraph instance
        WUGraph t = new WUGraph();
        // Add all the vertices of WUGraph g to the t
        HashTable edgeTable = new HashTable(g.edgeCount());
        HashTable vertexTable = new HashTable(g.vertexCount());
        SortedListPriorityQueue<Integer, Edge> edgeQueue = new SortedListPriorityQueue<Integer, Edge>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        Object[] vertices = g.getVertices();
        int i = 0;
        for (Object vertex : vertices) {
            t.addVertex(vertex);
            vertexTable.insert(vertex, i ++);
            Neighbors neighbors = g.getNeighbors(vertex);
            Object[] ns = neighbors.neighborList;
            for (Object n : ns) {
                VertexPair key = new VertexPair(vertex, n);
                if (!edgeTable.contains(key)) {
                    int weight = g.weight(vertex, n);
                    Edge edge = new Edge(vertex, n, weight);
                    edgeTable.insert(key, edge);
                    edgeQueue.insert(g.weight(vertex, n), edge);
                }
            }
        }

        DisjointSets disjointSets = new DisjointSets(vertices.length);
        while (!edgeQueue.isEmpty()) {
            Edge edge = edgeQueue.removeMin().getValue();
            int i1 = (Integer) vertexTable.find(edge.origin).value();
            int i2 = (Integer) vertexTable.find(edge.dest).value();
            if (disjointSets.find(i1) != disjointSets.find(i2)) {
                t.addEdge(edge.origin, edge.dest, edge.weight);
                disjointSets.union(i1, i2);
            }
        }

        return t;
    }

    private static class Edge {

        private Object origin;
        private Object dest;
        private int weight;

        public Edge(Object origin, Object dest, int weight) {
            this.origin = origin;
            this.dest = dest;
            this.weight = weight;
        }
    }

    private static class VertexPair {
        protected Object object1;
        protected Object object2;

        protected VertexPair(Object o1, Object o2) {
            object1 = o1;
            object2 = o2;
        }

        /**
         * hashCode() returns a hashCode equal to the sum of the hashCodes of each
         * of the two objects of the pair, so that the order of the objects will
         * not affect the hashCode.  Self-edges are treated differently:  we don't
         * add an object's hashCode to itself, since the result would always be even.
         * We add one to the hashCode so that a self-edge will not collide with the
         * object itself if vertices and edges are stored in the same hash table.
         */
        public int hashCode() {
            if (object1.equals(object2)) {
                return object1.hashCode() + 1;
            } else {
                return object1.hashCode() + object2.hashCode();
            }
        }

        /**
         * equals() returns true if this VertexPair represents the same unordered
         * pair of objects as the parameter "o".  The order of the pair does not
         * affect the equality test, so (u, v) is found to be equal to (v, u).
         */
        public boolean equals(Object o) {
            if (o instanceof VertexPair) {
                return ((object1.equals(((VertexPair) o).object1)) &&
                        (object2.equals(((VertexPair) o).object2))) ||
                        ((object1.equals(((VertexPair) o).object2)) &&
                                (object2.equals(((VertexPair) o).object1)));
            } else {
                return false;
            }
        }
    }

}
