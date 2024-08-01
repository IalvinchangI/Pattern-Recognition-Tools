package indi.IalvinchangI.patternrecognitionapp.gui.tools.panel;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;


/**
 * 自訂的 ScrollPane
 * @author IalvinchangI
 */
public class NormalScrollPanel extends TransparentPanel {
    
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
     * @param view
     */
    protected void addComponent(Component view) {
        if (this.scrollPanel == null) {
            this.scrollPanel = new JScrollPane(view);
            this.scrollPanel.getVerticalScrollBar().setUI(new NormalScrollBarUI());
            this.scrollPanel.getHorizontalScrollBar().setUI(new NormalScrollBarUI());

            this.add(this.scrollPanel, BorderLayout.CENTER);
        }
    }
}


/**
 * NormalScrollPanel 的 ScrollBar 外觀設計
 * @author IalvinchangI
 */
class NormalScrollBarUI extends BasicScrollBarUI {
    public NormalScrollBarUI() {}
    
}
