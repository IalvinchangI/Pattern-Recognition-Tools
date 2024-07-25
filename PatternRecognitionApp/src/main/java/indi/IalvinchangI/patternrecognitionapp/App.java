package indi.IalvinchangI.patternrecognitionapp;

import indi.IalvinchangI.patternrecognitionapp.gui.MainFrame;

/**
 * 主程式
 * @author IalvinchangI
 */
public class App {

    /** "resources/" */
    public static final String RESOURCES_PATH = App.class.getResource("../../../").getPath();

    public static void main(String[] args) {
        System.out.println(RESOURCES_PATH);

        MainFrame mainWindow = new MainFrame();

        mainWindow.setVisible(true);
    }
}
