package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

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
    }

    public ArrayList<PatternButton> patterns = null;


    /**
     * 新增 pattern
     * @param pattern 要新增的 pattern
     */
    public void addPattern(PatternData pattern) {
        PatternButton button = new PatternButton(pattern, BUTTON_WIDTH);
        
        this.patterns.add(button);
        this.add(button);

        this.revalidate();
        this.repaint();
    }
}
