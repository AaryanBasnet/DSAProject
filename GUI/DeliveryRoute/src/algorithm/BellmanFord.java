package org.example.GUI.DeliveryRoute.src.algorithm;

import java.util.ArrayList;
import java.util.List;

public class BellmanFord {

    public static int[] optimizeRoute(double[][] graph, int source, int destination) {
        int V = graph.length;
        double[] dist = new double[V];
        int[] prev = new int[V]; // Array to store the previous vertex in the optimal path

        // Initialize distances and previous array
        for (int i = 0; i < V; i++) {
            dist[i] = Double.MAX_VALUE;
            prev[i] = -1; // -1 means no previous node
        }

        dist[source] = 0;

        // Bellman-Ford algorithm to find the shortest paths
        for (int i = 1; i < V; i++) {
            for (int u = 0; u < V; u++) {
                for (int v = 0; v < V; v++) {
                    if (graph[u][v] != 0 && dist[u] != Double.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                        prev[v] = u; // Update the previous vertex for vertex v
                    }
                }
            }
        }

        // Check for negative-weight cycles (optional)
        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && dist[u] != Double.MAX_VALUE &&
                    dist[u] + graph[u][v] < dist[v]) {
                    System.out.println("Graph contains a negative-weight cycle");
                    return new int[0]; // Return empty array if a negative-weight cycle is detected
                }
            }
        }

        // Return the shortest path as an array of vertices
        return reconstructPath(prev, source, destination);
    }

    // Method to reconstruct the path from source to destination
    private static int[] reconstructPath(int[] prev, int source, int destination) {
        List<Integer> path = new ArrayList<>();
        int current = destination;

        // Collect nodes in the path starting from destination to source
        while (current != -1) {
            path.add(current);
            if (current == source) break;
            current = prev[current];
        }

        // If the path does not start with the source, no valid path was found
        if (path.isEmpty() || path.get(path.size() - 1) != source) {
            return new int[0]; // Return empty array if no valid path was found
        }

        // Reverse the path to get the correct order from source to destination
        int[] result = new int[path.size()];
        for (int i = 0; i < path.size(); i++) {
            result[i] = path.get(path.size() - 1 - i);
        }

        return result;
    }
}
