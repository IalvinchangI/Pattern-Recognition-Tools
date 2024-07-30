package indi.IalvinchangI.patternrecognitionapp.gui.tools.button;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;

import indi.IalvinchangI.patternrecognitionapp.gui.tools.GUITools;


/**
 * 可插入圖片的 Button
 * @author IalvinchangI
 */
public class GraphButton extends EditableButton {

    protected Image icon = null;

    /** 按鈕的寬 */
    protected int width = 0;
    /** 按鈕的長 (高) */
    protected int height = 0;
    
    
    /**
     * 設定按鈕長寬
     * @param width 寬
     * @param height 長 (高)
     */
    public GraphButton(int width, int height) {
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
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
        this(path, width, width);
    }

    /**
     * 1. 設定 icon 並縮放它
     * <p>
     * 2. 設定按鈕長寬
     * 
     * @param path 圖檔路徑
     * @param width 寬
     * @param height 長 (高)
     */
    public GraphButton(String path, int width, int height) {
        this(width, height);
        this.setIcon(path);
    }

    /**
     * 1. 設定 icon 並縮放它
     * <p>
     * 2. 設定按鈕長寬
     * 
     * @param image 圖
     * @param width 長寬
     */
    public GraphButton(Image image, int width) {
        this(image, width, width);
    }

    /**
     * 1. 設定 icon 並縮放它
     * <p>
     * 2. 設定按鈕長寬
     * 
     * @param image 圖
     * @param width 寬
     * @param height 長 (高)
     */
    public GraphButton(Image image, int width, int height) {
        this(width, height);
        this.setIcon(image);
    }


    private int iconMargin = 5;
    
    public void setIconMargin(int margin) {
        this.iconMargin = margin;
        this.setIcon(this.icon);
        repaint();
    }

    /**
     * 設定 icon 並縮放它
     * @param image 圖
     */
    public void setIcon(Image image) {
        Dimension iconSize = this.computeIconSize(image);
        this.icon = GUITools.getScaledImage(image, iconSize.width - iconMargin, iconSize.height - iconMargin);
        repaint();
    }

    /**
     * 設定 icon 並縮放它
     * @param path 圖檔路徑
     */
    public void setIcon(String path) {
        this.setIcon(GUITools.getImage(path));
    }


    private Dimension computeIconSize(Image image) {
        float width = image.getWidth(null);
        float height = image.getHeight(null);

        float scale = Math.min(this.width / width, this.height / height);

        return new Dimension((int)(width * scale), (int)(height * scale));
    }
    
    
    @Override
    protected void paintComponentSetting(Graphics2D g2d) {}

    @Override
    protected void paintComponentBackground(Graphics2D g2d) {}

    @Override
    protected void paintComponentContent(Graphics2D g2d) {
        if (this.icon != null) {
            g2d.drawImage(
                this.icon, 
                (this.getPreferredSize().width - this.icon.getWidth(null)) / 2, 
                (this.getPreferredSize().height - this.icon.getHeight(null)) / 2, 
                null
            );
        }
    }
}
