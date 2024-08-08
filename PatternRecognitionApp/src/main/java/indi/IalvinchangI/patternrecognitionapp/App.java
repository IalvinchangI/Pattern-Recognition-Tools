package indi.IalvinchangI.patternrecognitionapp;

import indi.IalvinchangI.patternrecognitionapp.gui.MainFrame;

/**
 * 主程式
 * @author IalvinchangI
 */
public class App {

    /** "resources/" */
    public static final String RESOURCES_PATH = App.class.getResource("../../../").getPath();

    /** 最底層的資料夾 */
    public static final String ROOT_PATH = App.class.getResource("../../../").getPath();


    /** pattern 的邊長 */
    public static final int PATTERN_WIDTH = 64;


    public static void main(String[] args) {
        MainFrame mainWindow = new MainFrame();

        mainWindow.setVisible(true);
    }
}
