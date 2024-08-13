package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import indi.IalvinchangI.patternrecognitionapp.ResourceConstant;
import indi.IalvinchangI.patternrecognitionapp.data.DataController;
import indi.IalvinchangI.patternrecognitionapp.data.SettingData;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.DecorativeButton;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.NormalButton;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.TransparentPanel;
import indi.IalvinchangI.patternrecognitionapp.io.LabelToCode;


/**
 * 讓使用者繪圖的頁面，屬於 {@code MainPanel}
 * @author IalvinchangI
 */
public class DrawingPanel extends TransparentPanel {

    private SettingData settingData = null;

    DataController dataController = null;


    public DrawingPanel(SettingData settingData) {
        // data
        this.settingData = settingData;
        this.dataController = new DataController(this.settingData);
        LabelToCode[] labelToCodes = LabelToCode.readFromJsonResource(ResourceConstant.getResourcePath(ResourceConstant.LABEL2CODE));


        // GUI
        this.setBackground(PRIMARY_BACKGROUND_COLOR);
        this.setLayout(new GridBagLayout());
        
        // new
        this.canvas = new GridCanvasPanel();
        this.labelPanel = new LabelPanel(labelToCodes);
        
        this.patterns = new PatternsPanel();
        this.addPatternButton = new NormalButton(ResourceConstant.getImagePath(ResourceConstant.ADD_PATTERN_IMAGE), PatternsPanel.BUTTON_WIDTH);
        this.saveButton = new DecorativeButton(ResourceConstant.getImagePath(ResourceConstant.SAVE_IMAGE), 100, 65);
        
        // set
        this.labelPanel.setFont(SUBTITLE_FONT);

        this.addNewPattern();
        
        this.addPatternButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addNewPattern() == true) {
                    canvas.clearCanvas();
                    labelPanel.clearSelection();
                }
                else {
                    changeEditingPattern(dataController.getNotFinish());
                    // TODO message
                }
            }
        });
        this.addPatternButton.setBackground(this.getBackground());

        this.saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dataController.saveAllPatterns() == true) {
                    patterns.deleteAllButton();
                    canvas.clearCanvas();
                    labelPanel.clearSelection();

                    addNewPattern();
                }
                else {
                    int index = dataController.getNotFinish();
                    if (index == -1) {
                        return;
                    }
                    changeEditingPattern(index);
                    // TODO message
                }
            }
        });
        this.saveButton.setBackground(this.getBackground());
        this.saveButton.setIconMargin(7);
        
        
        // add
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        this.add(canvas, constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 20, 3, 2);
        constraints.anchor = GridBagConstraints.WEST;
        this.add(labelPanel, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(3, 2, 3, 2);
        constraints.anchor = GridBagConstraints.WEST;
        this.add(patterns, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(3, 10, 3, 2);
        constraints.anchor = GridBagConstraints.EAST;
        this.add(this.addPatternButton, constraints);
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.insets = new Insets(3, 20, 3, 2);
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(this.saveButton, constraints);
    }

    GridCanvasPanel canvas = null;
    LabelPanel labelPanel = null;
    
    PatternsPanel patterns = null;
    private NormalButton addPatternButton = null;
    private DecorativeButton saveButton = null;


    /**
     * 新增 pattern
     * @return 是否新增成功
     */
    private boolean addNewPattern() {
        if (this.dataController.newPattern() == true) {
            this.patterns.addPattern(dataController.getPattern());
            return true;
        }
        return false;
    }


    /**
     * 切換要編輯的 圖形
     * @param index 第幾個 圖形
     */
    public void changeEditingPattern(int index) {
        this.dataController.setCurrentIndex(index);

        this.canvas.laodPattern(this.dataController.getPattern());
        this.labelPanel.loadLabel(this.dataController.getPattern());
        this.patterns.setSelected(this.dataController.getCurrentIndex(), true);
    }
}
