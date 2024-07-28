package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.Box;

import indi.IalvinchangI.patternrecognitionapp.App;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.NormalButton;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.TransparentPanel;


/**
 * 繪圖區
 * @author IalvinchangI
 */
public class GridCanvasPanel extends TransparentPanel {

    public static final int GRID_COUNT = App.PATTERN_WIDTH;
    public static final int GRID_WIDTH = 8;

    public static final int STROKE_WIDTH = 2;


    public GridCanvasPanel() {
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(GRID_COUNT * GRID_WIDTH, GRID_COUNT * GRID_WIDTH));
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // clean
        this.cleanButton = new NormalButton(App.RESOURCES_PATH + "images/clean_canvas.png", 35);
        this.cleanButton.setIconMargin(8);

        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(this.cleanButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(
            Box.createHorizontalStrut(this.getPreferredSize().width - this.cleanButton.getPreferredSize().width - 5), 
            constraints
        );
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        this.add(
            Box.createVerticalStrut(this.getPreferredSize().height - this.cleanButton.getPreferredSize().height - 5), 
            constraints
        );


        this.cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearCanvas();
            }
        });

        
        // draw setting
        this.drawingPattern = new BufferedImage(GRID_COUNT, GRID_COUNT, BufferedImage.TYPE_BYTE_GRAY);
        this.drawingPatternG2D = this.drawingPattern.createGraphics();
        clearCanvas();
        this.drawingPatternG2D.setStroke(new BasicStroke(STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (emptyCheck == 1) {
                    if (contains(e.getPoint()) == false) {  // out of the canvas?
                        emptyCheck = 0;  // stop it
                        savePattern();
                        return;
                    }
                    int x = e.getX() / GRID_WIDTH;
                    int y = e.getY() / GRID_WIDTH;
                    
                    drawingPatternG2D.drawLine(lastX, lastY, x, y);
                    repaint();
                    
                    lastX = x;
                    lastY = y;
                }
                else if (emptyCheck == 2) {
                    emptyCheck = 1;
                    lastX = e.getX() / GRID_WIDTH;
                    lastY = e.getY() / GRID_WIDTH;
                }
            }
        });
        this.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (emptyCheck == 1) {
                        emptyCheck = 0;
                        savePattern();
                    }
                    else if (emptyCheck == 2) {
                        emptyCheck = 3;
                    }
                }
            }
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (emptyCheck == 3) {
                        emptyCheck = 2;
                    }
                }
            }
        });
    }

    private NormalButton cleanButton = null;

    private BufferedImage drawingPattern = null;
    private Graphics2D drawingPatternG2D = null;

    private int lastX = -1;
    private int lastY = -1;

    /**
     * 畫布是不是空的
     * <p>
     * 0 -> 不是空的
     * <p>
     * 1 -> 繪圖中
     * <p>
     * 2 -> 按下 BUTTON1
     * <p>
     * 3 -> 空的
     */
    private byte emptyCheck = 0;


    /** 清空畫布 */
    public void clearCanvas() {
        this.drawingPatternG2D.setColor(Color.WHITE);
        this.drawingPatternG2D.fillRect(0, 0, GRID_COUNT, GRID_COUNT);
        this.drawingPatternG2D.setColor(Color.BLACK);

        this.lastX = -1;
        this.lastY = -1;
        this.emptyCheck = 3;
        repaint();
    }


    /** 儲存 pattern 的 圖形、各點速度 */
    private void savePattern() {
        // TODO
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setStroke(new BasicStroke(0));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.LIGHT_GRAY);

        int width = this.getPreferredSize().width;

        // draw
        g2d.drawImage(
            this.drawingPattern.getScaledInstance(width, width, Image.SCALE_AREA_AVERAGING), 
            0, 0, 
            null
        );

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
