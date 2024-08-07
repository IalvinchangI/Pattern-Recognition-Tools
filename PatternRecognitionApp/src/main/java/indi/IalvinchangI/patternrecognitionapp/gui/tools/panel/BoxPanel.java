package indi.IalvinchangI.patternrecognitionapp.gui.tools.panel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


/**
 * 格狀的 panel
 * @author IalvinchangI
 */
public abstract class BoxPanel extends TransparentPanel {
    
    public BoxPanel(int width, int height) {
        super();
        this.setPreferredSize(new Dimension(width, height));
    }


    /** 格子的透明度 */
    public float alpha = 1.f;


    /** 格子的底色 */
    public Color boxColor = Color.LIGHT_GRAY;
    
    /** 格子的邊框顏色 */
    public Color borderColor = Color.GRAY;


    /**
     * 設定 統一的繪製設定
     * @param g2d 元件的 Graphics
     */
    protected abstract void paintComponentSetting(Graphics2D g2d);

    /**
     * 繪製按鈕的背景、邊框
     * @param g2d 元件的 Graphics
     */
    protected abstract void paintComponentBackground(Graphics2D g2d);

    /**
     * 繪製按鈕的內容
     * @param g2d 元件的 Graphics
     */
    protected abstract void paintComponentContent(Graphics2D g2d);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha));
        this.paintComponentSetting(g2d);

        this.paintComponentBackground(g2d);
        
        this.paintComponentContent(g2d);

        // delete g2d
        g2d.dispose();
    }
}
