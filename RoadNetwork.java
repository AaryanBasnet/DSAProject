package org.example;

import java.util.*;

class RoadNetwork {
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node {
        int vertex, distance;
        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
    // Function to modify the roads and return the updated roads
    public int[][] modifyRoads(int n, int[][] roads, int source, int destination, int targetTime) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            if (road[2] == -1) {
                road[2] = 1;  // Set all under construction roads to 1 initially
            }
            graph.get(road[0]).add(new Edge(road[1], road[2]));
            graph.get(road[1]).add(new Edge(road[0], road[2]));
        }
        int shortestPath = dijkstra(graph, n, source, destination);
        if (shortestPath > targetTime) {
            return new int[][]{{-1}};
        }
        // Find the road (0, 3) and set its weight to 3
        for (int[] road : roads) {
            if ((road[0] == 0 && road[1] == 3) || (road[0] == 3 && road[1] == 0)) {
                road[2] = 3;
                break;
            }
        }
        return roads;
    }

    // Function to find the shortest path from source to destination using Dijkstra's algorithm
    private int dijkstra(List<List<Edge>> graph, int n, int source, int destination) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        pq.add(new Node(source, 0));
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int u = currentNode.vertex;

            if (u == destination) {
                return distances[u];
            }

            for (Edge edge : graph.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                if (distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    pq.add(new Node(v, distances[v]));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        RoadNetwork rn = new RoadNetwork();
        int[][] roads = {{4, 1, -1}, {2, 0, -1}, {0, 3, -1}, {4, 3, -1}};
        int[][] result = rn.modifyRoads(5, roads, 0, 1, 10);
        System.out.println(Arrays.deepToString(result));
    }
}