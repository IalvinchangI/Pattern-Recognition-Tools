package indi.IalvinchangI.patternrecognitionapp.gui.setting;

import java.awt.Color;

import javax.swing.BorderFactory;

import indi.IalvinchangI.patternrecognitionapp.data.SettingData;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.MultiBoxPanel;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.NormalScrollPanel;


/**
 * 讓使用者調整設定的頁面，屬於 {@code MainPanel}
 * @author IalvinchangI
 */
public class SettingPanel extends NormalScrollPanel {

    public static final int PANEL_WIDTH = 500;

    public SettingPanel(SettingData settingData) {
        super();
        this.settingData = settingData;
        this.setBackground(Color.ORANGE);

        this.panelsPanel = new MultiBoxPanel(MultiBoxPanel.Y_AXIS, 10);
        this.panelsPanel.setBackground(this.getBackground());
        this.panelsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, SCROLL_BAR_WIDTH + 3));
        this.addComponent(this.panelsPanel);

        this.panelsPanel.addPanel(new SaveDirectoryPathPanel(this.settingData, PANEL_WIDTH, 100));
    }

    private SettingData settingData = null;
    
    private MultiBoxPanel panelsPanel = null;
}
