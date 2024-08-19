package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ScrollPaneConstants;

import indi.IalvinchangI.patternrecognitionapp.data.PatternData;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.EditableButton;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.MultiButtonPanel;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.NormalScrollPanel;


/**
 * 顯示已繪製的 Pattern 和 新增新 Pattern
 * @author IalvinchangI
 */
class PatternsPanel extends NormalScrollPanel {

    public static final int BUTTON_WIDTH = 50;

    
    public PatternsPanel(DrawingPanel drawingPanel) {
        super();
        this.drawingPanel = drawingPanel;
        this.setBackground(PRIMARY_BACKGROUND_COLOR);

        this.buttonPanel = new MultiButtonPanel(MultiButtonPanel.X_AXIS, 10);
        
        this.addComponent(this.buttonPanel);
        this.buttonPanel.setBackground(this.getBackground());
        this.buttonPanel.setBorder(BorderFactory.createEmptyBorder(SCROLL_BAR_WIDTH + 3, 0, 0, 0));
        this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        this.setPreferredSize(new Dimension(450, BUTTON_WIDTH + (3 + SCROLL_BAR_WIDTH) * 2 + MARGIN * 2));
        this.setMinimumSize(this.getPreferredSize());
    }

    private DrawingPanel drawingPanel = null;

    private MultiButtonPanel buttonPanel = null;


    /**
     * 取得 button 數量
     * @return button 數量
     */
    public int getButtonCount() {
        return this.buttonPanel.getButtonCount();
    }


    /**
     * 取得特定 button
     * @param index 特定 button 的索引值
     * @return 特定 button or null
     */
    public PatternButton getButton(int index) {
        return (PatternButton) this.buttonPanel.getButton(index);
    }


    /**
     * 取得上一個選取的 PatternButton
     * @return 上一個選取的 PatternButton
     */
    public PatternButton getPreviousSelectedButton() {
        return (PatternButton) this.buttonPanel.getPreviousSelectedButton();
    }


    /**
     * 取得 button 在 PatternsPanel 內的索引值
     * @param button 想取得索引值的 button
     * @return button 在 PatternsPanel 內索引值
     */
    public int indexOf(EditableButton button) {
        return this.buttonPanel.indexOf(button);
    }


    /**
     * PatternsPanel 內是否有這個 button
     * @param button 要檢測的 button
     * @return 是否在 PatternsPanel 內
     */
    public boolean contains(PatternButton button) {
        return this.buttonPanel.contains(button);
    }


    /**
     * 新增 pattern
     * @param pattern 要新增的 pattern
     */
    public void addPattern(PatternData pattern) {
        PatternButton button = new PatternButton(pattern, BUTTON_WIDTH, this.drawingPanel);
        button.setBackground(this.getBackground());

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.changeEditingPattern(drawingPanel.dataController.patterns.indexOf(pattern), false);
            }
        });

        this.buttonPanel.addButton(button);
        this.toEnd();
    }


    /**
     * 選取特定 pattern
     * @param index pattern 的 index
     * @param selected_TF 選取與否
     * @return 成功設定與否
     * @apiNote 點擊按鈕時就會自動選取了，不用再 set 一次
     */
    public boolean setSelected(int index, boolean selected_TF) {
        return this.buttonPanel.setSelected(this.getButton(index), selected_TF);
    }


    /**
     * 從 PatternsPanel 內移除指定的 button
     * @param index 要刪除的 button 的索引值 (索引值就是第幾個加進去的 button)
     */
    public void deleteButton(int index) {
        this.buttonPanel.deleteButton(index);
    }


    /**
     * 清空 button
     */
    public void deleteAllButton() {
        this.buttonPanel.deleteAllButton();
    }
}
