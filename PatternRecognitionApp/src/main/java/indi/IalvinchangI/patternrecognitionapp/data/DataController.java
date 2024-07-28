package indi.IalvinchangI.patternrecognitionapp.data;

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
     * 填 pattern 的 圖形
     * @param pattern 要存入的 圖形
     */
    public void fillData(int[][] pattern) {
        PatternData patternData = this.patterns.get(currentIndex);
        for (int y = 0; y < patternData.pattern.length; y++) {
            patternData.pattern[y] = pattern[y].clone();
        }
    }
    
    /**
     * 填 pattern 的 各點速度
     * @param speed 要存入的 畫筆在各點的速度
     */
    public void fillData(float[][] speed) {
        PatternData patternData = this.patterns.get(currentIndex);
        for (int y = 0; y < patternData.speed.length; y++) {
            patternData.speed[y] = speed[y].clone();
        }
    }

    /**
     * 填 pattern 的 畫筆粗度
     * @param strokeWidth 要存入的 畫筆粗度
     */
    public void fillData(int strokeWidth) {
        PatternData patternData = this.patterns.get(currentIndex);
        patternData.strokeWidth = strokeWidth;
    }

    /**
     * 填 pattern 的 圖形標籤
     * @param label 要存入的 圖形標籤
     */
    public void fillData(String label) {
        PatternData patternData = this.patterns.get(currentIndex);
        patternData.label = label;
    }


    /**
     * 儲存所有 pattern
     */
    public void saveAllPatterns() {
        Iterator<PatternData> patternsIterator = this.patterns.iterator();
        // TODO save now? or check it first
        while (patternsIterator.hasNext()) {
            patternsIterator.next().save();
            patternsIterator.remove();
        }
    }
}
