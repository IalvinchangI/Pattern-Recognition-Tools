package indi.IalvinchangI.patternrecognitionapp.gui.tools.button;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

// import javax.swing.JButton;
import javax.swing.JRadioButton;


/**
 * 乾淨的 Button
 * @author IalvinchangI
 */
public abstract class EditableButton extends JRadioButton {
    /**
     * 把預設要繪製的東西清掉
     */
    public EditableButton() {
        setContentAreaFilled(false);
        setFocusPainted(false);  // 不繪製焦點框
        setBorderPainted(false);  // 不繪製按鈕邊框

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getParent().dispatchEvent(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                getParent().dispatchEvent(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                getParent().dispatchEvent(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                getParent().dispatchEvent(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                getParent().dispatchEvent(e);
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println("put drag");
                getParent().dispatchEvent(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                getParent().dispatchEvent(e);
            }
        });
        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                getParent().dispatchEvent(e);
            }
        });
    }


    /** 是否有選取的特效 */
    public boolean canSelectTF = false;
    
    
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
