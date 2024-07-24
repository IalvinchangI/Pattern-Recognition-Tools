package indi.IalvinchangI.patternrecognitionapp.gui.setting;

import java.awt.Color;

import javax.swing.JLabel;

import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.TransparentPanel;


/**
 * 讓使用者調整設定的頁面，屬於 {@code MainPanel}
 * @author IalvinchangI
 */
public class SettingPanel extends TransparentPanel {
    public SettingPanel() {
        this.add(new JLabel("SettingPanel"));
        this.setBackground(Color.ORANGE);
        // TODO
    }
    
}
