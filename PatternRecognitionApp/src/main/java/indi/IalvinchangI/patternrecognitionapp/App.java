package indi.IalvinchangI.patternrecognitionapp;

import java.io.File;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import indi.IalvinchangI.patternrecognitionapp.data.SettingData;
import indi.IalvinchangI.patternrecognitionapp.gui.MainFrame;
import indi.IalvinchangI.patternrecognitionapp.io.SettingHandler;

/**
 * 主程式
 * @author IalvinchangI
 */
public class App {

    private static final String VERSION = "PatternRecognitionApp-1.0-BETA";

    /** 最底層的資料夾 */
    public static final String ROOT_PATH = System.getProperty("user.dir");
    

    /** 設定的 File */
    public static final File SETTING_FILE = new File(ROOT_PATH, "setting.iai");


    /** pattern 的邊長 */
    public static final int PATTERN_WIDTH = 64;


    public static void main(String[] args) {
        System.out.println("version: " + VERSION);

        // read setting
        SettingHandler handler = new SettingHandler();
        SettingData settingData = handler.readSetting(SETTING_FILE);

        boolean firstTimeTF = false;  // 是否是第一次開啟 app
        if (settingData == null) {
            settingData =  new SettingData();
            firstTimeTF = true;
        }

        // for file chooser
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        MainFrame mainWindow = new MainFrame(settingData, firstTimeTF);

        mainWindow.setVisible(true);
    }
}
