package indi.IalvinchangI.patternrecognitionapp.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import indi.IalvinchangI.patternrecognitionapp.ResourceConstant;
import indi.IalvinchangI.patternrecognitionapp.gui.message.ShowableMessagePanel;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.GraphButton;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.ChangeablePanel;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.TransparentPanel;


/**
 * 用於使用教學的頁面，屬於 {@code MainFrame}
 * @author IalvinchangI
 */
public class TeachingPanel extends ShowableMessagePanel {

    public static final int PANEL_WIDTH = 600;
    public static final int PANEL_HEIGHT = 400;


    /** 全部顯示 */
    public static final int TEACH_ALL = -1;

    /** 只顯示 setting */
    public static final int TEACH_SETTING = 1;

    /** 只顯示 drawing */
    public static final int TEACH_DRAWING = 2;


    /**
     * 造出 TeachingPanel
     */
    public TeachingPanel() {
        this.setLayout(new BorderLayout());
        this.messageType = CONFIRM_MESSAGE;
        this.setBackground(PRIMARY_BACKGROUND_COLOR);
        
        this.contentPanel = new ChangeablePanel();
        this.leftButton = new GraphButton(ResourceConstant.getImagePath(ResourceConstant.ADD_PATTERN_IMAGE), 60);
        this.rightButton = new GraphButton(ResourceConstant.getImagePath(ResourceConstant.SAVE_IMAGE), 60);

        // content
        this.initTeachingSettingPanel();
        this.initTeachingDrawingPanel();

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(leftButton, BorderLayout.WEST);
        this.add(rightButton, BorderLayout.EAST);
        
        // this.leftButton.setVisible(false);
        // this.rightButton.setVisible(false);

        this.leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("leftButton");
            }
        });
        this.rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("rightButton");
            }
        });
        this.contentPanel.showPage(TEACHING_DRAWING_PAGE_NAME);
    }

    private ChangeablePanel contentPanel = null;

    private GraphButton leftButton = null;
    private GraphButton rightButton = null;

    private TransparentPanel teachingSettingPanel = null;
    private TransparentPanel teachingDrawingPanel = null;

    private static final String TEACHING_SETTING_PAGE_NAME = "teaching_setting";
    private static final String TEACHING_DRAWING_PAGE_NAME = "teaching_drawing";


    private void initTeachingSettingPanel() {
        this.teachingSettingPanel = new TransparentPanel();
        this.teachingSettingPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        JTextArea content = new JTextArea();
        content.setFont(CONTENT_FONT);
        content.setText("setting content\n");  // TODO
        content.setEditable(false);
        this.teachingSettingPanel.add(content);

        this.contentPanel.add(this.teachingSettingPanel, TEACHING_SETTING_PAGE_NAME);
    }
    
    private void initTeachingDrawingPanel() {
        this.teachingDrawingPanel = new TransparentPanel();
        this.teachingDrawingPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        JTextArea content = new JTextArea();
        content.setFont(CONTENT_FONT);
        content.setText("drawing content\n");  // TODO
        content.setEditable(false);
        this.teachingDrawingPanel.add(content);

        this.contentPanel.add(this.teachingDrawingPanel, TEACHING_DRAWING_PAGE_NAME);
    }


    /**
     * 設定 TeachingPanel 要顯示的內容
     * @param content 內容
     * {@code TEACH_ALL}, {@code TEACH_SETTING}, {@code TEACH_DRAWING}
     * @see TeachingPanel
     */
    public void setContent(int content) {
        switch (content) {
            case TEACH_ALL:
                break;

            case TEACH_SETTING:
                this.contentPanel.showPage(TEACHING_SETTING_PAGE_NAME);
                break;

            case TEACH_DRAWING:
                this.contentPanel.showPage(TEACHING_DRAWING_PAGE_NAME);
                break;

            default:
                throw new IllegalArgumentException("The value of content is illegal");
        }

        this.teachContent = content;
    }

    private int teachContent = 0;


    @Override
    public int getMessageType() {
        return this.messageType;
    }


    @Override
    public void closeMessage() {
        if (this.teachContent == TEACH_ALL) {
            this.leftButton.setVisible(false);
            this.rightButton.setVisible(false);
        }
        this.teachContent = 0;
        // TODO
    }
}
