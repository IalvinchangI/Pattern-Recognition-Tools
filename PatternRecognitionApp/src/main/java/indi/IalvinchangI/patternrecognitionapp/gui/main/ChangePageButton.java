package indi.IalvinchangI.patternrecognitionapp.gui.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.GraphButton;

/**
 * 切換頁面用的 Button
 * @author IalvinchangI
 */
public class ChangePageButton extends GraphButton {
    public final static int MIN_WIDTH = 35;

    public ChangePageButton(String path, int height) {
        super(path, MIN_WIDTH, height);
    }


    /**
     * 設定按鈕寬度
     * @param height 寬度
     */
    public void setWidth(int height) {
        this.height = height;
        this.setAbsoluteSize(new Dimension(MIN_WIDTH, height));
    }


    /** 按鈕的顏色 */
    public Color buttonColor = SECONDARY_BACKGROUND_COLOR;

    /** 按鈕被選擇時的顏色 */
    public Color selectedButtonColor = PRIMARY_BACKGROUND_COLOR;


    @Override
    protected void paintComponentSetting(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    protected void paintComponentBackground(Graphics2D g2d) {
        super.paintComponentBackground(g2d);
        
        if (this.getModel().isSelected()) {
            g2d.setColor(this.selectedButtonColor);
        }
        else {
            g2d.setColor(this.buttonColor);
        }
        g2d.fillRoundRect(0, 0, this.getPreferredSize().width + 10, this.getPreferredSize().height, 20, 20);
    }

    /*
    @Override
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
    */
}
