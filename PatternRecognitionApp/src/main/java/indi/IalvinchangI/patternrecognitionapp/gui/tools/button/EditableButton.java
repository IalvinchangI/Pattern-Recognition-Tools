package indi.IalvinchangI.patternrecognitionapp.gui.tools.button;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JRadioButton;

import indi.IalvinchangI.patternrecognitionapp.gui.tools.GUIConstant;


/**
 * 乾淨的 Button
 * @author IalvinchangI
 */
public abstract class EditableButton extends JRadioButton implements GUIConstant {
    /**
     * 把預設要繪製的東西清掉
     */
    public EditableButton() {
        setContentAreaFilled(false);
        setFocusPainted(false);  // 不繪製焦點框
        setBorderPainted(false);  // 不繪製按鈕邊框
    }


    /** 是否有選取的特效 */
    public boolean canSelectTF = false;

    /** 選取 button */
    public void select() {}

    /** 取消選取 button */
    public void deselect() {}
    
    
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
    protected void paintComponentContent(Graphics2D g2d) {
        if (this.getText() != null) {
            FontMetrics fm = g2d.getFontMetrics();
            Rectangle stringBounds = fm.getStringBounds(getText(), g2d).getBounds();
            int textX = (this.getSize().width - stringBounds.width) / 2;
            int textY = (this.getSize().height - stringBounds.height) / 2 + fm.getAscent();
            g2d.setColor(getForeground());
            g2d.drawString(getText(), textX, textY);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        this.paintComponentSetting(g2d);

        this.paintComponentBackground(g2d);
        
        this.paintComponentContent(g2d);

        // delete g2d
        g2d.dispose();
    }
}
