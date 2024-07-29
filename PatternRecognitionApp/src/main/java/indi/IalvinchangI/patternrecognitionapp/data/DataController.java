package indi.IalvinchangI.patternrecognitionapp.data;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 管理所有的 Pattern
 * @author IalvinchangI
 */
public class DataController {

    /** 所有的 Pattern */
    public ArrayList<PatternData> patterns = null;

    /** 當前編輯的 pattern */
    private int currentIndex = -1;

    /**
     * get 當前編輯的 pattern 的索引值
     * @return 索引值
     */
    public int getCurrentIndex() {
        return this.currentIndex;
    }

    /**
     * set 要編輯的 pattern 的索引值
     * @param index 索引值
     * 
     * @throws ArrayIndexOutOfBoundsException
     */
    public void setCurrentIndex(int index) {
        // TODO 還未編輯完的話？
        if (index < 0 && index >= this.patterns.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.currentIndex = index;
    }


    /**
     * 管理所有的 Pattern
     * <p>
     * new 一個空的 DataController
     */
    public DataController() {
        this.patterns = new ArrayList<>();
    }


    /**
     * 管理所有的 Pattern
     * <p>
     * new 一個裝有 dirPath 內所有 pattern 的 DataController
     */
    public DataController(String dirPath) {
        this();
        // TODO read all the file in the dirPath
    }


    /**
     * 新增新的 pattern，並將編輯指針指向他
     */
    public void newPattern() {
        this.currentIndex = patterns.size();
        this.patterns.add(new PatternData());
    }


    /**
     * get 編輯指針指向的 pattern
     * @return 編輯指針指向的 pattern
     */
    public PatternData getPattern() {
        if (this.currentIndex == -1) {
            throw new IllegalArgumentException("there is no pattern");
        }
        return this.patterns.get(this.currentIndex);
    }

    
    /**
     * 填 pattern 的 圖形
     * @param pattern 要存入的 圖形
     * 
     * @throws IllegalArgumentException
     */
    public void fillData(BufferedImage pattern) {
        PatternData patternData = this.getPattern();

        if ((patternData.pattern.length != pattern.getHeight(null)) || (patternData.pattern[0].length != pattern.getWidth(null))) {
            throw new IllegalArgumentException("image size is incompatible");
        }

        byte[] data = ((DataBufferByte) pattern.getRaster().getDataBuffer()).getData();
        int width = patternData.pattern[0].length;
        for (int y = 0; y < patternData.pattern.length; y++) {
            for (int x = 0; x < width; x++) {
                patternData.pattern[y][x] = data[y * width + x];
            }
        }
    }
    
    /**
     * 填 pattern 的 各點速度
     * @param speed 要存入的 畫筆在各點的速度
     */
    public void fillData(float[][] speed) {
        PatternData patternData = this.getPattern();
        for (int y = 0; y < patternData.speed.length; y++) {
            patternData.speed[y] = speed[y].clone();
        }
    }

    /**
     * 填 pattern 的 畫筆粗度
     * @param strokeWidth 要存入的 畫筆粗度
     */
    public void fillData(int strokeWidth) {
        PatternData patternData = this.getPattern();
        patternData.strokeWidth = strokeWidth;
    }

    /**
     * 填 pattern 的 圖形標籤
     * @param label 要存入的 圖形標籤
     */
    public void fillData(String label) {
        PatternData patternData = this.getPattern();
        patternData.label = label;
    }


    /**
     * 儲存所有 pattern
     */
    public void saveAllPatterns() {
        Iterator<PatternData> patternsIterator = this.patterns.iterator();
        while (patternsIterator.hasNext()) {
            patternsIterator.next().save();
            patternsIterator.remove();
        }
    }
}
