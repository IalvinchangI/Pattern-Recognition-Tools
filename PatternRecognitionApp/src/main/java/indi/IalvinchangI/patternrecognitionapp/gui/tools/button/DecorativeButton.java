package indi.IalvinchangI.patternrecognitionapp.gui.tools.button;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;


/**
 * 有特殊動畫、外觀的 Button
 * @apiNote 因為每個 DecorativeButton 背後都有一個 Timer 在動，就算按鈕沒有顯示，所以會耗較多效能
 * @author IalvinchangI
 */
public class DecorativeButton extends GraphButton {

    public DecorativeButton(int width, int height) {
        super(width - STROKE_RADIUS, height - STROKE_RADIUS);
        this.timerSetting();
    }

    public DecorativeButton(String path, int width, int height) {
        this(width, height);
        this.setIcon(path);
    }
    
    public DecorativeButton(String path, int width) {
        this(path, width, width);
    }

    private void timerSetting() {
        this.animationTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationStep += 3;
                repaint();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (animationTimer.isRunning() == false) {
                    animationTimer.start();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (canSelectTF == false || getModel().isSelected() == false) {
                    animationTimer.stop();
                    repaint();
                }
            }
        });
    }

    // for animation
    private byte animationStep = 0;
    private Timer animationTimer = null;
    

    @Override
    public void select() {
        if (canSelectTF == true && animationTimer.isRunning() == false) {
            animationTimer.start();
        }
    }

    @Override
    public void deselect() {
        if (canSelectTF == true && getModel().isRollover() == false) {
            animationTimer.stop();
            repaint();
        }
    }


    public Color buttonColor       = new Color(210, 210, 210);
    public Color ringColor         = new Color(255, 255, 255);
    public Color selectedRingColor = new Color(90, 90, 90);

    public int arcDiameter = 10;

    private static final int STROKE_RADIUS = 6;


    @Override
    protected void paintComponentSetting(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(STROKE_RADIUS * 2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    protected void paintComponentBackground(Graphics2D g2d) {
        super.paintComponentBackground(g2d);

        int width = this.getPreferredSize().width;
        int height = this.getPreferredSize().height;


        g2d.setColor(this.getBackground());
        g2d.fillRect(0, 0, width, height);

        // background
        g2d.setColor(this.buttonColor);
        g2d.fillRoundRect(
            STROKE_RADIUS / 2, STROKE_RADIUS / 2, 
            width - STROKE_RADIUS, height - STROKE_RADIUS, 
            this.arcDiameter, this.arcDiameter
        );

        // ring
        if (this.animationTimer.isRunning()) {
            if (this.getModel().isPressed() || (this.canSelectTF == true && this.getModel().isSelected())) {
                g2d.setColor(this.selectedRingColor);
            }
            else {
                g2d.setColor(this.ringColor);
            }

            double startX = 0;
            double endX   = width;
            double startY = 0;
            double endY   = height;
            if (this.animationStep >= 0) {  // +
                endX = this.animationStep / 127.;
                startY = endX * height;
                endX *= width;
            }
            else {  // -
                startX = (this.animationStep ^ (byte)(1 << 7)) / 127.;
                endY = startX * height;
                startX *= width;
            }
            
            g2d.drawLine((int) startX, 0, (int) endX, 0);  // up
            g2d.drawLine(width - 1, (int) startY, width - 1, (int) endY);  // right
            g2d.drawLine((int) (width - startX), height - 1, (int) (width - endX), height - 1);  // down
            g2d.drawLine(0, (int) (height - startY), 0, (int) (height - endY));  // left
        }
    }
}
