package indi.IalvinchangI.patternrecognitionapp.gui.tools.panel;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.EditableButton;

import indi.IalvinchangI.patternrecognitionapp.util.AutoDropQueue;


/**
 * 放置多個 button 的 panel，另外，一次只能選取其中一個
 * @author IalvinchangI
 */
public class MultiButtonPanel extends TransparentPanel {

    /** 沿著 X軸 排 */
    public static final int X_AXIS = BoxLayout.X_AXIS;

    /** 沿著 Y軸 排 */
    public static final int Y_AXIS = BoxLayout.Y_AXIS;

    
    /**
     * 創造新的 MultiButtonPanel
     * @param axis 擺放的軸
     */
    public MultiButtonPanel(int axis) {
        this(axis, 0);
    }
    
    /**
     * 創造新的 MultiButtonPanel
     * @param axis 擺放的軸
     * @param sep  按鈕間的間隔
     */
    public MultiButtonPanel(int axis, int sep) {
        this.setLayout(new BoxLayout(this, axis));

        this.sep = sep;
        this.buttonGroup = new ButtonGroup();
        this.buttons = new ArrayList<>();
        this.previousSelectedButton = new AutoDropQueue<>(2);
    }

    /** 按鈕間的間隔 */
    private int sep = 0;

    private ButtonGroup buttonGroup = null;
    
    private ArrayList<EditableButton> buttons = null;

    private AutoDropQueue<EditableButton> previousSelectedButton = null;


    /**
     * 取得 button 數量
     * @return button 數量
     */
    public int getButtonCount() {
        return this.buttons.size();
    }

    /**
     * 取得特定 button
     * @param index 特定 button 的索引值
     * @return 特定 button or null
     */
    public EditableButton getButton(int index) {
        if (index < 0 || this.getButtonCount() <= index) {
            return null;
        }
        return this.buttons.get(index);
    }


    /**
     * 取得上一個選取的 button
     * @return 上一個選取的 button
     */
    public EditableButton getPreviousSelectedButton() {
        return this.previousSelectedButton.peek();
    }


    /**
     * 新增 button 並設定 要觸發的動作
     * @param button 要新增的 button
     */
    public void addButton(EditableButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectAndDeselect((EditableButton) e.getSource());
            }
        });
        
        this.buttons.add(button);
        this.buttonGroup.add(button);
        this.setSelectedWithoutCheck(button, true);

        if (((BoxLayout) this.getLayout()).getAxis() == X_AXIS) {
            button.setAlignmentY(Component.CENTER_ALIGNMENT);
            if (this.sep != 0 && this.getComponentCount() != 0) {
                this.add(Box.createHorizontalStrut(this.sep));
            }
        }
        else if (((BoxLayout) this.getLayout()).getAxis() == Y_AXIS) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            if (this.sep != 0 && this.getComponentCount() != 0) {
                this.add(Box.createVerticalStrut(this.sep));
            }
        }


        
        this.add(button);

        this.revalidate();
        this.repaint();
    }


    /**
     * 設定 button 的選取狀態
     * @param button 要設定的 button
     * @param selected_TF 狀態
     */
    public void setSelected(EditableButton button, boolean selected_TF) {
        if (this.buttons.contains(button) == false) {
            return;
        }

        this.setSelectedWithoutCheck(button, selected_TF);
    }

    private void setSelectedWithoutCheck(EditableButton button, boolean selected_TF) {
        if (selected_TF == false) {
            this.clearSelection();
        }
        else {
            this.buttonGroup.setSelected(button.getModel(), selected_TF);
            this.selectAndDeselect(button);
        }
    }

    /**
     * 選取 button 並取消 previousSelectedButton 的選取
     * @param button 要選取的 button
     */
    private void selectAndDeselect(EditableButton button) {
        this.previousSelectedButton.add(button);

        if (this.previousSelectedButton.isFull()) {
            this.getPreviousSelectedButton().deselect();
        }
        button.select();
    }


    /**
     * 清除選擇的按鈕
     */
    public void clearSelection() {
        this.buttonGroup.clearSelection();
        if (this.previousSelectedButton.size() > 0) {
            this.previousSelectedButton.peekNewest().deselect();
        }
    }


    /**
     * 清空 MultiButtonPanel
     */
    public void deleteAllButton() {
        Iterator<EditableButton> buttonsIterator = buttons.iterator();
        while (buttonsIterator.hasNext()) {
            EditableButton button = buttonsIterator.next();
            this.buttonGroup.remove(button);
            this.remove(button);
            buttonsIterator.remove();
        }
        this.removeAll();  // remove box
        revalidate();
        repaint();

        this.previousSelectedButton.clear();
    }
}
