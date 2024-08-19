package org.example.GUI.FileConversionGui.src.main;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import helper.EachFileItem;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import net.coobird.thumbnailator.Thumbnails;
import raven.toast.Notifications;

public class Runner extends javax.swing.JFrame {
    private static Runner runner;
    private final List<EachFileItem> fileItems = new ArrayList<>();
    private int totalFiles;
    private int processedFiles = 0;
    private ExecutorService executorService;
    private List<Future<?>> futures = new ArrayList<>();
    private File destinationFolder;

    public Runner() {
        initComponents();
        getContentPane().setBackground(Color.decode("#FFFFFF"));
        setResizable(false);
        setTitle("File Conversion");

        Notifications.getInstance().setJFrame(this);

        itemContainer.setLayout(new javax.swing.BoxLayout(itemContainer, javax.swing.BoxLayout.Y_AXIS));
        itemContainer.setBackground(Color.decode("#FFFFFF"));

        selectImage.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openFileChooser();
            }
        });

        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                convertFiles();
            }
        });

        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelAllConversions();
            }
        });

        selectImage.setCursor(new Cursor(Cursor.HAND_CURSOR));

        scrollableItemPane.setBorder(null);
        scrollableItemPane.getViewport().setOpaque(false);
        scrollableItemPane.getViewport().setBorder(null);

        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    private void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true); // Allow multiple file selection
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            fileItems.clear();
            itemContainer.removeAll(); // Remove all previous items

            for (File file : selectedFiles) {
                EachFileItem fileItem = new EachFileItem();
                fileItem.setFile(file);
                fileItem.setBackground(Color.decode("#FFFFFF"));

                double fileSizeInMB = file.length() / (1024.0 * 1024.0); // Use double for accurate division
                fileItem.setFileSize(String.format("%.2f MB", fileSizeInMB)); // Show two decimal places
                fileItem.setProgress(0);
                fileItem.setImageName(file.getName());

                fileItem.setCancelListener(item -> cancelConversion(item));

                itemContainer.add(fileItem);
                fileItems.add(fileItem);
            }

            itemContainer.revalidate();
            itemContainer.repaint();
        }
    }

    private void convertFiles() {
        JFileChooser folderChooser = new JFileChooser();
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = folderChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            destinationFolder = folderChooser.getSelectedFile();
            if (destinationFolder != null && !destinationFolder.exists()) {
                destinationFolder.mkdirs(); // Create folder if it does not exist
            }
            totalFiles = fileItems.size();
            processedFiles = 0;
            futures.clear();

            for (EachFileItem fileItem : fileItems) {
                File file = fileItem.getFile();
                if (file != null) {
                    FileConversionTask task = new FileConversionTask(file, fileItem);
                    Future<?> future = executorService.submit(task);
                    futures.add(future);
                }
            }
        }
    }

    private void cancelConversion(EachFileItem fileItem) {
        for (Future<?> future : futures) {
            if (future.isDone() || future.isCancelled()) {
                continue;
            }
            future.cancel(true);
            fileItem.setProgress(0); // Optionally reset progress
            break;
        }
    }

    private void cancelAllConversions() {
        for (Future<?> future : futures) {
            future.cancel(true);
        }
        for (EachFileItem fileItem : fileItems) {
            fileItem.setProgress(0); // Optionally reset progress
        }
        futures.clear();
        Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "All conversions have been cancelled.");
    }

    private class FileConversionTask implements Runnable {
        private final File file;
        private final EachFileItem fileItem;

        public FileConversionTask(File file, EachFileItem fileItem) {
            this.file = file;
            this.fileItem = fileItem;
        }

        @Override
        public void run() {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                BufferedImage resizedImage = resizeImage(bufferedImage, 100, 100); // Example resize

                if (Thread.currentThread().isInterrupted()) {
                    return;
                }

                String fileName = file.getName(); // You might want to change the file name or extension
                File outputFile = new File(destinationFolder, fileName);

                ImageIO.write(resizedImage, "png", outputFile);

                fileItem.setProgress(100);
                synchronized (Runner.this) {
                    processedFiles++;
                    if (processedFiles == totalFiles) {
                        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "All images resized successfully!");
                    }
                }
            } catch (IOException e) {
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
                e.printStackTrace();
            }
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        return Thumbnails.of(originalImage).size(targetWidth, targetHeight).asBufferedImage();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectImage = new helper.DottedPanel();
        uploadImageBackground = new helper.PictureBox();
        uploadImageText = new javax.swing.JLabel();
        titleFirst = new javax.swing.JLabel();
        titleSecond = new javax.swing.JLabel();
        scrollableItemPane = new javax.swing.JScrollPane();
        itemContainer = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        uploadImageBackground.setImage(new javax.swing.ImageIcon(getClass().getResource("/src/upload_image.png"))); // NOI18N

        uploadImageText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uploadImageText.setText("upload an image");

        javax.swing.GroupLayout selectImageLayout = new javax.swing.GroupLayout(selectImage);
        selectImage.setLayout(selectImageLayout);
        selectImageLayout.setHorizontalGroup(
            selectImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectImageLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(uploadImageText)
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectImageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(uploadImageBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        selectImageLayout.setVerticalGroup(
            selectImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectImageLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(uploadImageBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uploadImageText)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        titleFirst.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        titleFirst.setText("Image Upload");

        titleSecond.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        titleSecond.setText("Effortless Image Resizing, All at Once!");

        itemContainer.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout itemContainerLayout = new javax.swing.GroupLayout(itemContainer);
        itemContainer.setLayout(itemContainerLayout);
        itemContainerLayout.setHorizontalGroup(
            itemContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );
        itemContainerLayout.setVerticalGroup(
            itemContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 241, Short.MAX_VALUE)
        );

        scrollableItemPane.setViewportView(itemContainer);

        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jButton1.setText("Convert Now");

        jButton2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jButton2.setText("Cancel Process");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(titleFirst))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(titleSecond))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(selectImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollableItemPane, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(titleFirst)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titleSecond, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollableItemPane, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        FlatLaf.registerCustomDefaultsSource("app.fileconversiongui.theme");
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            runner = new Runner();
            runner.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel itemContainer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane scrollableItemPane;
    private helper.DottedPanel selectImage;
    private javax.swing.JLabel titleFirst;
    private javax.swing.JLabel titleSecond;
    private helper.PictureBox uploadImageBackground;
    private javax.swing.JLabel uploadImageText;
    // End of variables declaration//GEN-END:variables
}
