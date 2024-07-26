package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.TransparentPanel;


/**
 * 繪圖區
 * @author IalvinchangI
 */
public class GridCanvaPanel extends TransparentPanel {

    public static final int GRID_COUNT = 64;
    public static final int GRID_WIDTH = 8;


    public GridCanvaPanel() {
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(GRID_COUNT * GRID_WIDTH, GRID_COUNT * GRID_WIDTH));
        
        // draw
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                // if (lastX != -1) {
                //     drawingPattern.drawLine(lastX, lastY, x, y);
                //     repaint();
                // }
                // else {
                //     drawingPattern = (Graphics2D) getGraphics().create();
                // }
                // TODO
                lastX = x;
                lastY = y;
            }
        });

        // TODO
    }

    private Graphics2D drawingPattern = null;

    private int lastX = -1;
    private int lastY = -1;


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setStroke(new BasicStroke(0));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.LIGHT_GRAY);

        int width = this.getPreferredSize().width;

        // grid
        for (int i = GRID_WIDTH; i < width; i += GRID_WIDTH) {
            // horizontal
            g2d.drawLine(0, i, width, i);
            // vertical
            g2d.drawLine(i, 0, i, width);
        }

        // delete g2d
        g2d.dispose();
    }
}
