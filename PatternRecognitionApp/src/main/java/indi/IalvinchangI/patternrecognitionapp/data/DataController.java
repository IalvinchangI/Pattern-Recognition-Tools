package indi.IalvinchangI.patternrecognitionapp.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    public void setCurrentIndex(int index) {
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
     * new 一個空的 DataController 並設定他
     */
    public DataController(SettingData settingData) {
        this();
        this.setting(settingData);
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
     * 設定 DataController
     * @param settingData 設定資料
     */
    public void setting(SettingData settingData) {
        this.settingData = settingData;
    }

    private SettingData settingData = null;


    /**
     * 尋找未編輯完的 pattern 並回傳其 index
     * @apiNote 沒有 pattern 的話，回傳 -1 (算是都編輯完了)
     * @return 找到的第一個未編輯完的 pattern 的 index，如果都完成了則回傳 -1
     */
    public int getNotFinish() {
        for (int i = 0; i < this.patterns.size(); i++) {  // check the editing of all patterns is complete
            if (this.patterns.get(i).getFinishEditing_TF() == false) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 新增新的 pattern，並將編輯指針指向他
     * @return 若有未編輯完的 pattern 就回傳 false，反之，回傳 true
     */
    public boolean newPattern() {
        if (this.getNotFinish() != -1) {
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
     * 刪除 pattern
     * @param index 要刪除的 pattern 的索引值
     * @implNote 若刪除最後一個 pattern，currentIndex 會在刪除後指向最後一個 pattern
     */
    public void deletePattern(int index) {
        this.patterns.remove(index);
        
        if (this.currentIndex >= this.patterns.size()) {
            this.currentIndex = this.patterns.size() - 1;
        }
    }


    /**
     * 儲存所有 pattern 並刪除
     * @return succeed or not
     */
    public boolean saveAllPatterns() throws IOException {
        if (this.settingData == null) {
            return false;
        }
        if (this.patterns.size() == 0) {
            return false;
        }
        
        if (this.getNotFinish() != -1) {
            return false;
        }

        if (this.settingData.checkSaveDirectoryPath() == false) {
            throw new IOException("存檔位置錯誤，請檢查一下資料夾是否存在");
        }

        // save
        String format = this.settingData.getSaveDirectoryPath() + File.separator + PREFIX_FILE_NAME + "%s" + FILE_EXTENTION;
        Iterator<PatternData> patternsIterator = this.patterns.iterator();
        PatternWriter writer = new PatternWriter();
        while (patternsIterator.hasNext()) {
            PatternData pattern = patternsIterator.next();
            writer.writePattern(String.format(format, pattern.getLabel()), pattern);  // may throw IOException
            patternsIterator.remove();
        }
        // reset
        this.currentIndex = -1;
        return true;
    }

    private final static String FILE_EXTENTION = ".iai";
    private final static String PREFIX_FILE_NAME = "PatternRecognitionRaw";
}
