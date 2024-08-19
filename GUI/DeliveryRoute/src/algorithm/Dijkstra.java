package org.example.GUI.DeliveryRoute.src.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

    public static int[] optimizeRoute(double[][] graph, int source, int destination) {
        int V = graph.length;
        boolean[] visited = new boolean[V];
        double[] dist = new double[V];
        int[] prev = new int[V]; // Array to store the previous vertex in the optimal path

        // Initialize distances and previous array
        for (int i = 0; i < V; i++) {
            dist[i] = Double.MAX_VALUE;
            prev[i] = -1; // -1 means no previous node
        }

        dist[source] = 0;

        // Dijkstra's algorithm to find the shortest paths
        for (int count = 0; count < V; count++) {
            int minVertex = minVertex(dist, visited);
            visited[minVertex] = true;

            for (int i = 0; i < V; i++) {
                if (graph[minVertex][i] != 0 && !visited[i] &&
                    dist[minVertex] != Double.MAX_VALUE && dist[i] > dist[minVertex] + graph[minVertex][i]) {
                    dist[i] = dist[minVertex] + graph[minVertex][i];
                    prev[i] = minVertex; // Update the previous vertex for vertex i
                }
            }
        }

        // Return the shortest path as an array of vertices
        return reconstructPath(prev, source, destination);
    }

    private static int minVertex(double[] distance, boolean[] visited) {
        int minVertex = -1;

        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
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
