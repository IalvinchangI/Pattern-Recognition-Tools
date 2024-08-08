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
    private String saveDirectoryPath = App.ROOT_PATH;


    /**
     * 取得儲存 pattern 的資料夾路徑
     * @return 儲存 pattern 的資料夾路徑
     */
    public String getSaveDirectoryPath() {
        return this.saveDirectoryPath;
    }
    
    /**
     * 設定
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
