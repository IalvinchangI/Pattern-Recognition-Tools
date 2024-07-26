package indi.IalvinchangI.patternrecognitionapp.gui.tools.button;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * 一般的 Button
 * @author IalvinchangI
 */
public class NormalButton extends EditableButton {
    public NormalButton() {
        // TODO
        
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        final int strokeRadius = 2;
        g2d.setStroke(new BasicStroke(strokeRadius * 2));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // background
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(
            strokeRadius, strokeRadius, 
            this.getPreferredSize().width - strokeRadius * 2 - 1, this.getPreferredSize().height - strokeRadius * 2 - 1, 
            10, 10
        );

        // border
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRoundRect(
            strokeRadius, strokeRadius, 
            this.getPreferredSize().width - strokeRadius * 2 - 1, this.getPreferredSize().height - strokeRadius * 2 - 1, 
            10, 10
        );

        // delete g2d
        g2d.dispose();
    }
}
