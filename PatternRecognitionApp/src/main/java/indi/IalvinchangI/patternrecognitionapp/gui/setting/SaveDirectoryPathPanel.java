package indi.IalvinchangI.patternrecognitionapp.gui.setting;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import indi.IalvinchangI.patternrecognitionapp.ResourceConstant;
import indi.IalvinchangI.patternrecognitionapp.data.SettingData;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.button.NormalButton;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.panel.NormalBoxPanel;

/**
 * 設定 "儲存 pattern 的資料夾路徑" 的 panel
 * @author IalvinchangI
 */
public class SaveDirectoryPathPanel extends NormalBoxPanel {

    public static final int TEXT_FIELD_HEIGHT = 30;

    public SaveDirectoryPathPanel(SettingData settingData, int width, int height) {
        super(width, height);
        this.settingData = settingData;
        this.setLayout(new GridBagLayout());

        // new
        this.label = new JLabel("儲存位置");
        this.directoryInputField = new JTextField();
        this.changeDirectoryButton = new NormalButton(ResourceConstant.getImagePath(ResourceConstant.FOLDER_IMAGE), TEXT_FIELD_HEIGHT);
        this.openDirectoryButton = new NormalButton(ResourceConstant.getImagePath(ResourceConstant.PONTER_IMAGE), TEXT_FIELD_HEIGHT);

        this.settingComponents();
        
        this.addingComponents();
    }
    
    private void settingComponents() {
        this.label.setFont(SUBTITLE_FONT);

        this.directoryInputField.setPreferredSize(new Dimension(350, TEXT_FIELD_HEIGHT));
        this.directoryInputField.setText(this.settingData.getSaveDirectoryPath());
        this.directoryInputField.setFont(CONTENT_FONT);
        this.directoryInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDirectoryPath(((JTextField) e.getSource()).getText());
            }
        });
        this.directoryInputField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                setDirectoryPath(((JTextField) e.getSource()).getText());
            }
        });

        SaveDirectoryPathPanel root = this;
        this.changeDirectoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int returnValue = fileChooser.showOpenDialog(root);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    setDirectoryPath(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        this.changeDirectoryButton.setBackground(this.getBackground());

        this.openDirectoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    if (desktop.isSupported(Desktop.Action.OPEN)) {
                        try {
                            desktop.open(new File(settingData.getSaveDirectoryPath()));
                            return;
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                // TODO 沒辦法開的話
            }
        });
        this.openDirectoryButton.setBackground(this.getBackground());
    }

    private void addingComponents() {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 0, 10, 0);
        this.add(this.label, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 20, 0, 5);
        this.add(this.directoryInputField, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(0, 5, 0, 5);
        this.add(this.changeDirectoryButton, constraints);
        constraints.gridx = 2;
        constraints.gridy = 1;
        this.add(this.openDirectoryButton, constraints);
    }

    private JLabel label = null;
    private JTextField directoryInputField = null;
    private NormalButton changeDirectoryButton = null;
    private NormalButton openDirectoryButton = null;
    

    private SettingData settingData = null;

    /**
     * 設定儲存路徑
     * @param path 儲存路徑
     */
    public void setDirectoryPath(String path) {
        if (this.settingData.setSaveDirectoryPath(path) == true) {
            this.directoryInputField.setText(path);
        }
        else {
            this.directoryInputField.setText(this.settingData.getSaveDirectoryPath());
        }
    }
}
