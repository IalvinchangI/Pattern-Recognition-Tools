package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import indi.IalvinchangI.patternrecognitionapp.gui.MainFrame;
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

    public static final Font LABEL_FONT = MainFrame.SUBTITLE_FONT;

    public static final int BUTTON_WIDTH = 100;

    public static final int BUTTON_HEIGHT = 50;


    /**
     * 創造新的 LabelPanel
     */
    public LabelPanel() {
        super();
        this.setBackground(Color.MAGENTA);

        this.buttonPanel = new MultiButtonPanel(MultiButtonPanel.Y_AXIS, 10);
        this.buttons = new HashMap<>();

        this.addComponent(this.buttonPanel);
        this.buttonPanel.setBackground(this.getBackground());
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
    public void loadLabel() {
        DrawingPanel drawingPanel = (DrawingPanel) getParent();
        this.buttonPanel.setSelected(
            this.buttons.get(
                drawingPanel.dataController.getPattern().getLabel()
            ), 
            true
        );
    }


    /**
     * 清除選擇的按鈕
     */
    public void clearSelection() {
        this.buttonPanel.clearSelection();
    }
}
