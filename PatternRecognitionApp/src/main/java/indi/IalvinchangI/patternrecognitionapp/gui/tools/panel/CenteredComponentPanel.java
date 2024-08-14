package indi.IalvinchangI.patternrecognitionapp.gui.tools.panel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


/**
 * 把 Component 放在 Panel 正中央的 Panel
 * @author IalvinchangI
 */
public class CenteredComponentPanel extends TransparentPanel {
    
    /**
     * 把 Component 放在 Panel 正中央的 Panel
     * @param comp 要放在 Panel 正中央的 Component
     */
    public CenteredComponentPanel(Component comp) {
        this.comp = comp;
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(comp, constraints);
    }

    protected Component comp = null;
}
