package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import indi.IalvinchangI.patternrecognitionapp.App;
import indi.IalvinchangI.patternrecognitionapp.data.PatternData;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.GraphButton;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.TransparentPanel;

/**
 * 顯示已繪製的 Pattern 和 新增新 Pattern
 * @author IalvinchangI
 */
public class PatternsPanel extends TransparentPanel {

    
    public PatternsPanel() {
        this.setBackground(Color.CYAN);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

        // new
        this.patterns = new ArrayList<>();
        this.addPatternButton = new GraphButton(App.RESOURCES_PATH + "images/add_pattern.png", 50);


        // add
        this.add(this.addPatternButton);
    }

    public ArrayList<PatternButton> patterns = null;
    private GraphButton addPatternButton = null;


    /**
     * 新增 pattern
     * @param pattern 要新增的 pattern
     */
    public void addPattern(PatternData pattern) {
        this.patterns.add(new PatternButton(pattern));

        this.add(this.patterns.get(this.patterns.size() - 1), this.getComponentCount() - 1);

        this.revalidate();
        this.repaint();
    }
}
