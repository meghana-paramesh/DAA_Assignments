package com.company;

import java.util.*;

/**
 * This application is a java implementation of Kruskal's Minimum Spanning Tree
 * Algorithm, and tests for it. The code is poached from psuedo code on page 631
 * of the text book "Introduction to Algorithms, 3rd Edition", by Cormen et al.
 *
 * @author Quinn
 */
public class App {

    /**
     * The test in this main uses asserts. Make sure -ea ("enable asserts")
     * is part of your vm arguments in order to ensure that tests run correctly
     *
     * @param args are ignored
     */
    public static void main(String[] args) {
        System.out.println("Kruskal's Algorithm...");

        App app = new App();

        //The graph on page 633 or Cormen's Intro to Alg. 3rd ed.
        Graph G = new Graph();
        Vertex a = new Vertex('a');
        Vertex b = new Vertex('b');
        Vertex c = new Vertex('c');
        Vertex d = new Vertex('d');
        Vertex e = new Vertex('e');

        G.add(new Edge(a, b, 4));
        G.add(new Edge(a, e, 2));

        G.add(new Edge(b, e, 3));
        G.add(new Edge(b, d, 5));

        G.add(new Edge(c, b, 1));
        G.add(new Edge(c, d, 7));

        G.add(new Edge(d, e, 6));


        //Make a graph A, which is the Kruskal's MST of graph G
        Graph A = app.Kruskal(G);

        //TEST that A is indeed an MST of G

        //does A have all the vertices?
        for (char label = 'a'; label <= 'i'; label++) {
            assert (A.has(new Vertex(label)));
        }

        //is A's total weigtht 4+8+1+2+4+2+7+9? (...btw: G's weight is 93)
        final int minWeight = 4 + 8 + 1 + 2 + 4 + 2 + 7 + 9;
        System.out.println("Graph weight is " + A.getWeight()
                + " num edges is " + A.getEdgesNonDecreasing().size());

        for (Edge edge : A.getEdgesNonDecreasing()) {
            System.out.println(edge.weight);
            System.out.println("start: " + edge.v.label + " end: " + edge.u.label);
        }


        //if we got to this line without asserting(false), the test is passed
        System.out.println("Test Passed");

    }

    Set<HashSet<Vertex>> sets = null;

    void makeSet(Vertex vertex) {
        if (vertex != null) {
            //initialize
            HashSet<Vertex> newSet = new HashSet<Vertex>();
            newSet.add(vertex);
            boolean addNewSet = true;

            if (sets == null) {
                sets = new HashSet<HashSet<Vertex>>();
            } else {
                //check and see if we already have this
                for (Set<Vertex> set : sets) {
                    if (newSet.equals(set)) {
                        addNewSet = false;
                    }
                }
            }
            //if the set is unique, add it
            sets.add(newSet);
        }
    }

    /**
     * FIND-SET(x) returns a pointer to the representative of the (unique)
     * set containing x. Cormen pg 562
     *
     * @return the set which contained the vertex
     */
    HashSet<Vertex> findSet(Vertex vertex) {
        HashSet<Vertex> rv = null;
        for (HashSet<Vertex> set : sets) {
            if (set.contains(vertex)) {
                if (rv != null) {
                    System.err.println("vertices shouldn't exits in more than one set");
                }
                rv = set;
            }
        }
        return rv;
    }

    /**
     * UNION consolidates two sets (determined by vertices) into one
     *
     * @param v1 a set which contains the vertex v1
     * @param v2 a set which contains the vertex v2
     */
    void union(Vertex v1, Vertex v2) {
        HashSet<Vertex> s1 = findSet(v1);
        HashSet<Vertex> s2 = findSet(v2);

        assert (s1 != null);
        assert (s2 != null);

        HashSet<Vertex> union = new HashSet<Vertex>(s1);
        union.addAll(s2);
        sets.remove(s1);
        sets.remove(s2);
        s1 = null;
        s2 = null;
        sets.add(union);
    }

