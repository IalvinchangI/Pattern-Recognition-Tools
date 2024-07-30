package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;

import indi.IalvinchangI.patternrecognitionapp.data.PatternData;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.TransparentPanel;


/**
 * 顯示已繪製的 Pattern 和 新增新 Pattern
 * @author IalvinchangI
 */
public class PatternsPanel extends TransparentPanel {

    public static final int BUTTON_WIDTH = 50;

    
    public PatternsPanel() {
        this.setBackground(Color.CYAN);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

        // new
        this.patterns = new ArrayList<>();
        this.buttonGroup = new ButtonGroup();
    }

    private ButtonGroup buttonGroup = null;

    public ArrayList<PatternButton> patterns = null;


    /**
     * 新增 pattern
     * @param pattern 要新增的 pattern
     */
    public void addPattern(PatternData pattern) {
        PatternButton button = new PatternButton(pattern, BUTTON_WIDTH);

        final int index = this.patterns.size();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawingPanel drawingPanel = (DrawingPanel) getParent();
                if (drawingPanel.dataController.setCurrentIndex(index) == true) {
                    drawingPanel.canvas.laodPattern(drawingPanel.dataController.getPattern());
                }
            }
        });

        this.patterns.add(button);
        this.buttonGroup.add(button);
        button.setSelected(true);
        this.add(button);

        this.revalidate();
        this.repaint();
    }
}
