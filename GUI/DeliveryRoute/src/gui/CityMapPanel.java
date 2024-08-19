package org.example.GUI.DeliveryRoute.src.gui;



import org.example.GUI.DeliveryRoute.src.algorithm.Dijkstra;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CityMapPanel extends JPanel {

    private final Map<String, Point> cities;
    private final Map<String, String[]> connections;
    private final Map<String, Double> distances; // Store distances between cities
    private final Map<String, Integer> cityIndices;
    private final List<List<int[]>> optimalPaths; // Store the optimal paths for multiple destinations
    private final Map<List<int[]>, Color> pathColorMap; // Map each set of paths to a color
    private final List<String> optimalPathStrings; // To hold the string representation of the paths

    private static final Color DEFAULT_PATH_COLOR = new Color(50, 50, 50); // Blackish color
    private static final Color[] PATH_COLORS = {
        Color.GREEN, Color.BLUE, Color.RED, Color.MAGENTA, Color.ORANGE
    }; // Array of colors for different paths

    private int colorIndex = 0; // Index to cycle through PATH_COLORS

    public CityMapPanel() {
        this.cities = new HashMap<>();
        this.connections = new HashMap<>();
        this.distances = new HashMap<>();
        this.cityIndices = new HashMap<>();
        this.optimalPaths = new ArrayList<>();
        this.pathColorMap = new HashMap<>();
        this.optimalPathStrings = new ArrayList<>();

        // Initialize cities and their locations
        cities.put("Kathmandu", new Point(2, 2));
        cities.put("Pokhara", new Point(5, 3));
        cities.put("Lalitpur", new Point(3, 3));
        cities.put("Biratnagar", new Point(6, 5));
        cities.put("Bharatpur", new Point(4, 6));
        cities.put("Janakpur", new Point(2, 5));
        cities.put("Hetauda", new Point(3, 4));
        cities.put("Dhangadhi", new Point(6, 6));
        cities.put("Butwal", new Point(5, 5));
        cities.put("Nepalgunj", new Point(7, 4));

        // Initialize connections between cities
        connections.put("Kathmandu", new String[]{"Pokhara", "Lalitpur", "Janakpur", "Hetauda"});
        connections.put("Pokhara", new String[]{"Biratnagar", "Butwal"});
        connections.put("Lalitpur", new String[]{"Hetauda"});
        connections.put("Biratnagar", new String[]{"Bharatpur", "Dhangadhi"});
        connections.put("Bharatpur", new String[]{"Janakpur"});
        connections.put("Janakpur", new String[]{"Hetauda"});
        connections.put("Hetauda", new String[]{"Butwal"});
        connections.put("Dhangadhi", new String[]{"Nepalgunj"});
        connections.put("Butwal", new String[]{"Nepalgunj"});

        // Assign an index to each city for graph representation
        int index = 0;
        for (String city : cities.keySet()) {
            cityIndices.put(city, index++);
        }

        // Calculate and store distances (weights) between cities
        calculateDistances();
    }

    private void calculateDistances() {
        for (Map.Entry<String, String[]> entry : connections.entrySet()) {
            String city1 = entry.getKey();
            Point point1 = cities.get(city1);
            if (point1 != null) {
                for (String city2 : entry.getValue()) {
                    Point point2 = cities.get(city2);
                    if (point2 != null) {
                        double distance = Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
                        distances.put(city1 + "-" + city2, distance);
                        distances.put(city2 + "-" + city1, distance); // Bidirectional distance
                    }
                }
            }
        }
    }

    public void findOptimalRoutes(List<String> destinationCities) {
    String sourceCity = "Kathmandu";
    optimalPaths.clear();
    optimalPathStrings.clear();
    pathColorMap.clear();

    if (!cityIndices.containsKey(sourceCity)) {
        JOptionPane.showMessageDialog(this, "Invalid source city!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int V = cities.size();
    double[][] graph = new double[V][V];

    // Initialize the graph
    for (int i = 0; i < V; i++) {
        Arrays.fill(graph[i], Double.MAX_VALUE);
    }

    // Fill the graph with distances for direct connections
    for (Map.Entry<String, Double> entry : distances.entrySet()) {
        String[] citiesPair = entry.getKey().split("-");
        if (citiesPair.length == 2) {
            int city1Index = cityIndices.get(citiesPair[0]);
            int city2Index = cityIndices.get(citiesPair[1]);
            graph[city1Index][city2Index] = entry.getValue();
            graph[city2Index][city1Index] = entry.getValue(); // Ensure bidirectional
        }
    }

    // Highlight the optimal paths
    String currentCity = sourceCity;
    for (String destinationCity : destinationCities) {
        if (!cityIndices.containsKey(destinationCity)) {
            JOptionPane.showMessageDialog(this, "Invalid destination city: " + destinationCity, "Error", JOptionPane.ERROR_MESSAGE);
            continue;
        }

        int sourceIndex = cityIndices.get(currentCity);
        int destinationIndex = cityIndices.get(destinationCity);

        int[] path = Dijkstra.optimizeRoute(graph, sourceIndex, destinationIndex);

        // Ensure the path starts with the current city
        if (path[0] != sourceIndex) {
            int[] newPath = new int[path.length + 1];
            newPath[0] = sourceIndex;
            System.arraycopy(path, 0, newPath, 1, path.length);
            path = newPath;
        }

        // Add the path and its string representation
        optimalPaths.add(Arrays.asList(path));
        optimalPathStrings.add(getPathString(path));

        // Assign a unique color to each path
        Color pathColor = PATH_COLORS[colorIndex % PATH_COLORS.length];
        pathColorMap.put(Arrays.asList(path), pathColor);
        colorIndex++;

        currentCity = destinationCity; // Set next source as current destination
    }

    repaint(); // Trigger a repaint to visualize the paths
    showPathDetails(); // Show path details in a popup
}


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set background color
        this.setBackground(new Color(240, 248, 255)); // Alice Blue

        // Define grid size
        int gridSize = 80;
        int nodeRadius = 15;

        // Draw the grid
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < getWidth(); i += gridSize) {
            for (int j = 0; j < getHeight(); j += gridSize) {
                g.drawRect(i, j, gridSize, gridSize);
            }
        }

        // Draw cities
        g.setFont(new Font("Arial", Font.BOLD, 14));
        cities.forEach((name, point) -> {
            int x = point.x * gridSize + gridSize / 2;
            int y = point.y * gridSize + gridSize / 2;

            // Draw city name
            g.setColor(Color.BLACK);
            g.drawString(name, x - nodeRadius, y - nodeRadius - 5);

            // Draw city node
            g.setColor(Color.RED);
            g.fillOval(x - nodeRadius / 2, y - nodeRadius / 2, nodeRadius, nodeRadius);
        });

        // Draw connections (edges) between cities
        g.setColor(DEFAULT_PATH_COLOR); // Default color for all paths
        connections.forEach((city1, neighbors) -> {
            for (String city2 : neighbors) {
                drawConnection(g, city1, city2, gridSize);
            }
        });

        // Highlight the optimal path if it exists
        for (Map.Entry<List<int[]>, Color> entry : pathColorMap.entrySet()) {
            List<int[]> paths = entry.getKey();
            Color pathColor = entry.getValue();
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(pathColor);
            g2d.setStroke(new BasicStroke(3)); // Make the path thicker

            for (int[] path : paths) {
                for (int j = 0; j < path.length - 1; j++) {
                    String city1 = getCityByIndex(path[j]);
                    String city2 = getCityByIndex(path[j + 1]);
                    drawConnection(g2d, city1, city2, 80);
                }
            }
            g2d.dispose();
        }

        // Print the optimal path as a string only if it is set
        if (!optimalPathStrings.isEmpty()) {
            g.setColor(Color.BLACK);
            int y = getHeight() - 30;
            int pathCount = 1;
            for (String path : optimalPathStrings) {
                g.drawString("Path " + pathCount + ": " + path, 18, y);
                y -= 20;
                pathCount++;
            }
        }
    }

    private void drawConnection(Graphics g, String city1, String city2, int gridSize) {
        Point p1 = cities.get(city1);
        Point p2 = cities.get(city2);

        if (p1 != null && p2 != null) {
            int x1 = p1.x * gridSize + gridSize / 2;
            int y1 = p1.y * gridSize + gridSize / 2;
            int x2 = p2.x * gridSize + gridSize / 2;
            int y2 = p2.y * gridSize + gridSize / 2;

            g.drawLine(x1, y1, x2, y2);
        }
    }

    private String getPathString(int[] path) {
        StringBuilder pathString = new StringBuilder();
        for (int i = 0; i < path.length; i++) {
            pathString.append(getCityByIndex(path[i]));
            if (i < path.length - 1) {
                pathString.append(" -> ");
            }
        }
        return pathString.toString();
    }

    private String getCityByIndex(int index) {
        for (Map.Entry<String, Integer> entry : cityIndices.entrySet()) {
            if (entry.getValue() == index) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    private void showPathDetails() {
    if (optimalPathStrings.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No paths found!", "Information", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    StringBuilder pathDetails = new StringBuilder();
    for (int i = 0; i < optimalPathStrings.size(); i++) {
        pathDetails.append("Path ").append(i + 1).append(": ").append(optimalPathStrings.get(i)).append("\n");
    }

    JTextArea textArea = new JTextArea(10, 30);
    textArea.setText(pathDetails.toString());
    textArea.setEditable(false);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    JOptionPane.showMessageDialog(this, scrollPane, "Optimal Paths", JOptionPane.INFORMATION_MESSAGE);
}

}
