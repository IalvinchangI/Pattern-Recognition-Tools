package indi.IalvinchangI.patternrecognitionapp.gui.tools.button;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

/**
 * 一般的 Button
 * @author IalvinchangI
 */
public class NormalButton extends GraphButton {
    
    public NormalButton(int width, int height) {
        super(width, height);
    }

    public NormalButton(String path, int width) {
        super(path, width);
    }

    public NormalButton(Image image, int width) {
        super(image, width);
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
        super.paintComponentBackground(g2d);

        // background
        g2d.setColor(new Color(220, 220, 220));
        g2d.fillRoundRect(
            STROKE_RADIUS, STROKE_RADIUS, 
            this.getPreferredSize().width - STROKE_RADIUS * 2 - 1, this.getPreferredSize().height - STROKE_RADIUS * 2 - 1, 
            this.arcDiameter, this.arcDiameter
        );
    
        // border
        if (this.getModel().isPressed() || (this.canSelectTF == true && this.getModel().isSelected())) {
            g2d.setColor(new Color(180, 180, 180));
        }
        else if (this.getModel().isRollover()) {
            g2d.setColor(new Color(210, 210, 210));
        }
        else {
            g2d.setColor(Color.WHITE);
        }
        g2d.drawRoundRect(
            STROKE_RADIUS, STROKE_RADIUS, 
            this.getPreferredSize().width - STROKE_RADIUS * 2 - 1, this.getPreferredSize().height - STROKE_RADIUS * 2 - 1, 
            this.arcDiameter, this.arcDiameter
        );
    }
}
