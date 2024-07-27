package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import indi.IalvinchangI.patternrecognitionapp.App;
import indi.IalvinchangI.patternrecognitionapp.data.PatternData;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.NormalButton;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.TransparentPanel;

/**
 * 顯示已繪製的 Pattern 和 新增新 Pattern
 * @author IalvinchangI
 */
public class PatternsPanel extends TransparentPanel {

    private static final int BUTTON_WIDTH = 50;

    
    public PatternsPanel() {
        this.setBackground(Color.CYAN);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

        // new
        this.patterns = new ArrayList<>();
        this.addPatternButton = new NormalButton(App.RESOURCES_PATH + "images/add_pattern.png", BUTTON_WIDTH);


        // add
        this.add(this.addPatternButton);
    }

    public ArrayList<PatternButton> patterns = null;
    private NormalButton addPatternButton = null;


    /**
     * 新增 pattern
     * @param pattern 要新增的 pattern
     */
    public void addPattern(PatternData pattern) {
        this.patterns.add(new PatternButton(pattern, BUTTON_WIDTH));

        this.add(this.patterns.get(this.patterns.size() - 1), this.getComponentCount() - 1);

        this.revalidate();
        this.repaint();
    }
}
