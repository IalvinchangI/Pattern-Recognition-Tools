package indi.IalvinchangI.patternrecognitionapp.gui.main;

import java.awt.Dimension;

import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.EditableButton;

/**
 * 切換頁面用的 Button
 * @author IalvinchangI
 */
public class ChangePageButton extends EditableButton {
    public final int MIN_HEIGHT = 30;

    public ChangePageButton() {
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, MIN_HEIGHT));
        // TODO

    }
}
