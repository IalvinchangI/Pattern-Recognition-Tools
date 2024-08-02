package indi.IalvinchangI.patternrecognitionapp.gui.tools.panel;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;


/**
 * 透明的 Panel
 * @author IalvinchangI
 */
public class TransparentPanel extends JPanel {
    /**
     * 把背景設成透明的
     */
    public TransparentPanel() {
        this.setBackground(new Color(0, 0, 0, 0));

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
}
