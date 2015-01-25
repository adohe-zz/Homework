/* WUGraph.java */

package com.xqbase.java.graph;

import com.xqbase.java.dict.HashTable;
import com.xqbase.java.list.DList;
import com.xqbase.java.list.DListNode;

/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {

    /**
     * The Vertex HashTable mapping application's vertex to the internal vertex.
     */
    private HashTable vertexTable;
    /**
     * The Vertex Doubly-Linked list.
     */
    private DList<Vertex> vertexList;
    /**
     * The Edge Hash Table.
     */
    private HashTable edgeTable;
    /**
     * The Adjacency List.
     */
    private DList<DList<Edge>> adjacencyList;

    private int edgeCount;
    private int vertexCount;

    /**
     * WUGraph() constructs a graph having no vertices or edges.
     * <p/>
     * Running time:  O(1).
     */
    public WUGraph() {
        vertexTable = new HashTable();
        vertexList = new DList<Vertex>();
        edgeTable = new HashTable();
        adjacencyList = new DList<DList<Edge>>();
        vertexCount = 0;
        edgeCount = 0;
    }

    /**
     * vertexCount() returns the number of vertices in the graph.
     * <p/>
     * Running time:  O(1).
     */
    public int vertexCount() {
        return vertexCount;
    }

    /**
     * edgeCount() returns the total number of edges in the graph.
     * <p/>
     * Running time:  O(1).
     */
    public int edgeCount() {
        return edgeCount;
    }

    /**
     * getVertices() returns an array containing all the objects that serve
     * as vertices of the graph.  The array's length is exactly equal to the
     * number of vertices.  If the graph has no vertices, the array has length
     * zero.
     * <p/>
     * (NOTE:  Do not return any internal data structure you use to represent
     * vertices!  Return only the same objects that were provided by the
     * calling application in calls to addVertex().)
     * <p/>
     * Running time:  O(|V|).
     */
    public Object[] getVertices() {
        Object[] vertices = new Object[vertexCount];
        DListNode<Vertex> head = vertexList.front();
        int i = 0;
        while (head != null) {
            vertices[i] = head.item.element;
            head = head.next;
            i ++;
        }
        return vertices;
    }

    /**
     * addVertex() adds a vertex (with no incident edges) to the graph.
     * The vertex's "name" is the object provided as the parameter "vertex".
     * If this object is already a vertex of the graph, the graph is unchanged.
     * <p/>
     * Running time:  O(1).
     */
    public void addVertex(Object vertex) {
        if (vertex == null)
            return;

        DList<Edge> edgeDList = new DList<Edge>();
        Vertex v = new Vertex(vertex, edgeDList);
        vertexTable.insert(vertex, v);
        vertexList.insertFront(v);
        adjacencyList.insertFront(edgeDList);
        vertexCount ++;
    }

    /**
     * removeVertex() removes a vertex from the graph.  All edges incident on the
     * deleted vertex are removed as well.  If the parameter "vertex" does not
     * represent a vertex of the graph, the graph is unchanged.
     * <p/>
     * Running time:  O(d), where d is the degree of "vertex".
     */
    public void removeVertex(Object vertex) {

    }

    /**
     * isVertex() returns true if the parameter "vertex" represents a vertex of
     * the graph.
     * <p/>
     * Running time:  O(1).
     */
    public boolean isVertex(Object vertex) {
        return vertexTable.contains(vertex);
    }

    /**
     * degree() returns the degree of a vertex.  Self-edges add only one to the
     * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
     * of the graph, zero is returned.
     * <p/>
     * Running time:  O(1).
     */
    public int degree(Object vertex) {
        return 0;
    }

    /**
     * getNeighbors() returns a new Neighbors object referencing two arrays.  The
     * Neighbors.neighborList array contains each object that is connected to the
     * input object by an edge.  The Neighbors.weightList array contains the
     * weights of the corresponding edges.  The length of both arrays is equal to
     * the number of edges incident on the input vertex.  If the vertex has
     * degree zero, or if the parameter "vertex" does not represent a vertex of
     * the graph, null is returned (instead of a Neighbors object).
     * <p/>
     * The returned Neighbors object, and the two arrays, are both newly created.
     * No previously existing Neighbors object or array is changed.
     * <p/>
     * (NOTE:  In the neighborList array, do not return any internal data
     * structure you use to represent vertices!  Return only the same objects
     * that were provided by the calling application in calls to addVertex().)
     * <p/>
     * Running time:  O(d), where d is the degree of "vertex".
     */
    public Neighbors getNeighbors(Object vertex) {
        return null;
    }

    /**
     * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
     * u and v does not represent a vertex of the graph, the graph is unchanged.
     * The edge is assigned a weight of "weight".  If the graph already contains
     * edge (u, v), the weight is updated to reflect the new value.  Self-edges
     * (where u.equals(v)) are allowed.
     * <p/>
     * Running time:  O(1).
     */
    public void addEdge(Object u, Object v, int weight) {

    }

    /**
     * removeEdge() removes an edge (u, v) from the graph.  If either of the
     * parameters u and v does not represent a vertex of the graph, the graph
     * is unchanged.  If (u, v) is not an edge of the graph, the graph is
     * unchanged.
     * <p/>
     * Running time:  O(1).
     */
    public void removeEdge(Object u, Object v) {

    }

    /**
     * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
     * if (u, v) is not an edge (including the case where either of the
     * parameters u and v does not represent a vertex of the graph).
     * <p/>
     * Running time:  O(1).
     */
    public boolean isEdge(Object u, Object v) {
        return false;
    }

    /**
     * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
     * an edge (including the case where either of the parameters u and v does
     * not represent a vertex of the graph).
     * <p/>
     * (NOTE:  A well-behaved application should try to avoid calling this
     * method for an edge that is not in the graph, and should certainly not
     * treat the result as if it actually represents an edge with weight zero.
     * However, some sort of default response is necessary for missing edges,
     * so we return zero.  An exception would be more appropriate, but also more
     * annoying.)
     * <p/>
     * Running time:  O(1).
     */
    public int weight(Object u, Object v) {
        return 0;
    }

    /**
     * The internal Vertex class.
     */
    private static class Vertex {

        private Object element;
        private DList<Edge> edgeDList;

        public Vertex(Object element, DList<Edge> edgeDList) {
            this.element = element;
            this.edgeDList = edgeDList;
        }
    }

    /**
     * The internal Edge class.
     */
    private static class Edge {

        private int weight;
        private Vertex origin;
        private Vertex dest;

        public Edge(int weight, Vertex origin, Vertex dest) {
            this.weight = weight;
            this.origin = origin;
            this.dest = dest;
        }
    }
}
