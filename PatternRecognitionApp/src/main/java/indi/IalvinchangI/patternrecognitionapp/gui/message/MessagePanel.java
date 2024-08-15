package indi.IalvinchangI.patternrecognitionapp.gui.message;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import indi.IalvinchangI.patternrecognitionapp.gui.MainFrame;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.GUITools;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.DecorativeButton;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.CenteredComponentPanel;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.NormalBoxPanel;


/**
 * 讓使用者調整設定的頁面，屬於 {@code MainFrame}
 * @author IalvinchangI
 */
public class MessagePanel extends CenteredComponentPanel {

    private final static int BUTTON_WIDTH = 80;
    private final static int BUTTON_HEIGHT = 45;

    private MainFrame window = null;

    public MessagePanel(MainFrame window) {
        super(new NormalBoxPanel(MIN_WINDOW_WIDTH - 100, MIN_WINDOW_HEIGHT - 100));
        this.innerPanel = (NormalBoxPanel) this.comp;

        this.window = window;


        // background
        // this.backgroundImage = new BufferedImage(1, 1, BufferedImage.TYPE_3BYTE_BGR);
        // Graphics2D g2d = this.backgroundImage.createGraphics();

        // g2d.setColor(PRIMARY_BACKGROUND_COLOR);
        // g2d.fillRect(0, 0, 1, 1);

        // g2d.dispose();

        this.addComponentListener(new ComponentAdapter() {  // repaint background when change the size of the window
            @Override
            public void componentResized(ComponentEvent e) {
                if (window.outerChangePanel.getCurrentPage().equals(window.MESSAGE_PAGE_NAME)) {
                    window.outerChangePanel.showPage(previousPage);
                    setBackgroundImage();
                    window.outerChangePanel.showPage(window.MESSAGE_PAGE_NAME);
                    repaint();
                }
            }
        });


        // click background
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentMessage.clickCancel();
                closeMessage();
            }
        });
        this.innerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // nothing
                // catch the event
            }
        });


        // new
        this.confirmButton = new DecorativeButton(BUTTON_WIDTH, BUTTON_HEIGHT);
        this.cancelButton = new DecorativeButton(BUTTON_WIDTH, BUTTON_HEIGHT);

        this.settingComponents();

        this.addingComponents();
    }

    private NormalBoxPanel innerPanel = null;
    private DecorativeButton confirmButton = null;
    private DecorativeButton cancelButton = null;

    /** 背景 */
    private BufferedImage backgroundImage = null;

    /** 模糊截圖用 */
    private static final ConvolveOp BLUR_OPERATION = new ConvolveOp(
        new Kernel(3, 3, new float[] {
            1/20f, 1/20f, 1/20f, 
            1/20f, 1/5f , 1/20f, 
            1/20f, 1/20f, 1/20f
        }), ConvolveOp.EDGE_NO_OP, null
    );


    private void settingComponents() {
        this.innerPanel.setLayout(new GridBagLayout());

        this.confirmButton.setBackground(this.innerPanel.boxColor);
        this.confirmButton.buttonColor = PRIMARY_BACKGROUND_COLOR;
        this.confirmButton.setFont(CONTENT_FONT);
        this.confirmButton.setText("確認");
        this.cancelButton.setBackground(this.innerPanel.boxColor);
        this.cancelButton.buttonColor = PRIMARY_BACKGROUND_COLOR;
        this.cancelButton.setFont(CONTENT_FONT);
        this.cancelButton.setText("取消");

        this.confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMessage.clickConfirm();
                closeMessage();
            }
        });
        this.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMessage.clickCancel();
                closeMessage();
            }
        });
    }


    private void addingComponents() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 0, 10);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        this.innerPanel.add(this.confirmButton, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.EAST;
        this.innerPanel.add(this.cancelButton, constraints);
    }


    /** 現在的 message */
    private ShowableMessagePanel currentMessage = null;

    /** 之前 MainFrame 顯示的頁面 */
    private String previousPage = null;

    /**
     * 顯示訊息
     * @param message 要顯示的訊息
     * 
     * @throws NullPointerException {@code message} can not be null.
     * @throws IllegalArgumentException The message type does not exist.
     */
    public void showMessage(ShowableMessagePanel message) {
        if (message == null) {
            throw new NullPointerException("message can not be null.");
        }
        
        // show button ?
        switch (message.getMessageType()) {
            case ShowableMessagePanel.DEFAULT_MESSAGE:
                this.confirmButton.setVisible(false);
                this.cancelButton.setVisible(false);
                break;

            case ShowableMessagePanel.CONFIRM_MESSAGE:
                this.confirmButton.setVisible(true);
                this.cancelButton.setVisible(true);
                break;
        
            default:
                throw new IllegalArgumentException("The message type does not exist.");
        }

        // store
        this.currentMessage = message;
        this.previousPage = this.window.outerChangePanel.getCurrentPage();

        // background
        this.setBackgroundImage();

        this.innerPanel.setPreferredSize(new Dimension(
            Math.max(message.getPreferredSize().width + 50, BUTTON_WIDTH * 2 + 40), 
            message.getPreferredSize().height + BUTTON_HEIGHT + 50
        ));

        this.addMessage(message);

        this.window.outerChangePanel.showPage(this.window.MESSAGE_PAGE_NAME);
    }

    private void setBackgroundImage() {
        BufferedImage backgroundImage = new BufferedImage(
            window.getContentPane().getSize().width, 
            window.getContentPane().getSize().height, 
            BufferedImage.TYPE_3BYTE_BGR
        );
        Graphics2D g2d = backgroundImage.createGraphics();
        this.window.getContentPane().paintAll(g2d);
        g2d.dispose();

        this.backgroundImage = BLUR_OPERATION.filter(backgroundImage, null);
    }

    private void addMessage(ShowableMessagePanel message) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        this.innerPanel.add(message, constraints);

        revalidate();
    }


    /**
     * 關閉 message
     * @apiNote 在還未 showMeesage 時呼叫此方法不會有任何影響
     */
    public void closeMessage() {
        if (this.previousPage == null) {
            return;
        }

        this.currentMessage.closeMessage();
        this.innerPanel.remove(this.currentMessage);
        this.currentMessage = null;

        this.window.outerChangePanel.showPage(this.previousPage);
        this.previousPage = null;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
    
        g2d.drawImage(GUITools.getScaledImage(this.backgroundImage, this.getSize().width, this.getSize().height), 0, 0, null);

        // delete g2d
        g2d.dispose();
    }
}
