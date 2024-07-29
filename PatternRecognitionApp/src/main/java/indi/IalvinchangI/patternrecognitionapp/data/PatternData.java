package indi.IalvinchangI.patternrecognitionapp.data;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Arrays;

import indi.IalvinchangI.patternrecognitionapp.App;

/**
 * 管理 pattern 資料
 * @author IalvinchangI
 */
public class PatternData {

    /** 檔案類型 */
    private static final String FILE_TYPE = "IaI.PatternRecognition.raw";

    /** 圖形 */
    public byte[][] pattern = null;

    /** 畫筆在各點的速度 */
    public float[][] speed = null;

    /** 畫筆粗度 */
    public int strokeWidth = 0;

    /** 圖形標籤 */
    public String label = "";


    public PatternData() {
        this.pattern = new byte[App.PATTERN_WIDTH][App.PATTERN_WIDTH];
        for (int i = 0; i < App.PATTERN_WIDTH; i++) {
            Arrays.fill(this.pattern[i], (byte)-1);
        }
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

        byte[] data = ((DataBufferByte) output.getRaster().getDataBuffer()).getData();
        int width = pattern[0].length;
        for (int y = 0; y < pattern.length; y++) {
            for (int x = 0; x < pattern[0].length; x++) {
                data[y * width + x] = pattern[y][x];
            }
        }

        return output;
    }
}
