package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import java.awt.Color;

import javax.swing.JLabel;

import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.TransparentPanel;


/**
 * 讓使用者繪圖的頁面，屬於 {@code MainPanel}
 * @author IalvinchangI
 */
public class DrawingPanel extends TransparentPanel {
    public DrawingPanel() {
        this.add(new JLabel("DrawingPanel"));
        this.setBackground(Color.YELLOW);
        // TODO
    }
}
