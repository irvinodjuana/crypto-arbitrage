package com.cryptoarb.Utils;

import com.cryptoarb.Models.DirectedGraph;

import java.util.*;

public class GraphUtils {
    public static List<Integer> findNegativeCycle(DirectedGraph graph) {
        var numVertices = graph.getNumVertices();
        var weights = graph.getWeights();

        if (numVertices == 0) {
            return new ArrayList<>();
        }
        if (weights.length != numVertices || weights[0].length != numVertices) {
            throw new IllegalArgumentException("Weights array should have dimensions (numVertices x numVertices)");
        }

        return bellmanFordAlgorithm(graph);
    }

    private static List<Integer> bellmanFordAlgorithm(DirectedGraph graph) {
        final var NO_PREDECESSOR = -1;
        var numVertices = graph.getNumVertices();

        // init distance and predecessor arrays
        var distance = new double[numVertices];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        distance[0] = 0.0;
        var predecessor = new int[numVertices];
        Arrays.fill(predecessor, NO_PREDECESSOR);

        findShortestPathPredecessors(graph, distance, predecessor);
        return extractNegativeCycle(graph, distance, predecessor);
    }

    private static void findShortestPathPredecessors(DirectedGraph graph, double[] distance, int[] predecessor) {
        var numVertices = graph.getNumVertices();
        var weights = graph.getWeights();
        var edges = graph.getEdges();

        for (int i = 0; i < numVertices - 1; i++) {
            var distanceUpdated = false;
            
            for (var edge : edges) {
                int src = edge.getSrc();
                int dst = edge.getDest();

                if (distance[src] + weights[src][dst] < distance[dst]) {
                    distance[dst] = distance[src] + weights[src][dst];
                    predecessor[dst] = src;
                    distanceUpdated = true;
                }
            }

            if (!distanceUpdated) {
                break;
            }
        }
    }

    private static List<Integer> extractNegativeCycle(DirectedGraph graph, double[] distance, int[] predecessor) {
        var weights = graph.getWeights();
        var edges = graph.getEdges();

        for (var edge : edges) {
            int src = edge.getSrc();
            int dst = edge.getDest();

            if (distance[src] + weights[src][dst] < distance[dst]) {
                return extractNegativeCycleWithVertex(predecessor, src);
            }
        }

        return new ArrayList<>();
    }

    private static List<Integer> extractNegativeCycleWithVertex(int[] predecessor, int src) {
        var indexMap = new HashMap<Integer, Integer>();
        var index = 0;
        var nextPredecessor = src;
        List<Integer> negativeCycleList = new ArrayList<Integer>();

        while (!indexMap.containsKey(nextPredecessor)) {
            negativeCycleList.add(nextPredecessor);
            indexMap.put(nextPredecessor, index);
            index++;
            nextPredecessor = predecessor[nextPredecessor];
        }

        negativeCycleList.add(nextPredecessor);
        int start = indexMap.get(nextPredecessor);
        int end = negativeCycleList.size();
        negativeCycleList = negativeCycleList.subList(start, end);
        Collections.reverse(negativeCycleList);

        return negativeCycleList;
    }
}
