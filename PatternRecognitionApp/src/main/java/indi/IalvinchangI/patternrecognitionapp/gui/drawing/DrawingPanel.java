package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import indi.IalvinchangI.patternrecognitionapp.gui.MainFrame;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.TransparentPanel;


/**
 * 讓使用者繪圖的頁面，屬於 {@code MainPanel}
 * @author IalvinchangI
 */
public class DrawingPanel extends TransparentPanel {

    public static String[] labels = {"圓形", "三角形", "矩形", "五邊形", "五角星"};

    public DrawingPanel() {
        this.setBackground(Color.YELLOW);
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(3, 2, 3, 20);

        // new
        this.canvas = new GridCanvasPanel();
        this.patterns = new PatternsPanel();
        this.labelPanel = new JList<>(DrawingPanel.labels);

        // set
        this.labelPanel.setBackground(Color.MAGENTA);
        this.labelPanel.setFont(MainFrame.SUBTITLE_FONT);
        this.labelPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // add
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        this.add(canvas, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        this.add(labelPanel, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        this.add(patterns, constraints);
    }

    private GridCanvasPanel canvas = null;
    private PatternsPanel patterns = null;
    private JList<String> labelPanel = null;

}
