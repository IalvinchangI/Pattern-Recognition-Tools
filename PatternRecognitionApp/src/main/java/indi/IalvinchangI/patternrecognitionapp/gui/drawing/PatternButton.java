package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import indi.IalvinchangI.patternrecognitionapp.data.PatternData;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.NormalButton;


/**
 * 顯示 Pattern 的按鈕
 * @author IalvinchangI
 */
public class PatternButton extends NormalButton {

    private PatternData pattern = null;

    public PatternButton(PatternData pattern, int width) {
        super(pattern.toImage(), width);
        this.pattern = pattern;

        this.setIconMargin(9);
        // TODO action listener
    }


    public void reloadPattern() {
        this.setIcon(pattern.toImage());
    }
}
