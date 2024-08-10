package indi.IalvinchangI.patternrecognitionapp.gui.tools.panel;


import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;


/**
 * 可切換頁面的 Panel
 * @author IalvinchangI
 */
public class ChangeablePanel extends TransparentPanel {

    /** 儲存 頁面名稱 */
    private ArrayList<String> pageNames = null;
    
    /** 取得 所有頁面名稱 */
    public ArrayList<String> getPageNames() {
        return this.pageNames;
    }


    /**
     * 製作可切換頁面的 Panel
     */
    public ChangeablePanel() {
        this.setLayout(new CardLayout());
        this.pageNames = new ArrayList<>();
    }


    /**
     * 切換至名為 pageName 的頁面
     * @param pageName 要切換的頁面名
     * @return 是否切換成功
     */
    public boolean showPage(String pageName) {
        if (this.pageNames.contains(pageName)) {
            ((CardLayout) this.getLayout()).show(this, pageName);

            return true;
        }
        return false;
    }
    

    /**
     * 把頁面加入 ChangeablePanel，方便切換
     * @param comp      加入的頁面
     * @param pageName  頁面名
     */
    public void add(Component comp, String pageName) {
        super.add(comp, pageName);
        this.pageNames.add(pageName);
    }


    /**
     * 設定一個 ActionListener，他的 actionPerformed 是將頁面切換到名為 pageName 的頁面，最後回傳設定好的 ActionListener
     * 
     * @param pageName 要切換的頁面名
     * @return
     * 如果 pageName 在 ChangeablePanel 裡，則回傳 ActionListener (ChangeablePanel 內有儲存此頁面)
     * <p>
     * 如果 pageName 不在 ChangeablePanel 裡，則回傳 null (ChangeablePanel 內沒儲存此頁面)
     * 
     * @apiNote 因為會先檢查在不在 ChangeablePanel 裡，所以要先 add 進去
     */
    public ActionListener createChangePagePerformed(String pageName) {
        if (this.pageNames.contains(pageName)) {
            CardLayout layout = (CardLayout) this.getLayout();
            TransparentPanel panel = this;
            return new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    layout.show(panel, pageName);
                }
            };
        }
        return null;
    }
}
