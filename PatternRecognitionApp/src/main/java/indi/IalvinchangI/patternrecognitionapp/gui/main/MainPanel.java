package indi.IalvinchangI.patternrecognitionapp.gui.main;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;

import indi.IalvinchangI.patternrecognitionapp.App;
import indi.IalvinchangI.patternrecognitionapp.data.SettingData;
import indi.IalvinchangI.patternrecognitionapp.gui.MainFrame;
import indi.IalvinchangI.patternrecognitionapp.gui.drawing.DrawingPanel;
import indi.IalvinchangI.patternrecognitionapp.gui.setting.SettingPanel;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.ChangeablePanel;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.TransparentPanel;


/**
 * 包含
 * <ul>
 *  <li> DrawingPanel </li>
 *  <li> SettingPanel </li>
 *  <li> ChangePageButtonPanel </li>
 * </ul>
 * 
 * @author IalvinchangI
 */
public class MainPanel extends TransparentPanel {
    public MainPanel(SettingData settingData) {
        this.settingData = settingData;

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // new
        this.buttonPanel = new TransparentPanel();
        this.contentPanel = new ChangeablePanel();

        this.settingPageButton = new ChangePageButton(App.RESOURCES_PATH + "images/setting.png", 50);
        this.drawingPageButton = new ChangePageButton(App.RESOURCES_PATH + "images/drawing.png", 100);

        this.settingPanel = new SettingPanel(this.settingData);
        this.drawingPanel = new DrawingPanel(this.settingData);
        
        
        // add
        this.add(Box.createHorizontalStrut(1));

        this.buttonPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        this.contentPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        
        this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, BoxLayout.Y_AXIS));
        this.settingPageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.drawingPageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.buttonPanel.add(settingPageButton);
        this.buttonPanel.add(drawingPageButton);
        this.add(buttonPanel);
        
        this.contentPanel.add(settingPanel, SETTING_PAGE_NAME);
        this.contentPanel.add(drawingPanel, DRAWING_PAGE_NAME);
        this.add(contentPanel);


        // set
        this.settingPageButton.addActionListener(this.contentPanel.createChangePagePerformed(SETTING_PAGE_NAME));
        this.drawingPageButton.addActionListener(this.contentPanel.createChangePagePerformed(DRAWING_PAGE_NAME));

        this.settingPageButton.color = settingPanel.getBackground();
        
        // this.drawingPageButton.setText("繪圖");
        // this.drawingPageButton.setFont(MainFrame.SUBTITLE_FONT);
        // this.drawingPageButton.setForeground(Color.BLACK);
        this.drawingPageButton.color = Color.YELLOW;
        
        this.buttonPanel.setBackground(MainFrame.BACKGROUND_COLOR);

        // show
        this.contentPanel.showPage(DRAWING_PAGE_NAME);
    }

    private SettingData settingData = null;

    private TransparentPanel buttonPanel = null;
    public ChangeablePanel contentPanel = null;

    private ChangePageButton settingPageButton = null;
    private ChangePageButton drawingPageButton = null;

    private SettingPanel settingPanel = null;
    private DrawingPanel drawingPanel = null;

    public final String SETTING_PAGE_NAME = "setting";
    public final String DRAWING_PAGE_NAME = "drawing";
}
