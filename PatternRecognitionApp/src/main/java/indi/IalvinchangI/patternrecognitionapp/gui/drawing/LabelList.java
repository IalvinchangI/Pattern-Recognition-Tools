package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListSelectionModel;


/**
 * 選擇 label 的地方
 * @author IalvinchangI
 */
public class LabelList extends JList<String> {

    public LabelList(String[] listData) {
        super(listData);
        
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setCellRenderer(new LabelListCellRenderer());
        this.setPreferredSize(new Dimension(100, 30 * listData.length));
    }
}



class LabelListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        this.setPreferredSize(new Dimension(100, 30));
        if (isSelected == true) {
            this.setBackground(Color.CYAN);
            this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3, true));
        }
        else {
            this.setBackground(list.getBackground());
            this.setBorder(null);
        }

        // TODO
        
        return this;
    }

}
