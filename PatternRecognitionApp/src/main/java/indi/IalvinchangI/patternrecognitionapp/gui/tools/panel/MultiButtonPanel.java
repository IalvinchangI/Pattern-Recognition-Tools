package indi.IalvinchangI.patternrecognitionapp.gui.tools.panel;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;

import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.EditableButton;

/**
 * 放置多個 button 的 panel，另外，一次只能選取其中一個
 * @author IalvinchangI
 */
public class MultiButtonPanel extends TransparentPanel {

    public static final int X_AXIS = BoxLayout.X_AXIS;
    public static final int Y_AXIS = BoxLayout.Y_AXIS;

    
    /**
     * 創造新的 MultiButtonPanel
     * @param axis 擺放的軸
     */
    public MultiButtonPanel(int axis) {
        this(axis, 0);
    }
    
    /**
     * 創造新的 MultiButtonPanel
     * @param axis 擺放的軸
     * @param sep  按鈕間的間隔
     */
    public MultiButtonPanel(int axis, int sep) {
        this.setLayout(new BoxLayout(this, axis));

        this.sep = sep;
        this.buttons = new ArrayList<>();
    }

    /** 按鈕間的間隔 */
    private int sep = 0;
    
    private ArrayList<EditableButton> buttons = null;


    /**
     * 新增 button 並設定 要觸發的動作
     * @param button 要新增的 button
     * @param listener 按下按鈕後，需要觸發的動作
     */
    public void addButton(EditableButton button, ActionListener listener) {
        if (listener != null) {
            button.addActionListener(listener);
        }

        this.buttons.add(button);

        if (this.sep != 0 && this.getComponentCount() != 0) {
            if (((BoxLayout) this.getLayout()).getAxis() == X_AXIS) {
                this.add(Box.createHorizontalStrut(this.sep));
            }
            else if (((BoxLayout) this.getLayout()).getAxis() == Y_AXIS) {
                this.add(Box.createVerticalStrut(this.sep));
            }
        }
        this.add(button);

        this.revalidate();
        this.repaint();
    }
}
