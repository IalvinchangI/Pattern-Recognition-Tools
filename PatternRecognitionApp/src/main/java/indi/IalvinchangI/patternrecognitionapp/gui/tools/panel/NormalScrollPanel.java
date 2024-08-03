package indi.IalvinchangI.patternrecognitionapp.gui.tools.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicScrollBarUI;


/**
 * 自訂的 ScrollPane
 * @author IalvinchangI
 */
public class NormalScrollPanel extends TransparentPanel {

    public static final int MARGIN = 5;

    public static final int SCROLL_BAR_WIDTH = NormalScrollBarUI.SCROLL_BAR_WIDTH;

    
    /**
     * @implNote 創造後，一定要呼叫 addComponent
     */
    protected NormalScrollPanel() {
        this.setLayout(new BorderLayout());
    }

    public NormalScrollPanel(Component view) {
        this();

        this.addComponent(view);
    }

    private JScrollPane scrollPanel = null;


    /**
     * 加入要捲動的內容
     * @param view 要捲動的內容
     */
    protected void addComponent(Component view) {
        if (this.scrollPanel == null) {
            this.scrollPanel = new JScrollPane(view);

            this.scrollPanel.getVerticalScrollBar().setUI(new NormalScrollBarUI());
            this.scrollPanel.getHorizontalScrollBar().setUI(new NormalScrollBarUI());

            this.add(this.scrollPanel, BorderLayout.CENTER);
            
            this.setBackground(this.getBackground());
            this.scrollPanel.setBorder(BorderFactory.createEmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));

            this.scrollPanel.getVerticalScrollBar().setUnitIncrement(25);
            this.scrollPanel.getVerticalScrollBar().setBlockIncrement(50);
            this.scrollPanel.getHorizontalScrollBar().setUnitIncrement(25);
            this.scrollPanel.getHorizontalScrollBar().setBlockIncrement(50);
        }
    }

    
    /**
     * 設定垂直 scroll bar 如何顯示
     * @param policy
     * {@code ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER}, 
     * {@code ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED}, 
     * {@code ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS}
     */
    public void setVerticalScrollBarPolicy(int policy) {
        if (this.scrollPanel != null) {
            this.scrollPanel.setVerticalScrollBarPolicy(policy);
        }
    }

    /**
     * 設定水平 scroll bar 如何顯示
     * @param policy
     * {@code ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER}, 
     * {@code ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED}, 
     * {@code ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS}
     */
    public void setHorizontalScrollBarPolicy(int policy) {
        if (this.scrollPanel != null) {
            this.scrollPanel.setHorizontalScrollBarPolicy(policy);
        }
    }


    /**
     * 拉到最底
     */
    public void toEnd() {
        if (this.scrollPanel != null) {
            SwingUtilities.invokeLater(() -> {
                scrollPanel.getVerticalScrollBar().setValue(scrollPanel.getViewport().getPreferredSize().height);
                scrollPanel.getHorizontalScrollBar().setValue(scrollPanel.getViewport().getPreferredSize().width);
            });
        }
    }


    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        if (this.scrollPanel != null) {
            this.scrollPanel.setBackground(bg);
            this.scrollPanel.getVerticalScrollBar().setBackground(bg);
            this.scrollPanel.getHorizontalScrollBar().setBackground(bg);
        }
    }
}


/**
 * NormalScrollPanel 的 ScrollBar 外觀設計
 * @author IalvinchangI
 */
class NormalScrollBarUI extends BasicScrollBarUI {

    public final static int SCROLL_BAR_WIDTH = 5;

    public NormalScrollBarUI() {}


    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = Color.GREEN;
    }


    @Override
    protected Dimension getMinimumThumbSize() {
        return new Dimension(SCROLL_BAR_WIDTH, SCROLL_BAR_WIDTH);
    }


    @Override
    public Dimension getPreferredSize(JComponent c) {
        return (scrollbar.getOrientation() == JScrollBar.VERTICAL)
                ? new Dimension(SCROLL_BAR_WIDTH, 48)
                : new Dimension(48, SCROLL_BAR_WIDTH);
    }


    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(this.thumbColor);
        if (thumbBounds.height > thumbBounds.width) {
            g2d.fillRoundRect(
                thumbBounds.x, thumbBounds.y, 
                SCROLL_BAR_WIDTH, thumbBounds.height, 
                SCROLL_BAR_WIDTH * 2, SCROLL_BAR_WIDTH * 2
            );
        }
        else {
            g2d.fillRoundRect(
                thumbBounds.x, thumbBounds.y, 
                thumbBounds.width, SCROLL_BAR_WIDTH, 
                SCROLL_BAR_WIDTH * 2, SCROLL_BAR_WIDTH * 2
            );
        }

        g2d.dispose();
    }


    @Override
    protected void 	paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(this.trackColor);
        if (trackBounds.height > trackBounds.width) {
            g2d.fillRect(
                trackBounds.x, trackBounds.y, 
                SCROLL_BAR_WIDTH, trackBounds.height
            );
        }
        else {
            g2d.fillRect(
                trackBounds.x, trackBounds.y, 
                trackBounds.width, SCROLL_BAR_WIDTH
            );
        }

        g2d.dispose();
    }


    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension();
            }
        };
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension();
            }
        };
    }
}
