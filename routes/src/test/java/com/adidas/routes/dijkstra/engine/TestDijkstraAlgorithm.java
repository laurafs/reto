package com.adidas.routes.dijkstra.engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.adidas.routes.dijkstra.model.Edge;
import com.adidas.routes.dijkstra.model.Graph;
import com.adidas.routes.dijkstra.model.Vertex;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestDijkstraAlgorithm {

    private List<Vertex> nodes;
    private List<Edge> edges;

    @Test
    public void testExcute() {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (int i = 0; i < 11; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            nodes.add(location);
        }

        addLane(0, 0, 1, 85L);
        addLane(1, 0, 2, 217L);
        addLane(2, 0, 4, 173L);
        addLane(3, 2, 6, 186L);
        addLane(4, 2, 7, 103L);
        addLane(5, 3, 7, 183L);
        addLane(6, 5, 8, 250L);
        addLane(7, 8, 9, 84L);
        addLane(8, 7, 9, 167L);
        addLane(9, 4, 9, 502L);
        addLane(10, 9, 10, 40L);
        addLane(11, 1, 10, 600L);

        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(0));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(10));

        assertNotNull(path);
        assertTrue(path.size() > 0);

        for (Vertex vertex : path) {
            System.out.println(vertex);
        }

    }

    private void addLane(int laneId, int sourceLocNo, int destLocNo,
    		Long duration) {
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
        edges.add(lane);
    }
}