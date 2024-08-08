package indi.IalvinchangI.patternrecognitionapp.gui.tools.panel;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


/**
 * 一般的 BoxPanel
 * @author IalvinchangI
 */
public class NormalBoxPanel extends BoxPanel {

    public NormalBoxPanel(int width, int height) {
        super(width, height);
    }


    public int arcDiameter = 10;

    private static final int STROKE_RADIUS = 1;


    @Override
    protected void paintComponentSetting(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(STROKE_RADIUS * 2));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    protected void paintComponentBackground(Graphics2D g2d) {
        // background
        g2d.setColor(this.boxColor);
        g2d.fillRoundRect(
            STROKE_RADIUS, STROKE_RADIUS, 
            getWidth() - STROKE_RADIUS * 2 - 1, getHeight() - STROKE_RADIUS * 2 - 1, 
            this.arcDiameter, this.arcDiameter
        );
        
        // border
        g2d.setColor(this.borderColor);
        g2d.drawRoundRect(
            STROKE_RADIUS, STROKE_RADIUS, 
            getWidth() - STROKE_RADIUS * 2 - 1, getHeight() - STROKE_RADIUS * 2 - 1, 
            this.arcDiameter, this.arcDiameter
        );
    }

    @Override
    protected void paintComponentContent(Graphics2D g2d) {}
}
