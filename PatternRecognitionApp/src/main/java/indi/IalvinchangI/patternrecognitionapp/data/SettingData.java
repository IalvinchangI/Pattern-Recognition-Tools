package indi.IalvinchangI.patternrecognitionapp.data;

import indi.IalvinchangI.patternrecognitionapp.App;


/**
 * 儲存設定的地方
 * @author IalvinchangI
 */
public class SettingData {

    /** 檔案類型 (const) */
    public static final String FILE_TYPE = "IaI.PatternRecognition.AppSettingData";

    /** 檔案類型 */
    public String fileType = FILE_TYPE;
    
    /** 儲存 pattern 的資料夾路徑 */
    public String saveDirectoryPath = App.ROOT_PATH;

}
