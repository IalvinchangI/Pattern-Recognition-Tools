package indi.IalvinchangI.patternrecognitionapp.io;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import indi.IalvinchangI.patternrecognitionapp.data.SettingData;


/**
 * can read SettingData from a file or write SettingData into a file
 * @provides readSetting  : read SettingData from a file
 * @provides writeSetting : write SettingData into a file
 * @author IalvinchangI
 */
public class SettingHandler {
    
    public SettingHandler() {
        this.mapper = new ObjectMapper();
    }

    private ObjectMapper mapper = null;


    /**
     * read SettingData from a file
     * @param fileName the name of the file that store SettingData
     * @return the SettingData that store in the file
     */
    public SettingData readSetting(String fileName) {
        return this.readSetting(new File(fileName));
    }

    /**
     * read SettingData from a file
     * @param file the file that store SettingData
     * @return the SettingData that store in the file or null if there are some error or the file does not exist
     */
    public SettingData readSetting(File file) {
        if (file.exists() == false) {
            return null;
        }

        try {
            SettingData output = this.mapper.readValue(file, SettingData.class);
            if (output.check() == false) {
                if (output.checkAndFix() == false) {
                    return null;
                }
            }
            return output;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * write SettingData into a file
     * @param fileName the name of the file that store SettingData
     * @param settingData the SettingData you want to store
     * @return succeed or not
     */
    public boolean writeSetting(String fileName, SettingData settingData) {
        return this.writeSetting(new File(fileName), settingData);
    }

    /**
     * write SettingData into a file
     * @param file the file you want to store SettingData
     * @param settingData the SettingData you want to store
     * @return succeed or not
     */
    public boolean writeSetting(File file, SettingData settingData) {
        if (settingData.check() == false) {
            return false;
        }

        try {
            this.mapper.writeValue(file, settingData);
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
