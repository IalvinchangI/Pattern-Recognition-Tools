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


    public Color buttonColor         = PRIMARY_BOX_COLOR;
    public Color borderColor         = SECONDARY_BOX_COLOR;
    public Color selectedBorderColor = DARK_COLOR;
    public Color hoveredBorderColor  = PRIMARY_BOX_COLOR;


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
        g2d.setColor(this.buttonColor);
        g2d.fillRoundRect(
            STROKE_RADIUS, STROKE_RADIUS, 
            this.getPreferredSize().width - STROKE_RADIUS * 2 - 1, this.getPreferredSize().height - STROKE_RADIUS * 2 - 1, 
            this.arcDiameter, this.arcDiameter
        );
    
        // border
        if (this.getModel().isPressed() || (this.canSelectTF == true && this.getModel().isSelected())) {
            g2d.setColor(this.selectedBorderColor);
        }
        else if (this.getModel().isRollover()) {
            g2d.setColor(this.hoveredBorderColor);
        }
        else {
            g2d.setColor(this.borderColor);
        }
        g2d.drawRoundRect(
            STROKE_RADIUS, STROKE_RADIUS, 
            this.getPreferredSize().width - STROKE_RADIUS * 2 - 1, this.getPreferredSize().height - STROKE_RADIUS * 2 - 1, 
            this.arcDiameter, this.arcDiameter
        );
    }
}
