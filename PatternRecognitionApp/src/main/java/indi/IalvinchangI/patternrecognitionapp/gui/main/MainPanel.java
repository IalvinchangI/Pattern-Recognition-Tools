package indi.IalvinchangI.patternrecognitionapp.gui.main;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import indi.IalvinchangI.patternrecognitionapp.ResourceConstant;
import indi.IalvinchangI.patternrecognitionapp.data.SettingData;
import indi.IalvinchangI.patternrecognitionapp.gui.MainFrame;
import indi.IalvinchangI.patternrecognitionapp.gui.TeachingPanel;
import indi.IalvinchangI.patternrecognitionapp.gui.drawing.DrawingPanel;
import indi.IalvinchangI.patternrecognitionapp.gui.setting.SettingPanel;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.GraphButton;
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
    public MainPanel(MainFrame window, SettingData settingData) {
        this.settingData = settingData;

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // new
        this.buttonPanel = new TransparentPanel();
        this.contentPanel = new ChangeablePanel();

        this.changePageButtonGroup = new ButtonGroup();
        this.settingPageButton = new ChangePageButton(ResourceConstant.getImagePath(ResourceConstant.SETTING_IMAGE), 50);
        this.drawingPageButton = new ChangePageButton(ResourceConstant.getImagePath(ResourceConstant.DRAWING_IMAGE), 100);
        this.helpButton = new GraphButton(ResourceConstant.getImagePath(ResourceConstant.HELP_IMAGE), this.settingPageButton.getPreferredSize().width);
        this.exitButton = new GraphButton(ResourceConstant.getImagePath(ResourceConstant.EXIT_IMAGE), this.settingPageButton.getPreferredSize().width);

        this.settingPanel = new SettingPanel(window, this.settingData);
        this.drawingPanel = new DrawingPanel(window, this.settingData);

        
        this.addingComponents();
        
        this.settingComponents(window);

        // show
        this.contentPanel.showPage(DRAWING_PAGE_NAME);
        this.changePageButtonGroup.setSelected(this.drawingPageButton.getModel(), true);
    }

    private SettingData settingData = null;

    private TransparentPanel buttonPanel = null;
    public ChangeablePanel contentPanel = null;

    private ChangePageButton settingPageButton = null;
    private ChangePageButton drawingPageButton = null;
    private GraphButton helpButton = null;
    private GraphButton exitButton = null;
    private ButtonGroup changePageButtonGroup = null;

    private SettingPanel settingPanel = null;
    private DrawingPanel drawingPanel = null;


    private void addingComponents() {
        this.add(Box.createHorizontalStrut(1));

        this.buttonPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        this.contentPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        
        this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, BoxLayout.Y_AXIS));
        this.settingPageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.drawingPageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.helpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.buttonPanel.add(settingPageButton);
        this.buttonPanel.add(drawingPageButton);
        this.buttonPanel.add(Box.createVerticalGlue());
        this.buttonPanel.add(helpButton);
        this.buttonPanel.add(Box.createVerticalStrut(5));
        this.buttonPanel.add(exitButton);
        this.add(buttonPanel);
        
        this.contentPanel.add(settingPanel, SETTING_PAGE_NAME);
        this.contentPanel.add(drawingPanel, DRAWING_PAGE_NAME);
        this.add(contentPanel);
    }


    private void settingComponents(MainFrame window) {
        this.buttonPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        this.settingPageButton.addActionListener(this.contentPanel.createChangePagePerformed(SETTING_PAGE_NAME));
        this.drawingPageButton.addActionListener(this.contentPanel.createChangePagePerformed(DRAWING_PAGE_NAME));
        this.helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int content = 0;
                if (contentPanel.getCurrentPage().equals(SETTING_PAGE_NAME)) {
                    content = TeachingPanel.TEACH_SETTING;
                }
                else if (contentPanel.getCurrentPage().equals(DRAWING_PAGE_NAME)) {
                    content = TeachingPanel.TEACH_DRAWING;
                }
                window.teachingPanel.setContent(content);
                window.messagePanel.showMessage(window.teachingPanel);
            }
        });
        this.exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.close();
            }
        });
        
        this.changePageButtonGroup.add(this.settingPageButton);
        this.changePageButtonGroup.add(this.drawingPageButton);
        
        // this.drawingPageButton.setText("繪圖");
        // this.drawingPageButton.setFont(MainFrame.SUBTITLE_FONT);
        // this.drawingPageButton.setForeground(Color.BLACK);

        this.helpButton.setBackground(this.buttonPanel.getBackground());
        this.helpButton.setIconMargin(11);

        this.exitButton.setBackground(this.buttonPanel.getBackground());
        this.exitButton.setIconMargin(11);
    }

    public final String SETTING_PAGE_NAME = "setting";
    public final String DRAWING_PAGE_NAME = "drawing";
}
