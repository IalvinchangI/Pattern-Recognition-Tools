package indi.IalvinchangI.patternrecognitionapp.io;

import java.awt.Image;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import indi.IalvinchangI.patternrecognitionapp.ResourceConstant;
import indi.IalvinchangI.patternrecognitionapp.gui.tools.GUITools;


/**
 * 儲存 圖形的標籤跟與其對應編碼 的類別
 * <p>
 * 另外，靜態方法也是 LabelToCode 的 reader
 * @author IalvinchangI
 */
public class LabelToCode {
    /** 圖形的標籤 */
    public String label = null;

    /** 與標籤對應的編碼 */
    public String code = null;

    /** 與標籤對應的編碼 */
    private String image = null;


    /**
     * 取得標籤對應的圖形
     * @return 標籤的圖形
     */
    public Image getImage() {
        return GUITools.getImageFromResource(ResourceConstant.getImagePath("pattern_images/" + this.image));
    }


    /**
     * 從 json 中讀取 LabelToCode
     * @param resourcePath json 檔的路徑
     * @return 一串 LabelToCode 物件
     */
    public static LabelToCode[] readFromJsonResource(String resourcePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            LabelToCode[] output = mapper.readValue(LabelToCode.class.getResourceAsStream(resourcePath), LabelToCode[].class);
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
