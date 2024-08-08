package indi.IalvinchangI.patternrecognitionapp;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import indi.IalvinchangI.patternrecognitionapp.data.SettingData;
import indi.IalvinchangI.patternrecognitionapp.gui.MainFrame;

/**
 * 主程式
 * @author IalvinchangI
 */
public class App {

    /** "resources/" */
    public static final String RESOURCES_PATH = App.class.getResource("../../../").getPath();

    /** 最底層的資料夾 */
    public static final String ROOT_PATH = System.getProperty("user.dir");


    /** pattern 的邊長 */
    public static final int PATTERN_WIDTH = 64;


    public static void main(String[] args) {
        SettingData settingData = new SettingData();  // TODO read file

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

        MainFrame mainWindow = new MainFrame(settingData);

        mainWindow.setVisible(true);
    }
}
