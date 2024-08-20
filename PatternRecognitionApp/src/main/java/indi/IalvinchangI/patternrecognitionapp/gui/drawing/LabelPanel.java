package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;

import indi.IalvinchangI.patternrecognitionapp.data.PatternData;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.DecorativeButton;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.GraphButton;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.MultiButtonPanel;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.NormalScrollPanel;
import indi.IalvinchangI.patternrecognitionapp.io.LabelToCode;


/**
 * 選擇 label 的地方
 * @author IalvinchangI
 */
class LabelPanel extends NormalScrollPanel {

    public static final Font LABEL_FONT = SUBTITLE_FONT;

    public static final int BUTTON_WIDTH = 180;

    public static final int BUTTON_HEIGHT = 80;

    private static final int BUTTON_SEP = 10;


    /**
     * 創造新的 LabelPanel
     */
    public LabelPanel() {
        super();
        this.setBackground(PRIMARY_BACKGROUND_COLOR);

        this.buttonPanel = new MultiButtonPanel(MultiButtonPanel.Y_AXIS, BUTTON_SEP);
        this.buttons = new HashMap<>();

        this.addComponent(this.buttonPanel);
        this.buttonPanel.setBackground(this.getBackground());

        this.buttonPanel.setBorder(BorderFactory.createEmptyBorder(SCROLL_BAR_WIDTH + 3, 0, 0, 0));
        this.setPreferredSize(new Dimension(BUTTON_WIDTH + (SCROLL_BAR_WIDTH) * 2 + MARGIN * 2, GridCanvasPanel.GRID_COUNT * GridCanvasPanel.GRID_WIDTH));
        this.setMinimumSize(this.getPreferredSize());
    }

    /**
     * 創造新的 LabelPanel 並加入 button
     * @param label button 的名字
     * @param code  label 對應的編碼
     */
    public LabelPanel(LabelToCode[] labelToCodes) {
        this();
        for (int i = 0; i < labelToCodes.length; i++) {
            this.addButton(labelToCodes[i]);
        }
    }

    private MultiButtonPanel buttonPanel = null;

    private HashMap<String, GraphButton> buttons = null;


    /**
     * @param label button 的名字
     * @param code  label 對應的編碼
     */
    public void addButton(LabelToCode l2c) {
        GraphButton button = new DecorativeButton(LabelPanel.BUTTON_WIDTH, LabelPanel.BUTTON_HEIGHT);
        button.canSelectTF = true;

        button.setBackground(this.getBackground());
        button.setIcon(l2c.getImage());
        button.setIconMargin(15);
        button.setText(l2c.label);
        button.setFont(LabelPanel.LABEL_FONT);
        button.setActionCommand(l2c.code);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawingPanel drawingPanel = (DrawingPanel) getParent();
                drawingPanel.dataController.fillData(e.getActionCommand());
            }
        });

        this.buttonPanel.addButton(button);
        this.buttonPanel.setSelected(button, false);
        this.buttons.put(l2c.code, button);
    }


    /**
     * 顯示選取的 label
     */
    public void loadLabel(PatternData pattern) {
        if (pattern.getFinishEditing_TF(PatternData.CHECK_LABEL) == true) {
            GraphButton button = this.buttons.get(pattern.getLabel());
            this.buttonPanel.setSelected(button, true);

            int index = this.buttonPanel.indexOf(button);
            this.setVerticalValue(index * (BUTTON_HEIGHT + BUTTON_SEP));
        }
        else {
            this.buttonPanel.clearSelection();
        }
    }


    /**
     * 清除選擇的按鈕
     */
    public void clearSelection() {
        this.buttonPanel.clearSelection();
    }
}
