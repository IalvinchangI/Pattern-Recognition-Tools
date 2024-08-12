package indi.IalvinchangI.patternrecognitionapp.io;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

import indi.IalvinchangI.patternrecognitionapp.data.PatternData;


/**
 * can write PatternData into a file
 * @provides writePattern : write PatternData into a file
 * @author IalvinchangI
 */
public class PatternWriter {
    
    /**
     * create a new PatternWriter
     */
    public PatternWriter() {
        this.mapper = new ObjectMapper();
    }

    private ObjectMapper mapper = null;


    /**
     * write PatternData into a file
     * @param fileName the name of the file that store PatternData
     * @param patternData the PatternData you want to store
     * @return succeed or not
     */
    public boolean writePattern(String fileName, PatternData patternData) {
        return this.writePattern(new File(fileName), patternData);
    }

    /**
     * write PatternData into a file
     * @param file the file you want to store PatternData
     * @param patternData the PatternData you want to store
     * @return succeed or not
     */
    public boolean writePattern(File file, PatternData patternData) {
        file = setSuffixFileName(file);
        // byte[][] -> int[][]
        byte[][] bytePattern = patternData.getPattern();
        int[][] pattern = new int[bytePattern.length][bytePattern[0].length];

        for (int y = 0; y < bytePattern.length; y++) {
            for (int x = 0; x < bytePattern[0].length; x++) {
                pattern[y][x] = Byte.toUnsignedInt(bytePattern[y][x]);
            }
        }

        // write
        JSONPattern jsonPattern = new JSONPattern(
                                            PatternData.FILE_TYPE, 
                                            pattern, 
                                            patternData.getVelocity(), 
                                            patternData.getStrokeWidth(), 
                                            patternData.getLabel()
        );

        try {
            this.mapper.writeValue(file, jsonPattern);
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    private File setSuffixFileName(File file) {
        while (true) {
            if (file.exists() == false) {
                return file;
            }

            // split name & extension
            Pattern extensionPattern = Pattern.compile("(.+)(\\.[^\\.\\s]+)");
            Matcher extensionMatcher = extensionPattern.matcher(file.getName());
            if (extensionMatcher.find() == false) {  // strange name
                return file;
            }
            String name = extensionMatcher.group(1);
            String extension = extensionMatcher.group(2);

            String path = file.getParent();
            path = (path == null) ? "" : path + File.separator;
            
            // split prefix & suffix
            Pattern prefixPattern = Pattern.compile("(.+_)(\\d+)$");
            Matcher prefixMatcher = prefixPattern.matcher(name);
            if (prefixMatcher.find() == true) {
                String prefix = prefixMatcher.group(1);
                String suffix = prefixMatcher.group(2);

                int suffixNumber = Integer.parseInt(suffix);
                suffix = Integer.toString(suffixNumber + 1);
                file = new File(path + prefix + suffix + extension);
            }
            else {
                file = new File(path + name + "_1" + extension);
            }
        }
    }
}
