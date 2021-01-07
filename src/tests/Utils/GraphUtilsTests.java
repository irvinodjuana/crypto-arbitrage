package tests.Utils;

import com.cryptoarb.Models.DirectedGraph;
import com.cryptoarb.Models.Edge;
import com.cryptoarb.Utils.GraphUtils;
import com.google.common.base.Stopwatch;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class GraphUtilsTests {
    @Test
    public void DetectNegativeCycles_GraphContainsNegativeCycles_ReturnsTrue() {
        // Arrange
        var numVertices = 4;
        var weights = new double[4][4];
        weights[0][1] = 5.0;
        weights[0][2] = 4.0;
        weights[2][1] = -6.5;
        weights[1][3] = 3.1;
        weights[3][2] = 3.2;
        var edges = new ArrayList<Edge>();
        edges.add(new Edge(0, 1));
        edges.add(new Edge(0, 2));
        edges.add(new Edge(2, 1));
        edges.add(new Edge(1, 3));
        edges.add(new Edge(3, 2));
        var graph = new DirectedGraph(numVertices, weights, edges);

        // Act
        var negativeCycle = GraphUtils.findNegativeCycle(graph);

        // Assert
        Assert.assertEquals(3, negativeCycle.size());
        Assert.assertTrue(negativeCycle.contains(1));
        Assert.assertTrue(negativeCycle.contains(2));
        Assert.assertTrue(negativeCycle.contains(3));
    }

    @Test
    public void DetectNegativeCycles_GraphDoesNotContainsNegativeCycles_ReturnsFalse() {
        // Arrange
        var numVertices = 4;
        var weights = new double[4][4];
        weights[0][1] = 5.0;
        weights[0][2] = 4.0;
        weights[2][1] = -6.5;
        weights[1][3] = 3.1;
        weights[3][2] = 4.2;
        var edges = new ArrayList<Edge>();
        edges.add(new Edge(0, 1));
        edges.add(new Edge(0, 2));
        edges.add(new Edge(2, 1));
        edges.add(new Edge(1, 3));
        edges.add(new Edge(3, 2));
        var graph = new DirectedGraph(numVertices, weights, edges);

        // Act
        var negativeCycle = GraphUtils.findNegativeCycle(graph);

        // Assert
        Assert.assertEquals(0, negativeCycle.size());
    }

    @Test
    public void DetectNegativeCycles_LargeGraph_FinishesWithinTimeLimit() {
        // Arrange
        var stopwatch = Stopwatch.createStarted();

        var numVertices = 350;
        var weights = new double[numVertices][numVertices];
        var edges = new ArrayList<Edge>();
        int maxEdgeCount = 950;
        int edgeCount = 0;

        for (int i = 0; i < numVertices; i++) {
            for (int j = i+1; j < numVertices; j++) {
                weights[i][j] = new Random().nextDouble();
                weights[j][i] = new Random().nextDouble() * -1.0;
                edges.add(new Edge(i, j));
                edges.add(new Edge(j, i));
            }

            if (++edgeCount > maxEdgeCount) {
                break;
            }
        }

        var graph = new DirectedGraph(numVertices, weights, edges);

        // Act
        GraphUtils.findNegativeCycle(graph);
        stopwatch.stop();

        // Assert
        var millis = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        Assert.assertTrue(millis < 1000);
    }
}
