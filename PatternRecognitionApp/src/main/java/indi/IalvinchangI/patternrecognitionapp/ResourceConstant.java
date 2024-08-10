package indi.IalvinchangI.patternrecognitionapp;

/**
 * 取得 resources 的類別
 * @author IalvinchangI
 */
public class ResourceConstant {

    /** 儲存 圖形的標籤跟與其對應編碼 的檔案 */
    public static final String LABEL2CODE = "label2code.json";


    /** 圖檔的目錄 */
    public static final String IMAGE_PATH = "images/";

    /** 加圖形的圖檔 */
    public static final String ADD_PATTERN_IMAGE = "add_pattern.png";

    /** 清除的圖檔 */
    public static final String CLEAN_IMAGE = "clean_canvas.png";

    /** 結束程式的圖檔 */
    public static final String EXIT_IMAGE = "exit.png";

    /** 繪圖的圖檔 */
    public static final String DRAWING_IMAGE = "drawing.png";

    /** 資料夾的圖檔 */
    public static final String FOLDER_IMAGE = "folder.png";

    /** 導航到資料夾的圖檔 */
    public static final String PONTER_IMAGE = "pointer.png";

    /** 存檔的圖檔 */
    public static final String SAVE_IMAGE = "save_file.png";

    /** 存檔的圖檔 */
    public static final String SETTING_IMAGE = "setting.png";

    
    /**
     * 取得目標 resources 的路徑
     * @param target 目標 resources
     * @return resources 的路徑
     */
    public static String getResourcePath(String target) {
        return App.RESOURCES_PATH + target;
    }


    /**
     * 取得目標圖片的路徑
     * @param target 目標圖片
     * @return 圖片的路徑
     */
    public static String getImagePath(String target) {
        return getResourcePath(IMAGE_PATH + target);
    }
}
