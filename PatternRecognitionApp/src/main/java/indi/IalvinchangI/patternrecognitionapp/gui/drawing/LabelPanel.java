package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import indi.IalvinchangI.patternrecognitionapp.gui.MainFrame;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.GraphButton;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.NormalButton;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.MultiButtonPanel;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.NormalScrollPanel;


/**
 * 選擇 label 的地方
 * @author IalvinchangI
 */
public class LabelPanel extends NormalScrollPanel {

    public static final Font LABEL_FONT = MainFrame.SUBTITLE_FONT;

    public static final int BUTTON_WIDTH = 100;

    public static final int BUTTON_HEIGHT = 40;


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
    public LabelPanel(String[] label, String[] code) {
        this();

        if (label.length != code.length) {
            throw new IllegalArgumentException("The length of label and code is differnet. ( " + label.length + " != " + code.length + " )");
        }

        for (int i = 0; i < label.length; i++) {
            this.addButton(label[i], code[i]);
        }
    }

    private MultiButtonPanel buttonPanel = null;

    private HashMap<String, GraphButton> buttons = null;


    /**
     * @param label button 的名字
     * @param code  label 對應的編碼
     */
    public void addButton(String label, String code) {
        GraphButton button = new NormalButton(LabelPanel.BUTTON_WIDTH, LabelPanel.BUTTON_HEIGHT);
        button.canSelectTF = true;

        button.setBackground(this.getBackground());
        button.setText(label);
        button.setFont(LabelPanel.LABEL_FONT);
        button.setActionCommand(code);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawingPanel drawingPanel = (DrawingPanel) getParent();
                drawingPanel.dataController.fillData(e.getActionCommand());
            }
        });

        this.buttonPanel.addButton(button);
        this.buttonPanel.setSelected(button, false);
        this.buttons.put(code, button);
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
