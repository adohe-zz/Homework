/* WUGraph.java */

package com.xqbase.java.graph;

import com.xqbase.java.dict.Entry;
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
            head = vertexList.next(head);
            i++;
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
        if (vertex == null || vertexTable.contains(vertex))
            return;

        DList<Edge> edgeDList = new DList<Edge>();
        Vertex v = new Vertex(vertex, edgeDList, null);
        vertexTable.insert(vertex, v);
        DListNode<Vertex> node = vertexList.insertFront(v);
        v.node = node;
        adjacencyList.insertFront(edgeDList);
        vertexCount++;
    }

    /**
     * removeVertex() removes a vertex from the graph.  All edges incident on the
     * deleted vertex are removed as well.  If the parameter "vertex" does not
     * represent a vertex of the graph, the graph is unchanged.
     * <p/>
     * Running time:  O(d), where d is the degree of "vertex".
     */
    public void removeVertex(Object vertex) {
        if (!isVertex(vertex))
            return;

        Vertex v = (Vertex) vertexTable.find(vertex).value();
        DList<Edge> edgeDList = v.edgeDList;
        DListNode<Edge> head = edgeDList.front();
        while (head != null) {
            DListNode<Edge> nextEdge = edgeDList.next(head);
            Edge edge = head.item;
            DListNode<Edge> firstNode = edge.firstNode;
            DListNode<Edge> secondNode = edge.secondNode;
            if (firstNode.equals(secondNode)) {
                edgeDList.remove(firstNode);
                edgeTable.remove(new VertexPair(vertex, vertex));
                edgeCount--;
            } else {
                edgeDList.remove(head);
                Vertex other = edge.dest == v ? edge.origin : edge.dest;
                other.edgeDList.remove(firstNode == head ? secondNode : firstNode);
                edgeTable.remove(new VertexPair(vertex, other.element));
                edgeCount--;
            }
            head = nextEdge;
        }
        vertexTable.remove(vertex);
        vertexList.remove(v.node);
        vertexCount--;
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
        if (!isVertex(vertex))
            return 0;

        Vertex v = (Vertex) vertexTable.find(vertex).value();
        return v.edgeDList.length();
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
        if (!isVertex(vertex) || degree(vertex) == 0)
            return null;

        Vertex v = (Vertex) vertexTable.find(vertex).value();
        int degree = degree(vertex);
        DList<Edge> edgeDList = v.edgeDList;
        Neighbors neighbors = new Neighbors();
        Object[] neighborList = new Object[degree];
        int[] weightList = new int[degree];
        DListNode<Edge> head = edgeDList.front();
        int i = 0;
        while (head != null) {
            Edge edge = head.item;
            weightList[i] = edge.weight;
            if (edge.origin.element.equals(vertex)) {
                neighborList[i] = edge.dest.element;
            } else {
                neighborList[i] = edge.origin.element;
            }
            head = edgeDList.next(head);
            i++;
        }
        neighbors.neighborList = neighborList;
        neighbors.weightList = weightList;

        return neighbors;
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
        if (!isVertex(u) || !isVertex(v))
            return;

        Edge edge;
        Entry entry = edgeTable.find(new VertexPair(u, v));
        if (entry != null) {
            edge = (Edge) entry.value();
            edge.weight = weight;
        } else {
            Vertex uVertex = (Vertex) vertexTable.find(u).value();
            Vertex vVertex = (Vertex) vertexTable.find(v).value();
            edge = new Edge(weight, uVertex, vVertex, null, null);
            if (uVertex.equals(vVertex)) {
                DListNode<Edge> firstNode = uVertex.edgeDList.insertFront(edge);
                edge.firstNode = firstNode;
                edge.secondNode = firstNode;
            } else {
                DListNode<Edge> firstNode = uVertex.edgeDList.insertFront(edge);
                DListNode<Edge> secondNode = vVertex.edgeDList.insertFront(edge);
                edge.firstNode = firstNode;
                edge.secondNode = secondNode;
            }
            edgeTable.insert(new VertexPair(u, v), edge);
            edgeCount++;
        }

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
        if (!isVertex(u) || !isVertex(v))
            return;
        if (!isEdge(u, v))
            return;

        VertexPair key = new VertexPair(u, v);
        Edge edge = (Edge) edgeTable.find(key).value();
        if (u.equals(v)) {
            Vertex uVertex = (Vertex) vertexTable.find(u).value();
            uVertex.edgeDList.remove(edge.firstNode);
        } else {
            Vertex uVertex = (Vertex) vertexTable.find(u).value();
            uVertex.edgeDList.remove(edge.firstNode);
            Vertex vVertex = (Vertex) vertexTable.find(v).value();
            vVertex.edgeDList.remove(edge.secondNode);
        }
        edgeTable.remove(key);
        edgeCount--;
    }

    /**
     * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
     * if (u, v) is not an edge (including the case where either of the
     * parameters u and v does not represent a vertex of the graph).
     * <p/>
     * Running time:  O(1).
     */
    public boolean isEdge(Object u, Object v) {
        return edgeTable.contains(new VertexPair(u, v));
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
        if (!isEdge(u, v))
            return 0;

        Edge edge = (Edge) edgeTable.find(new VertexPair(u, v)).value();
        return edge.weight;
    }

    /**
     * The internal Vertex class.
     */
    private static class Vertex {

        private Object element;
        private DList<Edge> edgeDList;
        private DListNode<Vertex> node;

        public Vertex(Object element, DList<Edge> edgeDList, DListNode<Vertex> node) {
            this.element = element;
            this.edgeDList = edgeDList;
            this.node = node;
        }
    }

    /**
     * The internal Edge class.
     */
    private static class Edge {

        private int weight;
        private Vertex origin;
        private Vertex dest;

        private DListNode<Edge> firstNode;
        private DListNode<Edge> secondNode;

        public Edge(int weight, Vertex origin, Vertex dest, DListNode<Edge> firstNode,
                    DListNode<Edge> secondNode) {
            this.weight = weight;
            this.origin = origin;
            this.dest = dest;
            this.firstNode = firstNode;
            this.secondNode = secondNode;
        }
    }
}
