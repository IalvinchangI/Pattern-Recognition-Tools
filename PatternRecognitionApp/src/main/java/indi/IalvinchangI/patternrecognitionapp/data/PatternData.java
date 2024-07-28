package indi.IalvinchangI.patternrecognitionapp.data;

import java.awt.Image;
import java.awt.image.BufferedImage;

import indi.IalvinchangI.patternrecognitionapp.App;

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
        this.pattern = new int[App.PATTERN_WIDTH][App.PATTERN_WIDTH];
        this.speed = new float[App.PATTERN_WIDTH][App.PATTERN_WIDTH];
    }


    public void save() {
        // TODO
    }


    /**
     * 把 pattern 轉成 Image
     * @return 轉成 Image 的 pattern
     */
    public Image toImage() {
        BufferedImage output = new BufferedImage(pattern[0].length, pattern.length, BufferedImage.TYPE_BYTE_GRAY);
        return output;
    }
}
