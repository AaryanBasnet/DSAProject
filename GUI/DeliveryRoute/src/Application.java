package org.example.GUI.DeliveryRoute.src;


import org.example.GUI.DeliveryRoute.src.gui.CityMapPanel;
import org.example.GUI.DeliveryRoute.src.gui.DestinationItem;
import org.example.GUI.DeliveryRoute.src.gui.PanelRound;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Application extends javax.swing.JFrame {
    private static Application application;
    private Queue<HashMap<Integer, String>> destinationQueue = new LinkedList<>();

    public Application() {
        initComponents();
        setTitle("Vehicle Route Optimization");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(Color.decode("#FFFFFF"));
        
        itemContainer.setLayout(new javax.swing.BoxLayout(itemContainer, javax.swing.BoxLayout.X_AXIS));
        itemContainer.setBackground(Color.decode("#FFFFFF"));
        
        
        scrollPaneDestinations.setBorder(null);
        scrollPaneDestinations.getViewport().setOpaque(false);
        scrollPaneDestinations.getViewport().setBorder(null);
        scrollPaneDestinations.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cityMapPanel1 = new CityMapPanel();
        panelRound1 = new PanelRound();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        selectedDestination = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        selectedVehicle = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        distanceConstraints = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        selectedAlgorithm = new javax.swing.JComboBox<>();
        startOptimization = new javax.swing.JButton();
        scrollPaneDestinations = new javax.swing.JScrollPane();
        itemContainer = new javax.swing.JPanel();
        addToDestinations = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Path Optimization");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/pin.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Add your destination and we will optimize the route for you!");

        selectedDestination.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        selectedDestination.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kathmandu", "Lalitpur", "Pokhara", "Nepalgunj", "Butwal", "Biratnagar", "Dhangadhi", "Bharatpur", "Hetauda", "Janakpur" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Select your destination");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Select the vehicle");

        selectedVehicle.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        selectedVehicle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Car • 5 Orders", "Bike • 3 Orders", "Bicycle: 2 Orders" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Distance Constraints");

        distanceConstraints.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        distanceConstraints.setText("Example: 1Km");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Select Algorithm");

        selectedAlgorithm.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        selectedAlgorithm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Djkistra's Algorithm", "Bellman Ford Algorithm" }));

        startOptimization.setBackground(new java.awt.Color(51, 102, 255));
        startOptimization.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 16)); // NOI18N
        startOptimization.setForeground(new java.awt.Color(255, 255, 255));
        startOptimization.setText("Start Optimization");
        startOptimization.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startOptimizationActionPerformed(evt);
            }
        });

        scrollPaneDestinations.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout itemContainerLayout = new javax.swing.GroupLayout(itemContainer);
        itemContainer.setLayout(itemContainerLayout);
        itemContainerLayout.setHorizontalGroup(
            itemContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 447, Short.MAX_VALUE)
        );
        itemContainerLayout.setVerticalGroup(
            itemContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        scrollPaneDestinations.setViewportView(itemContainer);

        addToDestinations.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/location.png"))); // NOI18N
        addToDestinations.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addToDestinationsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPaneDestinations)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(selectedDestination, 0, 212, Short.MAX_VALUE)
                            .addComponent(selectedVehicle, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(29, 29, 29)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(selectedAlgorithm, 0, 208, Short.MAX_VALUE)
                            .addComponent(distanceConstraints)
                            .addComponent(jLabel6)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addComponent(addToDestinations, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(startOptimization, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addGap(8, 8, 8)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(selectedAlgorithm)
                    .addComponent(selectedDestination, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(distanceConstraints)
                    .addComponent(selectedVehicle, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(startOptimization, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(addToDestinations, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(scrollPaneDestinations, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cityMapPanel1Layout = new javax.swing.GroupLayout(cityMapPanel1);
        cityMapPanel1.setLayout(cityMapPanel1Layout);
        cityMapPanel1Layout.setHorizontalGroup(
            cityMapPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cityMapPanel1Layout.createSequentialGroup()
                .addContainerGap(758, Short.MAX_VALUE)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        cityMapPanel1Layout.setVerticalGroup(
            cityMapPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cityMapPanel1Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cityMapPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cityMapPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addToDestinationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addToDestinationsMouseClicked
        String destination = (String) selectedDestination.getSelectedItem();
    String vehicle = (String) selectedVehicle.getSelectedItem();
    
    int maxDestinations = getMaxDestinationsForVehicle(vehicle);

    if (itemContainer.getComponentCount() < maxDestinations) {
        DestinationItem item = new DestinationItem();
        item.setDestinationCity(destination);

        itemContainer.setLayout(new BoxLayout(itemContainer, BoxLayout.X_AXIS));

        String priority = ""+ (itemContainer.getComponentCount() + 1);
        item.setPriorityLevel(priority);
        item.setBackground(Color.decode("#FFFFFF"));

        // Create a HashMap for the destination with its priority
        HashMap<Integer, String> destinationMap = new HashMap<>();
        destinationMap.put(itemContainer.getComponentCount() + 1, destination);

        destinationQueue.add(destinationMap);

        JOptionPane.showMessageDialog(this, "Added to Destinations.", "Warning", JOptionPane.INFORMATION_MESSAGE);
        itemContainer.add(item);

        itemContainer.revalidate();
        itemContainer.repaint();
    } else {
        JOptionPane.showMessageDialog(this, "Cannot add more destinations. Max limit reached for selected vehicle.", "Warning", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_addToDestinationsMouseClicked

    private void startOptimizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startOptimizationActionPerformed
        CityMapPanel cityMapPanel = new CityMapPanel();
        if (destinationQueue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No destinations added for optimization.", "Optimization Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get the selected destinations
        List<String> destinationCities = new ArrayList<>();
        destinationQueue.forEach(map -> {
            destinationCities.addAll(map.values());
        });

        // Ensure the algorithm is selected
        String selectedAlgo = (String) selectedAlgorithm.getSelectedItem();
        if (selectedAlgo != null && selectedAlgo.equals("Djkistra's Algorithm")) {
            cityMapPanel1.findOptimalRoutes(destinationCities);
        } else {
            JOptionPane.showMessageDialog(this, "Only Dijkstra's Algorithm is implemented for now.", "Algorithm Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_startOptimizationActionPerformed

    private int getMaxDestinationsForVehicle(String vehicle) {
        return switch (vehicle) {
            case "Car • 5 Orders" -> 5;
            case "Bike • 3 Orders" -> 3;
            case "Bicycle: 2 Orders" -> 2;
            default -> Integer.MAX_VALUE;
        };
    }
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            application = new Application();
            application.setVisible(true);
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToDestinations;
    private CityMapPanel cityMapPanel1;
    private javax.swing.JTextField distanceConstraints;
    private javax.swing.JPanel itemContainer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private PanelRound panelRound1;
    private javax.swing.JScrollPane scrollPaneDestinations;
    private javax.swing.JComboBox<String> selectedAlgorithm;
    private javax.swing.JComboBox<String> selectedDestination;
    private javax.swing.JComboBox<String> selectedVehicle;
    private javax.swing.JButton startOptimization;
    // End of variables declaration//GEN-END:variables
}
