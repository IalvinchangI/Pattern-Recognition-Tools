package indi.IalvinchangI.patternrecognitionapp.gui.tools.panel;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;


/**
 * 放置多個 BoxPanel 的 panel
 * @author IalvinchangI
 */
public class MultiBoxPanel extends TransparentPanel {
    
    /** 沿著 X軸 排 */
    public static final int X_AXIS = BoxLayout.X_AXIS;

    /** 沿著 Y軸 排 */
    public static final int Y_AXIS = BoxLayout.Y_AXIS;

    
    /**
     * 創造新的 MultiBoxPanel
     * @param axis 擺放的軸
     */
    public MultiBoxPanel(int axis) {
        this(axis, 0);
    }
    
    /**
     * 創造新的 MultiBoxPanel
     * @param axis 擺放的軸
     * @param sep  按鈕間的間隔
     */
    public MultiBoxPanel(int axis, int sep) {
        this.setLayout(new BoxLayout(this, axis));

        this.sep = sep;
        this.panels = new ArrayList<>();
    }

    /** BoxPanel 間的間隔 */
    private int sep = 0;
    
    private ArrayList<BoxPanel> panels = null;


    /**
     * 取得 panel 數量
     * @return panel 數量
     */
    public int getPanelCount() {
        return this.panels.size();
    }

    /**
     * 取得特定 panel
     * @param index 特定 panel 的索引值
     * @return 特定 panel or null
     */
    public BoxPanel getPanel(int index) {
        if (index < 0 || this.getPanelCount() <= index) {
            return null;
        }
        return this.panels.get(index);
    }


    /**
     * 新增 panel 並設定 要觸發的動作
     * @param panel 要新增的 panel
     */
    public void addPanel(BoxPanel panel) {
        Dimension preferredSize = panel.getPreferredSize();
        panel.setMinimumSize(preferredSize);
        panel.setMaximumSize(preferredSize);
        
        this.panels.add(panel);

        // add sep
        if (this.sep != 0 && this.getComponentCount() != 0) {
            if (((BoxLayout) this.getLayout()).getAxis() == X_AXIS) {
                this.add(Box.createHorizontalStrut(this.sep));
            }
            else if (((BoxLayout) this.getLayout()).getAxis() == Y_AXIS) {
                this.add(Box.createVerticalStrut(this.sep));
            }
        }
        this.add(panel);

        this.revalidate();
        this.repaint();
    }
}
