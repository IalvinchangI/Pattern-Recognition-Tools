package indi.IalvinchangI.patternrecognitionapp.gui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import indi.IalvinchangI.patternrecognitionapp.App;
import indi.IalvinchangI.patternrecognitionapp.data.SettingData;
import indi.IalvinchangI.patternrecognitionapp.gui.main.MainPanel;
import indi.IalvinchangI.patternrecognitionapp.gui.message.MessagePanel;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.GUIConstant;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.ChangeablePanel;
import indi.IalvinchangI.patternrecognitionapp.io.SettingHandler;


/**
 * 主視窗
 * @author IalvinchangI
 */
public class MainFrame extends JFrame implements GUIConstant {

    /**
     * 主視窗
     */
    public MainFrame(SettingData settingData, boolean firstTimeTF) {
        this.settingData = settingData;

        // set size
        this.setSize(MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT);
        this.setMinimumSize(new Dimension(MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT));
        this.setPreferredSize(new Dimension(MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setTitle("pattern recognition app");
        this.setBackground(SECONDARY_BACKGROUND_COLOR);


        // set CloseOperation
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });


        setOuterPanel();
    }


    private SettingData settingData = null;


    /**
     * 包含
     * <ul>
     *  <li> MainPanel </li>
     *  <li> TeachingPanel </li>
     *  <li> MessagePanel </li>
     * </ul>
     */
    private void setOuterPanel() {
        // new
        this.outerChangePanel = new ChangeablePanel();

        this.mainPanel = new MainPanel(this, this.settingData);
        this.teachingPanel = new TeachingPanel();
        this.messagePanel = new MessagePanel();


        // add
        this.outerChangePanel.add(mainPanel, MAIN_PAGE_NAME);
        this.outerChangePanel.add(teachingPanel, TEACHING_PAGE_NAME);
        this.outerChangePanel.add(messagePanel, MESSAGE_PAGE_NAME);

        this.add(outerChangePanel);


        // show
        this.outerChangePanel.showPage(MAIN_PAGE_NAME);
    }

    public ChangeablePanel outerChangePanel = null;

    private MainPanel     mainPanel = null;
    private TeachingPanel teachingPanel = null;
    private MessagePanel  messagePanel = null;

    public final String MAIN_PAGE_NAME     = "main";
    public final String TEACHING_PAGE_NAME = "teaching";
    public final String MESSAGE_PAGE_NAME  = "message";


    /**
     * 關閉視窗
     */
    public void close() {
        // update setting
        SettingHandler handler = new SettingHandler();
        if (handler.writeSetting(App.SETTING_FILE, this.settingData) == false) {
            this.settingData.checkAndFix();
            handler.writeSetting(App.SETTING_FILE, this.settingData);
        }

        // delete
        this.dispose();  // 釋放視窗相關的資源
        System.exit(0);  // 退出程式
    }
}
