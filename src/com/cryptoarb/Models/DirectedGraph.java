package com.cryptoarb.Models;

import java.util.List;

public class DirectedGraph {
    private int numVertices;
    private double[][] weights;
    private List<Edge> edges;

    public DirectedGraph(int numVertices, double[][] weights, List<Edge> edges) {
        this.numVertices = numVertices;
        this.weights = weights;
        this.edges = edges;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public double[][] getWeights() {
        return weights;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
