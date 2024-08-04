package indi.IalvinchangI.patternrecognitionapp.data;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import indi.IalvinchangI.patternrecognitionapp.io.PatternWriter;

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
    public boolean setCurrentIndex(int index) {
        if (this.getPattern().getFinishEditing_TF() == false) {
            return false;
        }

        if (index < 0 && index >= this.patterns.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        this.currentIndex = index;
        return true;
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
    public boolean newPattern() {
        if (this.currentIndex != -1 && this.getPattern().getFinishEditing_TF() == false) {
            return false;
        }

        this.currentIndex = patterns.size();
        this.patterns.add(new PatternData());
        return true;
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
        this.getPattern().fillData(pattern);
    }
    
    /**
     * 填 pattern 的 各點速度
     * @param velocity 要存入的 畫筆在各點的速度
     */
    public void fillData(double[][][] velocity) {
        this.getPattern().fillData(velocity);
    }

    /**
     * 填 pattern 的 畫筆粗度
     * @param strokeWidth 要存入的 畫筆粗度
     */
    public void fillData(int strokeWidth) {
        this.getPattern().fillData(strokeWidth);
    }

    /**
     * 填 pattern 的 圖形標籤
     * @param label 要存入的 圖形標籤
     */
    public void fillData(String label) {
        this.getPattern().fillData(label);
    }


    /**
     * 儲存所有 pattern
     * @return succeed or not
     */
    public boolean saveAllPatterns() {
        if (patterns.size() == 0) {
            return false;
        }
        // check the editing of all patterns is complete
        for (PatternData patternData : patterns) {
            if (patternData.getFinishEditing_TF() == false) {
                return false;
            }
        }

        // save
        Iterator<PatternData> patternsIterator = this.patterns.iterator();
        PatternWriter writer = new PatternWriter();
        while (patternsIterator.hasNext()) {
            PatternData pattern = patternsIterator.next();
            writer.writePattern(PREFIX_FILE_NAME + pattern.getLabel() + FILE_EXTENTION, pattern);
            patternsIterator.remove();
        }
        return true;
    }

    private final static String FILE_EXTENTION = ".iai";
    private final static String PREFIX_FILE_NAME = "PatternRecognitionRaw";
}
