package indi.IalvinchangI.patternrecognitionapp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import indi.IalvinchangI.patternrecognitionapp.App;
import indi.IalvinchangI.patternrecognitionapp.data.SettingData;
import indi.IalvinchangI.patternrecognitionapp.gui.main.MainPanel;
import indi.IalvinchangI.patternrecognitionapp.gui.message.MessagePanel;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.ChangeablePanel;
import indi.IalvinchangI.patternrecognitionapp.io.SettingHandler;


/**
 * 主視窗
 * @author IalvinchangI
 */
public class MainFrame extends JFrame {
    /** 視窗最小寬度 */
    public static final int MIN_WIDTH  = 900;
    /** 視窗最小高度 */
    public static final int MIN_HEIGHT = 500;


    /** 背景顏色 */
    public static final Color BACKGROUND_COLOR = new Color(240, 240, 240);

    /** 小標題的字體 */
    public static final Font SUBTITLE_FONT = new Font("微軟正黑體", Font.BOLD, 18);
    /** 內文的字體 */
    public static final Font CONTENT_FONT = new Font("微軟正黑體", Font.BOLD, 14);


    /**
     * 主視窗
     */
    public MainFrame(SettingData settingData, boolean firstTimeTF) {
        this.settingData = settingData;

        this.setTitle("pattern recognition app");
        this.setBackground(BACKGROUND_COLOR);

        // set size
        this.setSize(MIN_WIDTH, MIN_HEIGHT);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);


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

        this.mainPanel = new MainPanel(this.settingData);
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
