package indi.IalvinchangI.patternrecognitionapp.gui.tools;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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
     * 縮放 {@code Image}
     * @param image 圖
     * @param width 寬度
     * @param height 高度
     * @return 縮放完的 {@code Image}
     */
    public static Image getScaledImage(Image image, int width, int height) {
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    /**
     * 縮放 {@code Image}
     * @param path 圖檔路徑
     * @param width 寬度
     * @param height 高度
     * @return 縮放完的 {@code Image} or {@code null}
     */
    public static Image getScaledImage(String path, int width, int height) {
        Image image = GUITools.getImage(path);
        if (image == null) {
            return null;
        }
        return GUITools.getScaledImage(image, width, height);
    }
    
    /**
     * 縮放 {@code ImageIcon}
     * @param path 圖檔路徑
     * @param width 寬度
     * @param height 高度
     * @return 縮放完的 {@code ImageIcon} or {@code null}
     */
    public static ImageIcon getScaledImageIcon(String path, int width, int height) {
        Image image = getScaledImage(path, width, height);
        if (image == null) {
            return null;
        }
        return new ImageIcon(image);
    }

    /**
     * 從 path 讀取 {@code Image}
     * @param path 圖檔路徑
     * @return 讀取的 {@code Image} or {@code null}
     */
    public static Image getImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



    /**
     * 為 comp 加入事件向上傳遞的功能
     * @param comp 要加入事件向上傳遞功能的元件
     * @return 加入事件向上傳遞功能的元件
     */
    public static Component addEventBubbling(Component comp) {
        comp.addMouseListener(new MouseAdapter()  {
            @Override
            public void mouseClicked(MouseEvent e) {
                comp.getParent().dispatchEvent(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                comp.getParent().dispatchEvent(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                comp.getParent().dispatchEvent(e);
            }
        });
        comp.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                comp.getParent().dispatchEvent(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                comp.getParent().dispatchEvent(e);
            }
        });
        comp.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                comp.getParent().dispatchEvent(e);
            }
        });

        return comp;
    }
}
