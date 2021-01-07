package com.cryptoarb.Helpers;

import com.cryptoarb.Models.DirectedGraph;

import java.util.Arrays;

public class GraphUtils {
    public static boolean detectNegativeCycles(DirectedGraph graph) {
        var numVertices = graph.getNumVertices();
        var weights = graph.getWeights();

        if (numVertices == 0) {
            return false;
        }
        if (weights.length != numVertices || weights[0].length != numVertices) {
            throw new IllegalArgumentException("Weights array should have dimensions (numVertices x numVertices)");
        }

        return bellmanFordAlgorithm(graph);
    }

    private static boolean bellmanFordAlgorithm(DirectedGraph graph) {
        final var NO_PREDECESSOR = -1;
        var numVertices = graph.getNumVertices();

        // init distance and predecessor arrays
        var distance = new double[numVertices];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        distance[0] = 0.0;
        var predecessor = new int[numVertices];
        Arrays.fill(predecessor, NO_PREDECESSOR);

        findShortestPathPredecessors(graph, distance, predecessor);
        return checkForNegativeCycles(graph, distance);
    }

    private static void findShortestPathPredecessors(DirectedGraph graph, double[] distance, int[] predecessor) {
        var numVertices = graph.getNumVertices();
        var weights = graph.getWeights();
        var edges = graph.getEdges();

        for (int i = 0; i < numVertices - 1; i++) {
            for (var edge : edges) {
                int src = edge.getSrc();
                int dst = edge.getDest();

                if (distance[src] + weights[src][dst] < distance[dst]) {
                    distance[dst] = distance[src] + weights[src][dst];
                    predecessor[dst] = src;
                }
            }
        }
    }

    private static boolean checkForNegativeCycles(DirectedGraph graph, double[] distance) {
        var weights = graph.getWeights();
        var edges = graph.getEdges();

        for (var edge : edges) {
            int src = edge.getSrc();
            int dst = edge.getDest();

            if (distance[src] + weights[src][dst] < distance[dst]) {
                return true;
            }
        }

        return false;
    }
}
