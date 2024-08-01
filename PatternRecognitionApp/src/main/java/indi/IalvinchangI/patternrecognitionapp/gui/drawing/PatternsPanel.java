package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import indi.IalvinchangI.patternrecognitionapp.data.PatternData;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.MultiButtonPanel;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.NormalScrollPanel;


/**
 * 顯示已繪製的 Pattern 和 新增新 Pattern
 * @author IalvinchangI
 */
public class PatternsPanel extends NormalScrollPanel {

    public static final int BUTTON_WIDTH = 50;

    
    public PatternsPanel() {
        super();

        this.buttonPanel = new MultiButtonPanel(MultiButtonPanel.X_AXIS, 10);
        this.setBackground(Color.CYAN);
        
        this.addComponent(this.buttonPanel);
        
        this.setPreferredSize(new Dimension(450, BUTTON_WIDTH + 10));
    }

    private MultiButtonPanel buttonPanel = null;


    /**
     * 取得特定 button
     * @param index 特定 button 的索引值
     * @return 特定 button or null
     */
    public PatternButton getButton(int index) {
        return (PatternButton) this.buttonPanel.getButton(index);
    }


    /**
     * 新增 pattern
     * @param pattern 要新增的 pattern
     */
    public void addPattern(PatternData pattern) {
        PatternButton button = new PatternButton(pattern, BUTTON_WIDTH);

        int index = this.buttonPanel.getButtonCount();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawingPanel drawingPanel = (DrawingPanel) getParent();
                if (drawingPanel.dataController.setCurrentIndex(index) == true) {
                    drawingPanel.canvas.laodPattern(drawingPanel.dataController.getPattern());
                    drawingPanel.labelPanel.loadLabel();
                }
                else {
                    buttonPanel.setSelected(buttonPanel.getPreviousSelectedButton(), true);
                }
            }
        });

        this.buttonPanel.addButton(button);
    }
}
