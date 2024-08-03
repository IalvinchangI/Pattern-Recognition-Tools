package indi.IalvinchangI.patternrecognitionapp.util;


/**
 * 計算速度的靜態方法
 * @author IalvinchangI
 */
public class MotionCalculator {
    
    /**
     * 計算一維速度
     * @param startX    初始座標
     * @param endX      結束座標
     * @param startTime 初始時間
     * @param endTime   結束時間
     * @return 一維速度
     */
    public static double velocity(double startX, double endX, long startTime, long endTime) {
        return (endX - startX) / (endTime - startTime);
    }

    /**
     * 計算二維速度
     * @param startX    初始座標
     * @param endX      結束座標
     * @param startTime 初始時間
     * @param endTime   結束時間
     * @return 二維速度
     */
    public static double[] velocity(double[] startPos, double[] endPos, long startTime, long endTime) {
        return new double[] {
            MotionCalculator.velocity(startPos[0], endPos[0], startTime, endTime), 
            MotionCalculator.velocity(startPos[1], endPos[1], startTime, endTime)
        };
    }


    /**
     * 取得現在時間 (ms)
     * @return 現在時間 (ms)
     */
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
