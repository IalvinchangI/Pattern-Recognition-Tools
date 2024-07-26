package indi.IalvinchangI.patternrecognitionapp.gui.tools.button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import indi.IalvinchangI.patternrecognitionapp.gui.tools.GUITools;

/**
 * 可插入圖片的 Button
 * @author IalvinchangI
 */
public class GraphButton extends NormalButton {

    protected Image icon = null;

    protected int width = 0;
    
    
    public GraphButton() {
        this.setBackground(Color.LIGHT_GRAY);
        // TODO
    }


    /**
     * 1. 設定 icon 並縮放它
     * <p>
     * 2. 設定按鈕長寬
     * 
     * @param path 圖檔路徑
     * @param width 長寬
     */
    public GraphButton(String path, int width) {
        this.width = width;
        this.setIcon(path);
        this.setPreferredSize(new Dimension(width, width));
    }


    /**
     * 設定 icon 並縮放它
     * @param path 圖檔路徑
     */
    public void setIcon(String path) {
        this.icon = GUITools.getScaledImage(path, this.width - 5, this.width - 5);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // icon
        if (this.icon != null) {
            g2d.drawImage(
                this.icon, 
                (this.getSize().width - this.icon.getWidth(null)) / 2, 
                (this.getSize().height - this.icon.getHeight(null)) / 2, 
                null
            );
        }
        // TODO getSize -> getPreferredSize ?

        // delete g2d
        g2d.dispose();
    }
}
