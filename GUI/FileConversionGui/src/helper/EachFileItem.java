package helper;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 *
 * @author shahi
 */
public class EachFileItem extends javax.swing.JPanel {
    private File file;
    private CancelListener cancelListener;

    /**
     * Creates new form EachFileItem
     */
    public EachFileItem() {
        initComponents();
        
        cancelProcess.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (cancelListener != null) {
                    cancelListener.onCancel(EachFileItem.this);
                }
            }
        });
    }
    
    public void setCancelListener(CancelListener listener) {
        this.cancelListener = listener;
    }

    public void setImageName(String imageName) {
        if (imageName.length() > 18) {
            imageName = imageName.substring(0, 18);
            this.imageName.setText(imageName);
        }
    }
    
    public interface CancelListener {
        void onCancel(EachFileItem item);
    }
    
    public File getFile() {
        return file;
    }
    
    public void setFile(File file) {
        this.file = file;
    }
    
    public void setFileSize(String fileSize) {
        this.fileSize.setText(fileSize);
    }
    
    public void setProgress(int progress) {
        this.progressBar.setValue(progress);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageIcon = new helper.PictureBox();
        imageName = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        fileSize = new javax.swing.JLabel();
        cancelProcess = new helper.PictureBox();

        imageIcon.setImage(new javax.swing.ImageIcon(getClass().getResource("/src/image_icon.png"))); // NOI18N

        imageName.setText("image1.png");

        fileSize.setText("0.98/12.46 MB");

        cancelProcess.setImage(new javax.swing.ImageIcon(getClass().getResource("/src/cancel_icon.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(imageIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fileSize)))
                .addGap(14, 14, 14)
                .addComponent(cancelProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(imageName)
                                .addComponent(fileSize))
                            .addComponent(cancelProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private helper.PictureBox cancelProcess;
    private javax.swing.JLabel fileSize;
    private helper.PictureBox imageIcon;
    private javax.swing.JLabel imageName;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration//GEN-END:variables
}