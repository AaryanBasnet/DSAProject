package org.example.GUI.DeliveryRoute.src.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class PanelRound extends JPanel{
 
    /**
     * @return the roundTopLeft
     */
    public int getRoundTopLeft() {
        return roundTopLeft;
    }
 
    /**
     * @param roundTopLeft the roundTopLeft to set
     */
    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }
    private float opacity = 1.0f;
 
    public float getOpacity() {
        return opacity;
    }
 
    public void setOpacity(float opacity) {
        if (opacity < 0.0f) {
            this.opacity = 0.0f;
        } else if (opacity > 1.0f) {
            this.opacity = 1.0f;
        } else {
            this.opacity = opacity;
        }
        repaint();
    }
 
 
    /**
     * @return the roundTopRight
     */
    public int getRoundTopRight() {
        return roundTopRight;
    }
 
    /**
     * @param roundTopRight the roundTopRight to set
     */
    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }
 
    /**
     * @return the RoundBottomLeft
     */
    public int getRoundBottomLeft() {
        return roundBottomLeft;
    }
 
    /**
     * @param RoundBottomLeft the RoundBottomLeft to set
     */
    public void setRoundBottomLeft(int RoundBottomLeft) {
        this.roundBottomLeft = RoundBottomLeft;
        repaint();
    }
 
    /**
     * @return the roundBottomRight
     */
    public int getRoundBottomRight() {
        return roundBottomRight;
    }
 
    /**
     * @param roundBottomRight the roundBottomRight to set
     */
    public void setRoundBottomRight(int roundBottomRight) {
        this.roundBottomRight = roundBottomRight;
        repaint();
    }
    private int roundTopLeft = 0;
    private int roundTopRight=0;
    private int roundBottomLeft=0;
    private int roundBottomRight=0;
    public PanelRound(){
        setOpaque(false);

    }
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D  g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       g2.setColor(getBackground());
       Area area = new Area(createRoundTopLeft());
       if (roundTopRight>0){
           area.intersect(new Area( createRoundTopRight()));
       }
       if (roundBottomLeft>0){
           area.intersect(new Area( createRoundBottomLeft()));
       }
       if (roundBottomRight>0){
           area.intersect(new Area( createRoundBottomRight()));
       }

       g2.fill(area);
        g2.dispose();
    }
        private Shape createRoundTopRight() {
        int width=getWidth();
        int height=getHeight();
        int roundX =Math.min(width, roundTopRight);
        int roundY = Math.min(height, roundTopRight);
        Area area = new Area(new RoundRectangle2D.Double(0,0,width,height,roundX,roundY));
        area.add(new Area(new Rectangle2D.Double(0,0 ,width -roundX/2, width)));
        area.add(new Area(new Rectangle2D.Double(0,roundY / 2, width , height-roundY/ 2)));
        return area;
    }
           private Shape createRoundBottomLeft() {
        int width=getWidth();
        int height=getHeight();
        int roundX =Math.min(width, roundBottomLeft);
        int roundY = Math.min(height, roundBottomLeft);
        Area area = new Area(new RoundRectangle2D.Double(0,0,width,height,roundX,roundY));
        area.add(new Area(new Rectangle2D.Double(roundX/2,0 ,width -roundX/2, width)));
        area.add(new Area(new Rectangle2D.Double(0,0, width , height-roundY/ 2)));
        return area;
    }
                  private Shape createRoundBottomRight() {
        int width=getWidth();
        int height=getHeight();
        int roundX =Math.min(width, roundBottomRight);
        int roundY = Math.min(height, roundBottomRight);
        Area area = new Area(new RoundRectangle2D.Double(0,0,width,height,roundX,roundY));
        area.add(new Area(new Rectangle2D.Double(0,0 ,width -roundX/2, width)));
        area.add(new Area(new Rectangle2D.Double(0,0, width , height-roundY/ 2)));
        return area;
    }
 
       
    private Shape createRoundTopLeft() {
        int width=getWidth();
        int height=getHeight();
        int roundX =Math.min(width, roundTopLeft);
        int roundY = Math.min(height, roundTopLeft);
        Area area = new Area(new RoundRectangle2D.Double(0,0,width,height,roundX,roundY));
        area.add(new Area(new Rectangle2D.Double(roundX/2,0 ,width -roundX/2, width)));
        area.add(new Area(new Rectangle2D.Double(0,roundY / 2, width , height-roundY/ 2)));
        return area;
    }
 
   
}