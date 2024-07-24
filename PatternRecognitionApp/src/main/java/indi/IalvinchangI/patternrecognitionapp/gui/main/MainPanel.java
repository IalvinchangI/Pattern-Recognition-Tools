package indi.IalvinchangI.patternrecognitionapp.gui.main;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;

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
    public MainPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // new
        this.buttonPanel = new TransparentPanel();
        this.contentPanel = new ChangeablePanel();

        this.settingPageButton = new ChangePageButton();
        this.drawingPageButton = new ChangePageButton();

        this.settingPanel = new SettingPanel();
        this.drawingPanel = new DrawingPanel();
        
        
        // add
        this.buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.buttonPanel.add(settingPageButton);
        this.buttonPanel.add(drawingPageButton);
        this.add(buttonPanel);
        
        this.contentPanel.add(settingPanel, SETTING_PAGE_NAME);
        this.contentPanel.add(drawingPanel, DRAWING_PAGE_NAME);
        this.add(contentPanel);


        // set listener
        this.settingPageButton.addActionListener(this.contentPanel.createChangePagePerformed(SETTING_PAGE_NAME));
        this.drawingPageButton.addActionListener(this.contentPanel.createChangePagePerformed(DRAWING_PAGE_NAME));
        

        // show
        this.contentPanel.showPage(DRAWING_PAGE_NAME);
    }

    private TransparentPanel buttonPanel = null;
    public ChangeablePanel contentPanel = null;

    private ChangePageButton settingPageButton = null;
    private ChangePageButton drawingPageButton = null;

    private SettingPanel settingPanel = null;
    private DrawingPanel drawingPanel = null;

    public final String SETTING_PAGE_NAME = "setting";
    public final String DRAWING_PAGE_NAME = "drawing";
}
