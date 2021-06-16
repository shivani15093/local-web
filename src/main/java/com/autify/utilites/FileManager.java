package com.autify.utilites;

import java.io.File;
import java.net.URL;
import java.util.Date;

import static com.autify.constants.AppConstants.*;

public class FileManager {
    public static String removeSpecialCharacters(String path){
        final String cleanPathString = path.replaceAll(REGEX_RMV_SP_CHAR, DOT);
        return  cleanPathString;
    }
    public static String getFileNameFromUrl(URL url){
        String fileName = url.getAuthority() + removeSpecialCharacters(url.getPath());
        return fileName;
    }
    public static Date getLastModifiedDate(String fileName){
        File lastDownloadedFile = new File(fileName + HTML_FILE_EXT);
        Date lastModifiedDate = null;
        if(lastDownloadedFile.exists()){
            long lastModified = lastDownloadedFile.lastModified();
            lastModifiedDate = new Date(lastModified);
        }
        return lastModifiedDate;
    }
}