    /**
     * The Kruskal's MST algorithm, as close to psuedocode as possible
     *
     * @param G the Graph to find an MST for
     * @return the MST made from the input graph
     */
    Graph Kruskal(Graph G) {
        //Make a new graph for our resulting minimum spanning tree
        Graph A = new Graph();
        //make mini set of size 1, for each vertex v exists as a vertex in G
        for (Vertex v : G.getVertices()) {
            makeSet(v);
        }
        //for each edge, smallest first
        for (Edge edge : G.getEdgesNonDecreasing()) {
            //if the end point bridges the two sets
            if (findSet(edge.u) != (findSet(edge.v))) {
                //add the edge to the MST
                A.add(edge);
                //update the sets
                union(edge.u, edge.v);
            }
        }
        return A;
    }
}

class Edge implements Comparable<Edge> {
    /**
     * the character which represents the u point of the edge
     */
    public final Vertex u;
    /**
     * the character which represents teh v point of the edge
     */
    public final Vertex v;
    /**
     * the weight of this edge
     */
    public final int weight;

    /**
     * construct a new edgeß
     *
     * @param weight the weight of the edge
     */
    Edge(Vertex u, Vertex v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    /**
     * does this edge include the vertex argument?
     *
     * @param v the vertex to test and see if this edge has
     * @return true if the vertex passed is in the edge, false otw
     */
    boolean has(Vertex v) {
        return ((this.v.equals(v)) || (this.u.equals(v)));
    }

    /**
     * a comparator is implemented to help with 'min-heap' operationsß
     *
     * @return a number indicating the relative result of the comparison
     */
    public int compareTo(Edge t1) {
        Integer it = this.weight;
        Integer it1 = t1.weight;
        return it.compareTo(it1);
    }

}

class Graph {

    private List<Edge> edges = null;

    /**
     * adds an edge to the Graph
     *
     * @param edge to add to the Graph
     */
    public void add(Edge edge) {
        if (edges == null) {
            edges = new ArrayList<Edge>();
        }
        edges.add(edge);
    }

    /**
     * answers the question, does this graph contain a particular vertex
     *
     * @param vertex the vertex to query for
     * @return true if the graph contains the vertex
     */
    boolean has(Vertex vertex) {
        boolean has = false;
        for (Edge edge : edges) {
            if (edge.has(vertex)) {
                has = true;
            }
        }
        return has;
    }

    /**
     * returns the total weight of the graph
     *
     * @return the total weight of the graph
     */
    int getWeight() {
        int weight = 0;
        for (Edge edge : edges) {
            weight += edge.weight;
        }
        return weight;
    }

    /**
     * returns all the vertices in the graph
     *
     * @return all the vertices in the graph
     */
    Set<Vertex> getVertices() {
        Set<Vertex> vertices = null;
        if (edges != null) {
            vertices = new HashSet<Vertex>();
            for (Edge edge : edges) {
                vertices.add(edge.u);
                vertices.add(edge.v);
            }
        }
        return vertices;
    }

    /**
     * analogous to creating a min-heap
     * (though I'm not positive of the underlying container type)
     *
     * @return
     */
    Queue<Edge> getEdgesNonDecreasing() {
        Queue<Edge> edges = new PriorityQueue<Edge>();
        for (Edge edge : this.edges) {
            edges.add(edge);
        }
        return edges;
    }
}

class Vertex {
    public final char label;

    /**
     * C'tor to build Vertex, requires a single character label
     *
     * @param label
     */
    Vertex(char label) {
        this.label = label;
    }

    /**
     * Vertices are compared by their labels
     *
     * @param vertex the vertex to compare with this one
     * @return true if labels are teh same, flase otw
     */
    public boolean equals(Vertex vertex) {
        return (this.label == vertex.label);
    }

    //hide private constructor
    private Vertex() {
        label = 0x07;
    }

    ;
}

