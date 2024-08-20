package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import indi.IalvinchangI.patternrecognitionapp.ResourceConstant;
import indi.IalvinchangI.patternrecognitionapp.data.PatternData;
import indi.IalvinchangI.patternrecognitionapp.gui.message.TextMessagePanel;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.GraphButton;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.NormalButton;


/**
 * 顯示 Pattern 的按鈕
 * @author IalvinchangI
 */
public class PatternButton extends NormalButton {

    private PatternData pattern = null;

    public PatternButton(PatternData pattern, int width, DrawingPanel drawingPanel) {
        super(pattern.toImage(), width);
        this.pattern = pattern;
        
        this.canSelectTF = true;
        this.setIconMargin(9);


        // delete button
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder());

        this.deleteButton = new GraphButton(ResourceConstant.getImagePath(ResourceConstant.DELETE_IMAGE), 17);
        this.deleteButton.setIconMargin(0);
        this.deleteButton.setBackground(new Color(0, 0, 0, 0));
        this.deleteButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.add(this.deleteButton);

        this.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextMessagePanel confirmMessage = new TextMessagePanel("確定要刪除嗎") {
                    @Override
                    protected void clickConfirm() {
                        drawingPanel.deleteButton(drawingPanel.dataController.patterns.indexOf(pattern));
                    }
                };
                confirmMessage.setMessageType(TextMessagePanel.CONFIRM_MESSAGE);
                drawingPanel.window.messagePanel.showMessage(confirmMessage);
            }
        });
        this.deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private GraphButton deleteButton = null;


    public void reloadPattern() {
        this.setIcon(pattern.toImage());
    }
}
