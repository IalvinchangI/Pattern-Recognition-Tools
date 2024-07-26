package indi.IalvinchangI.patternrecognitionapp.data;


/**
 * 管理 pattern 資料
 * @author IalvinchangI
 */
public class PatternData {

    /** 檔案類型 */
    private static final String FILE_TYPE = "";

    /** 圖形 */
    public int[][] pattern = null;

    /** 畫筆在各點的速度 */
    public float[][] speed = null;

    /** 畫筆粗度 */
    public int strokeWidth = 0;

    /** 圖形標籤 */
    public String label = "";


    public PatternData() {
        // TODO
    }


    public void save() {
        // TODO
    }
}
