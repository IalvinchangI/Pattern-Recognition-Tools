package indi.IalvinchangI.patternrecognitionapp.gui.message;

import java.awt.BorderLayout;

import javax.swing.JLabel;

/**
 * 只有文字的 message
 * @author IalvinchangI
 */
public class TextMessagePanel extends ShowableMessagePanel {

    public TextMessagePanel(String message) {
        this.setLayout(new BorderLayout());
        this.messageType = DEFAULT_MESSAGE;

        JLabel label = new JLabel(message);
        label.setFont(SUBTITLE_FONT);
        this.add(label, BorderLayout.CENTER);
    }


    @Override
    public int getMessageType() {
        return this.messageType;
    }
    
    /**
     * 設定 message 的種類
     * @param messageType message 的種類
     * @see ShowableMessagePanel
     */
    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }


    @Override
    public void closeMessage() {}
}
