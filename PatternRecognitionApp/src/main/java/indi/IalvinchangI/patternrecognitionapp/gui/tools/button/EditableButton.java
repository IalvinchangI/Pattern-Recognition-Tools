package indi.IalvinchangI.patternrecognitionapp.gui.tools.button;

import javax.swing.JButton;


/**
 * 乾淨的 Button
 * @author IalvinchangI
 */
public abstract class EditableButton extends JButton {
    /**
     * 把預設要繪製的東西清掉
     */
    public EditableButton() {
        setContentAreaFilled(false);
        setFocusPainted(false);  // 不繪製焦點框
        setBorderPainted(false);  // 不繪製按鈕邊框
    }
}
