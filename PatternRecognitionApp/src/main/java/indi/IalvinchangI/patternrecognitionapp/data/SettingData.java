package indi.IalvinchangI.patternrecognitionapp.data;

import java.io.File;

import indi.IalvinchangI.patternrecognitionapp.App;


/**
 * 儲存設定的地方
 * @author IalvinchangI
 */
public class SettingData {

    /** 檔案類型 (const) */
    public static final String FILE_TYPE = "IaI.PatternRecognition.AppSettingData";

    /** 檔案類型 */
    private String fileType = FILE_TYPE;
    
    /** 儲存 pattern 的資料夾路徑 */
    private String saveDirectoryPath = null;


    public SettingData() {
        this.setSaveDirectoryPath();
    }


    /**
     * 取得儲存 pattern 的資料夾路徑
     * @return 儲存 pattern 的資料夾路徑
     */
    public String getSaveDirectoryPath() {
        return this.saveDirectoryPath;
    }
    
    /**
     * 設定儲存 pattern 的資料夾路徑為 最底層的資料夾
     */
    public void setSaveDirectoryPath() {
        this.saveDirectoryPath = App.ROOT_PATH;
    }

    /**
     * 設定儲存 pattern 的資料夾路徑
     * @param path 儲存 pattern 的資料夾路徑
     * @return 是否成功
     */
    public boolean setSaveDirectoryPath(String path) {
        String oldPath = this.saveDirectoryPath;
        this.saveDirectoryPath = path;
        if (checkSaveDirectoryPath() == true) {
            return true;
        }
        this.saveDirectoryPath = oldPath;
        return false;
    }


    /**
     * 檢查資料的正確性
     * @return 資料是否正確
     */
    public boolean check() {
        if (this.fileType != SettingData.FILE_TYPE) {
            return false;
        }
        if (this.checkSaveDirectoryPath() == false) {
            return false;
        }
        return true;
    }


    /**
     * 檢查資料的正確性，並且將錯誤的部分改成預設值
     * @return 是否成功修復
     */
    public boolean checkAndFix() {
        if (this.fileType != SettingData.FILE_TYPE) {
            return false;
        }
        if (this.checkSaveDirectoryPath() == false) {
            this.setSaveDirectoryPath();
        }
        return true;
    }


    /**
     * 檢查 saveDirectoryPath 是否 "存在、是目錄"
     * @return 是否 "存在、是目錄"
     */
    private boolean checkSaveDirectoryPath() {
        File dir = new File(this.saveDirectoryPath);
        if (dir.exists() && dir.isDirectory()) {
            return true;
        }
        return false;
    }
}
