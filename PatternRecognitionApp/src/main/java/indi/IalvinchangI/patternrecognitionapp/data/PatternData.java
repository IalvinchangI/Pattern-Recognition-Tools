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
    public static final String FILE_TYPE = "IaI.PatternRecognition.raw";

    /** 圖形 */
    private byte[][] pattern = null;

    /** 畫筆在各點的速度 */
    private double[][][] velocity = null;
    
    /** 畫筆粗度 */
    private int strokeWidth = 0;

    /** 圖形標籤 */
    private String label = "";


    /**
     * 0: pattern
     * 1: speed
     * 2: strokeWidth
     * 3: label
     */
    private byte editCheck = 0;

    private static final byte FINISH_ALL = 15;
    
    /** 檢查 圖形 是否已填完 */
    public static final byte CHECK_PATTERN = 1;
    /** 檢查 畫筆在各點的速度 是否已填完 */
    public static final byte CHECK_VELOCITY = 2;
    /** 檢查 畫筆粗度 是否已填完 */
    public static final byte CHECK_STROKE_WIDTH = 4;
    /** 檢查 圖形標籤 是否已填完 */
    public static final byte CHECK_LABEL = 8;

    /**
     * 確認是否都編輯完了
     * @return 是否都編輯完了
     */
    public boolean getFinishEditing_TF() {
        return this.editCheck == FINISH_ALL;
    }

    /**
     * 確認 target 是否編輯完了
     * @param target 要檢查的資料
     * {@code CHECK_PATTERN}, {@code CHECK_VELOCITY}, {@code CHECK_STROKE_WIDTH}, {@code CHECK_LABEL}
     * @return 是否編輯完 target 了
     */
    public boolean getFinishEditing_TF(byte target) {
        return (this.editCheck & target) != 0;
    }


    public PatternData() {
        this.pattern = new byte[App.PATTERN_WIDTH][App.PATTERN_WIDTH];
        for (int i = 0; i < App.PATTERN_WIDTH; i++) {
            Arrays.fill(this.pattern[i], (byte)-1);
        }
        this.velocity = new double[App.PATTERN_WIDTH][App.PATTERN_WIDTH][2];
    }


    /**
     * 填 pattern 的 圖形
     * @param pattern 要存入的 圖形
     * 
     * @throws IllegalArgumentException
     */
    public void fillData(BufferedImage pattern) {
        if ((this.pattern.length != pattern.getHeight(null)) || (this.pattern[0].length != pattern.getWidth(null))) {
            throw new IllegalArgumentException("image size is incompatible");
        }

        byte emptyCheck = ~0;

        byte[] data = ((DataBufferByte) pattern.getRaster().getDataBuffer()).getData();
        int width = this.pattern[0].length;
        for (int y = 0; y < this.pattern.length; y++) {
            for (int x = 0; x < width; x++) {
                this.pattern[y][x] = data[y * width + x];
                emptyCheck &= this.pattern[y][x];
            }
        }

        if (emptyCheck == ~0) {
            this.editCheck &= ~1;
        }
        else {
            this.editCheck |= (1);
        }
    }
    
    /**
     * 填 pattern 的 圖形
     * @param pattern 要存入的 圖形
     * 
     * @throws IllegalArgumentException
     */
    public void fillData(byte[][] pattern) {
        if ((this.pattern.length != pattern.length) || (this.pattern[0].length != pattern[0].length)) {
            throw new IllegalArgumentException("image size is incompatible");
        }

        byte emptyCheck = ~0;
        for (int y = 0; y < this.pattern.length; y++) {
            for (int x = 0; x < this.pattern[0].length; x++) {
                this.pattern[y][x] = pattern[y][x];
                emptyCheck &= this.pattern[y][x];
            }
        }

        if (emptyCheck == ~0) {
            this.editCheck &= ~1;
        }
        else {
            this.editCheck |= (1);
        }
    }
    
    /**
     * 填 pattern 的 各點速度
     * @param velocity 要存入的 畫筆在各點的速度
     */
    public void fillData(double[][][] velocity) {
        boolean emptyCheck = true;
        
        for (int y = 0; y < this.velocity.length; y++) {
            for (int x = 0; x < this.velocity.length; x++) {
                this.velocity[y][x] = velocity[y][x].clone();

                if (this.velocity[y][x][0] != 0 || this.velocity[y][x][1] != 0) {
                    emptyCheck = false;
                }
            }
        }

        if (emptyCheck == true) {  // is empty
            this.editCheck &= ~(1 << 1);
        }
        else {
            this.editCheck |= (1 << 1);
        }
    }

    /**
     * 填 pattern 的 畫筆粗度
     * @param strokeWidth 要存入的 畫筆粗度
     */
    public void fillData(int strokeWidth) {
        this.strokeWidth = strokeWidth;

        this.editCheck |= (1 << 2);
    }

    /**
     * 填 pattern 的 圖形標籤
     * @param label 要存入的 圖形標籤
     */
    public void fillData(String label) {
        this.label = label;

        this.editCheck |= (1 << 3);
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


    /**
     * 取得 pattern 的 圖形
     * @return pattern 的 圖形
     */
    public byte[][] getPattern() {
        return this.pattern;
    }


    /**
     * 取得 pattern 在各點的畫筆速度
     * @return pattern 在各點的畫筆速度
     */
    public double[][][] getVelocity() {
        return this.velocity;
    }


    /**
     * 取得 pattern 的 畫筆粗度
     * @return pattern 的 畫筆粗度
     */
    public int getStrokeWidth() {
        return this.strokeWidth;
    }


    /**
     * 取得 pattern 的 圖形標籤
     * @return pattern 的 圖形標籤
     */
    public String getLabel() {
        return this.label;
    }
}
