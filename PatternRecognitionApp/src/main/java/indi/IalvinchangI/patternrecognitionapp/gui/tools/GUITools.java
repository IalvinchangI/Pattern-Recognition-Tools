package indi.IalvinchangI.patternrecognitionapp.gui.tools;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 有靜態方法
 * @author IalvinchangI
 */
public class GUITools {
    /**
     * 縮放 {@code ImageIcon}
     * @param path 圖檔路徑
     * @param width 寬度
     * @param height 高度
     * @return 縮放完的 {@code ImageIcon} or {@code null}
     */
    public static ImageIcon getScaledImageIcon(String path, int width, int height) {
        try {
            return new ImageIcon(ImageIO.read(new File(path)).getScaledInstance(width, height, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
