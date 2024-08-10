package indi.IalvinchangI.patternrecognitionapp.io;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;


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


    /**
     * 從 json 中讀取 LabelToCode
     * @param path json 檔的路徑
     * @return 一串 LabelToCode 物件
     */
    public static LabelToCode[] readFromJson(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            LabelToCode[] output = mapper.readValue(new File(path), LabelToCode[].class);
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
