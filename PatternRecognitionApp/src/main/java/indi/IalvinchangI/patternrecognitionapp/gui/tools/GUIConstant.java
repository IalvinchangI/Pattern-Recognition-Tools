package indi.IalvinchangI.patternrecognitionapp.gui.tools;

import java.awt.Color;
import java.awt.Font;


/**
 * 視窗的顏色、字體、大小的常數
 * @author IalvinchangI
 */
public interface GUIConstant {

    /** 視窗最小寬度 */
    public static final int MIN_WINDOW_WIDTH  = 900;
    /** 視窗最小高度 */
    public static final int MIN_WINDOW_HEIGHT = 700;


    /** 底色 */
    public static final Color BOTTOM_COLOR = new Color(218, 215, 205);
    // public static final Color BOTTOM_COLOR = new Color(250, 237, 205);
    /** 主要的背景色 */
    public static final Color PRIMARY_BACKGROUND_COLOR = new Color(204, 213, 174);
    /** 次要的背景色 */
    public static final Color SECONDARY_BACKGROUND_COLOR = new Color(233, 237, 201);
    /** 區塊底色 */
    public static final Color BOX_BOTTOM_COLOR = new Color(254, 250, 224);
    /** 主要的區塊顏色 */
    public static final Color PRIMARY_BOX_COLOR = new Color(212, 163, 115);
    /** 次要的區塊顏色 */
    public static final Color SECONDARY_BOX_COLOR = new Color(254, 250, 224);


    /** 小標題的字體 */
    public static final Font SUBTITLE_FONT = new Font("微軟正黑體", Font.BOLD, 18);
    /** 內文的字體 */
    public static final Font CONTENT_FONT = new Font("微軟正黑體", Font.BOLD, 14);

    
}
