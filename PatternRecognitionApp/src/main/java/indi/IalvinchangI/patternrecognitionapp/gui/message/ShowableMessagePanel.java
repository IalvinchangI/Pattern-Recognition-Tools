package indi.IalvinchangI.patternrecognitionapp.gui.message;

import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.TransparentPanel;

/**
 * 可做為 message 傳入 MessagePanel 的 message
 * @author IalvinchangI
 */
public abstract class ShowableMessagePanel extends TransparentPanel {

    /** 只有文字的 message */
    public static final int DEFAULT_MESSAGE = 0;

    /** 有確認按鈕的 message */
    public static final int CONFIRM_MESSAGE = 1;

    
    /**
     * 取得 message 的種類
     * @return message 的種類
     */
    public abstract int getMessageType();

    protected int messageType = DEFAULT_MESSAGE;


    /** 按下 confirm 時要做的事 */
    public void clickConfirm() {}


    /** 按下 cancel 時要做的事 */
    public void clickCancel() {}


    /**
     * 關閉 meesage 時要做的事
     */
    public abstract void closeMessage();
}
