package indi.IalvinchangI.patternrecognitionapp.gui.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Icon;

import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.EditableButton;

/**
 * 切換頁面用的 Button
 * @author IalvinchangI
 */
public class ChangePageButton extends EditableButton {
    public final static int MIN_HEIGHT = 30;

    public ChangePageButton() {
        super();
    }


    public void setWidth(int width) {
        this.setPreferredSize(new Dimension(width, MIN_HEIGHT));
    }


    /** 按鈕的顏色 */
    public Color color = Color.WHITE;


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(this.color);
        
        // background
        g2d.fillRoundRect(0, 0, this.getPreferredSize().width, this.getPreferredSize().height + 10, 20, 20);

        // text
        if (this.getText() != null) {
            FontMetrics fm = g2d.getFontMetrics();
            Rectangle stringBounds = fm.getStringBounds(getText(), g2d).getBounds();
            int textX = (getWidth() - stringBounds.width) / 2;
            int textY = (getHeight() - stringBounds.height) / 2 + fm.getAscent();
            g2d.setColor(getForeground());
            g2d.drawString(getText(), textX, textY);
        }

        // delete g2d
        g2d.dispose();
    }
}
