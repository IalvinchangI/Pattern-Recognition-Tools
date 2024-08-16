package indi.IalvinchangI.patternrecognitionapp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;

import indi.IalvinchangI.patternrecognitionapp.ResourceConstant;
import indi.IalvinchangI.patternrecognitionapp.gui.message.ShowableMessagePanel;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.GraphButton;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.ChangeablePanel;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.TransparentPanel;


/**
 * 用於使用教學的頁面，屬於 {@code MainFrame}
 * <p>
 * 只是存在 MainFrame 而已，不會 add 在 MainFrame
 * @author IalvinchangI
 */
public class TeachingPanel extends ShowableMessagePanel {

    public static final int PANEL_WIDTH = 500;
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
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        this.contentPanel = new ChangeablePanel();
        this.leftButton = new GraphButton(ResourceConstant.getImagePath(ResourceConstant.ADD_PATTERN_IMAGE), 60);
        this.rightButton = new GraphButton(ResourceConstant.getImagePath(ResourceConstant.SAVE_IMAGE), 60);

        this.teachingPanels = new TransparentPanel[] {
            null, 
            new TransparentPanel(), 
            new TransparentPanel()
        };

        // content
        this.initTeaching(this.teachingPanels[TEACH_SETTING], 
            "<html>" + 
            "<body style='font-family: " + SUBTITLE_FONT.getName() + "; '>" + 
                "<h1> 設定 </h1>" + 
                "<dir style='font-size: " + SUBTITLE_FONT.getSize() + ";'>" + 
                    "<strong> 儲存位置 </strong>" + 
                    "<dir style='font-size: " + CONTENT_FONT.getSize() + ";'>" + 
                        "<strong> 這是用來設定「要把圖檔存到哪個資料夾」的 </strong>" + 
                        "<ul style='margin-left: 0'>" + 
                            "<li> 左邊的按鈕可以「選擇儲存位置」。 </li> " + 
                            "<li> 最右邊的按鈕可以「開啟資料夾」，方便你找到儲存的圖檔。 </li>" + 
                        "</ul>" + 
                    "</dir>" + 
                "</dir>" + 
            "</body>" + 
            "</html>"
        );
        this.initTeaching(this.teachingPanels[TEACH_DRAWING], 
            "<html>" + 
            "<body style='font-family: " + SUBTITLE_FONT.getName() + "; '>" + 
                "<h1> 繪圖 </h1>" + 
                "<dir style='font-size: " + CONTENT_FONT.getSize() + ";'>" + 
                    "<strong> 這個頁面是用來「畫圖、上標籤和存檔」的 </strong>" + 
                    "<ul style='margin-left: 0'>" + 
                        "<li> 左邊白色的那一大塊是畫布，用來「畫圖形」的。<br> 另外，這個畫布很特別，他只能畫一筆，所以所有的圖形只能「一筆完成」。 </li> " + 
                        "<li> 右邊那一排有圖有文字的按鈕是標籤，點選後可以幫左邊畫好的圖形「上標籤」。 </li>" + 
                        "<li> 畫布下面那一排按鈕是你之前畫過的圖，你可以點擊然後修改之前畫的圖檔。<br> 最右邊那顆不會動的按鈕是「新增圖檔的按鈕」。 </li>" + 
                        "<li> 標籤下面那一顆比較大的按鈕是「存檔按鈕」（他在新增圖檔的按鈕右邊）。<br> 在按下存檔按鈕前，還請確認每個圖檔的圖和標籤是正確的。 </li>" + 
                    "</ul>" + 
                    "<p> 如果你有圖檔還沒完成，在按下「新增圖檔的按鈕」或「存檔按鈕」時會跳出警告 </p>" + 
                "</dir>" + 
            "</body>" + 
            "</html>"
        );

        this.add(leftButton);
        this.add(Box.createHorizontalStrut(10));
        this.add(contentPanel);
        this.add(Box.createHorizontalStrut(10));
        this.add(rightButton);
        
        this.contentPanel.add(this.teachingPanels[TEACH_SETTING], TEACHING_SETTING_PAGE_NAME);
        this.contentPanel.add(this.teachingPanels[TEACH_DRAWING], TEACHING_DRAWING_PAGE_NAME);

        this.leftButton.setVisible(false);
        this.rightButton.setVisible(false);

        this.leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("leftButton");
                // TODO
            }
        });
        this.rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("rightButton");
                // TODO
            }
        });
        this.contentPanel.showPage(TEACHING_DRAWING_PAGE_NAME);
    }

    private ChangeablePanel contentPanel = null;

    private GraphButton leftButton = null;
    private GraphButton rightButton = null;

    private TransparentPanel[] teachingPanels = null;

    private static final String TEACHING_SETTING_PAGE_NAME = "teaching_setting";
    private static final String TEACHING_DRAWING_PAGE_NAME = "teaching_drawing";


    private void initTeaching(TransparentPanel panel, String content) {
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        JEditorPane contentArea = new JEditorPane();
        contentArea.setContentType("text/html");
        contentArea.setText(content);
        contentArea.setEditable(false);
        
        panel.add(contentArea, BorderLayout.CENTER);
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
    protected void setBackgroundColor(Color color) {
        this.setBackground(color);
        this.contentPanel.setBackground(color);
        this.leftButton.setBackground(color);
        this.rightButton.setBackground(color);

        for (TransparentPanel panel : this.teachingPanels) {
            if (panel == null) {
                continue;
            }
            panel.getComponent(0).setBackground(color);
        }
    }


    @Override
    protected void closeMessage() {
        if (this.teachContent == TEACH_ALL) {
            this.leftButton.setVisible(false);
            this.rightButton.setVisible(false);
        }
        this.teachContent = 0;
        // TODO
    }
}
