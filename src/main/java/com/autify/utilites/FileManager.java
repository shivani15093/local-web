package com.autify.utilites;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import static com.autify.constants.AppConstants.*;

public class FileManager {
    public String removeSpecialCharsFromPath(String path){
        final String pathStr = path.replaceAll(REGEX_RMV_SP_CHAR, DOT);
        StringBuilder cleanPathStr = new StringBuilder(pathStr);
        if(cleanPathStr.length()>0 && cleanPathStr.charAt(cleanPathStr.length()-1) == DOT_CHAR){
            cleanPathStr.deleteCharAt(cleanPathStr.length()-1);
        }
        return  cleanPathStr.toString();
    }
    public String createFileNameFromUrl(URL url){
        String fileName = url.getAuthority() + removeSpecialCharsFromPath(url.getPath()) + HTML_FILE_EXT;
        return fileName;
    }
    public Date getLastModifiedDate(String fileName){
        File lastDownloadedFile = new File(fileName);
        Date lastModifiedDate = null;
        if(lastDownloadedFile.exists()){
            long lastModified = lastDownloadedFile.lastModified();
            lastModifiedDate = new Date(lastModified);
        }
        return lastModifiedDate;
    }

    public String readContentFromUrlAndWriteToLocal(URLConnection connection){
        FileManager fileManager = new FileManager();
        String fileName = "";
        try {
            BufferedReader readr = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            // Creating file name from url string
            fileName = fileManager.createFileNameFromUrl(connection.getURL());
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            // Read each line from stream till end
            String line;
            while ((line = readr.readLine()) != null) {
                writer.write(line);
            }
            readr.close();
            writer.close();
        }
        catch (IOException e) {
            System.out.println("IOException raised  while reading/writing file - " + e.getMessage());
            return null;
        }
        return fileName;
    }
}
