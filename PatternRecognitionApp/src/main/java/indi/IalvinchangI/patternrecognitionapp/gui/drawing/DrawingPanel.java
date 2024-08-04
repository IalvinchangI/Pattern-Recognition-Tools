package indi.IalvinchangI.patternrecognitionapp.gui.drawing;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import indi.IalvinchangI.patternrecognitionapp.App;
import indi.IalvinchangI.patternrecognitionapp.data.DataController;
import indi.IalvinchangI.patternrecognitionapp.data.PatternData;
import indi.IalvinchangI.patternrecognitionapp.gui.MainFrame;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.DecorativeButton;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.NormalButton;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.TransparentPanel;


/**
 * 讓使用者繪圖的頁面，屬於 {@code MainPanel}
 * @author IalvinchangI
 */
public class DrawingPanel extends TransparentPanel {

    public static String[] labels = {"圓形", "三角形", "矩形", "五邊形", "五角星"};
    public static String[] codes =  {"1"   , "3"    , "4"   , "5"     , "50"   };


    DataController dataController = null;


    public DrawingPanel() {
        // data
        this.dataController = new DataController();


        // GUI
        this.setBackground(Color.YELLOW);
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        
        // new
        this.canvas = new GridCanvasPanel();
        this.labelPanel = new LabelPanel(DrawingPanel.labels, DrawingPanel.codes);
        
        this.patterns = new PatternsPanel();
        this.addPatternButton = new NormalButton(App.RESOURCES_PATH + "images/add_pattern.png", PatternsPanel.BUTTON_WIDTH);
        this.saveButton = new DecorativeButton(App.RESOURCES_PATH + "images/save_file.png", 100);
        
        // set
        this.labelPanel.setFont(MainFrame.SUBTITLE_FONT);
        
        this.addNewPattern();
        
        this.addPatternButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addNewPattern() == true) {
                    canvas.clearCanvas();
                    labelPanel.clearSelection();
                }
            }
        });

        this.saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataController.saveAllPatterns();
                // TODO delete
            }
        });
        
        
        // add
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        this.add(canvas, constraints);
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(3, 20, 3, 2);
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
        constraints.insets = new Insets(3, 2, 3, 2);
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(this.saveButton, constraints);
    }

    GridCanvasPanel canvas = null;
    LabelPanel labelPanel = null;
    
    PatternsPanel patterns = null;
    private NormalButton addPatternButton = null;
    private DecorativeButton saveButton = null;


    private boolean addNewPattern() {
        if (this.dataController.newPattern() == true) {
            this.patterns.addPattern(dataController.getPattern());
            return true;
        }
        return false;
    }
}
