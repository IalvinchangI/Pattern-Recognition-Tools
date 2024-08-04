package indi.IalvinchangI.patternrecognitionapp.io;

class JSONPattern {
    /** 檔案類型 */
    public String fileType = "";

    /** 圖形 */
    public int[][] pattern = null;

    /** 畫筆在各點的速度 */
    public double[][][] velocity = null;

    /** 畫筆粗度 */
    public int strokeWidth = 0;

    /** 圖形標籤 */
    public String label = "";


    public JSONPattern(String fileType, int[][] pattern, double[][][] velocity, int strokeWidth, String label) {
        this.fileType = fileType;
        this.pattern = pattern;
        this.velocity = velocity;
        this.strokeWidth = strokeWidth;
        this.label = label;
    }
}
