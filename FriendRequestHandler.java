package org.example;

import java.util.ArrayList;
import java.util.List;

public class FriendRequestHandler {
    // Define the Union-Find data structure

    static class UnionFind {
        private int[] parent;
        private int[] rank;

        // Constructor to initialize the Union-Find data structure
        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i; // Each node is initially its own parent
                rank[i] = 1;   // Rank is used to keep the tree balanced
            }
        }

        // Find the root representative of the set containing x
        public int findRoot(int x) {
            if (parent[x] != x) {
                parent[x] = findRoot(parent[x]); // Path compression
            }
            return parent[x];
        }

        // Merge the sets containing x and y
        // Return true if a new friendship is successfully formed
        // Return false if x and y are already in the same set
        public boolean unionSets(int x, int y) {
            int rootX = findRoot(x);
            int rootY = findRoot(y);
            if (rootX == rootY) {
                return false; // x and y are already connected
            }
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX; // Attach the smaller tree under the larger tree
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY; // Attach the smaller tree under the larger tree
            } else {
                parent[rootY] = rootX; // If ranks are equal, attach and increase rank
                rank[rootX]++;
            }
            return true;
        }

        // Check if x and y belong to the same set
        public boolean isConnected(int x, int y) {
            return findRoot(x) == findRoot(y);
        }
    }

    // Function to process friend requests and determine if they can be approved
    public static List<String> evaluateFriendRequests(int n, int[][] restrictions, int[][] requests) {
        UnionFind unionFind = new UnionFind(n); // Initialize Union-Find for n entities
        List<String> decisions = new ArrayList<>();

        // Process each friend request
        for (int[] request : requests) {
            int entity1 = request[0];
            int entity2 = request[1];
            boolean canFormFriendship = true;

            // Check if this friendship violates any restriction
            for (int[] restriction : restrictions) {
                int restrictedEntity1 = restriction[0];
                int restrictedEntity2 = restriction[1];
                if ((unionFind.isConnected(entity1, restrictedEntity1) && unionFind.isConnected(entity2, restrictedEntity2)) ||
                        (unionFind.isConnected(entity1, restrictedEntity2) && unionFind.isConnected(entity2, restrictedEntity1))) {
                    canFormFriendship = false;
                    break; // No need to check further if a restriction is violated
                }
            }

            // If no restrictions are violated, approve the friendship
            if (canFormFriendship) {
                unionFind.unionSets(entity1, entity2);
                decisions.add("approved");
            } else {
                // Otherwise, deny the request
                decisions.add("denied");
            }
        }

        return decisions; // Return the list of decisions
    }

    // Main method to test the solution
    public static void main(String[] args) {
        int n = 5; // Number of entities
        int[][] restrictions = {{0, 1}, {1, 2}, {2, 3}}; // List of restrictions
        int[][] requests = {{0, 4}, {1, 2}, {3, 1}, {3, 4}}; // List of friend requests
        List<String> result = evaluateFriendRequests(n, restrictions, requests);

        // Print the result of each friend request
        for (String res : result) {
            System.out.println(res);
        }
    }
}
