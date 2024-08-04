package indi.IalvinchangI.patternrecognitionapp.io;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import indi.IalvinchangI.patternrecognitionapp.data.PatternData;


/**
 * can read PatternData from a file
 * @provides readPattern : read PatternData from a file
 * @author IalvinchangI
 */
public class PatternReader {
    
    /**
     * create a new PatternReader
     */
    public PatternReader() {
        this.mapper = new ObjectMapper();
    }

    private ObjectMapper mapper = null;


    /**
     * read PatternData from a file
     * @param fileName the name of the file that store PatternData
     * @return the PatternData that store in the file
     */
    public PatternData readPattern(String fileName) {
        return this.readPattern(new File(fileName));
    }

    /**
     * read PatternData from a file
     * @param file the file that store PatternData
     * @return the PatternData that store in the file
     */
    public PatternData readPattern(File file) {
        // read
        JSONPattern jsonPattern = null;
        try {
            jsonPattern = this.mapper.readValue(file, JSONPattern.class);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // int[][] -> byte[][]
        int[][] pattern = jsonPattern.pattern;
        byte[][] bytePattern = new byte[pattern.length][pattern[0].length];

        for (int y = 0; y < pattern.length; y++) {
            for (int x = 0; x < pattern[0].length; x++) {
                bytePattern[y][x] = (byte) pattern[y][x];
            }
        }

        // convert to PatternData
        PatternData output = new PatternData();

        output.fillData(bytePattern);
        output.fillData(jsonPattern.velocity);
        output.fillData(jsonPattern.strokeWidth);
        output.fillData(jsonPattern.label);

        return output;
    }
}
