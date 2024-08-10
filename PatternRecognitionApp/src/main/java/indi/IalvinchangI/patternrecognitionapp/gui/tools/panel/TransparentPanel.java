package indi.IalvinchangI.patternrecognitionapp.gui.tools.panel;

import java.awt.Color;

import javax.swing.JPanel;

import indi.IalvinchangI.patternrecognitionapp.gui.tools.GUIConstant;


/**
 * 透明的 Panel
 * @author IalvinchangI
 */
public class TransparentPanel extends JPanel implements GUIConstant {
    /**
     * 把背景設成透明的
     */
    public TransparentPanel() {
        this.setBackground(new Color(0, 0, 0, 0));
    }
}
