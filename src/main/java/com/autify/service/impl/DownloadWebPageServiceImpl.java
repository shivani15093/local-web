package com.autify.service.impl;

import com.autify.service.DownloadWebPageService;
import com.autify.utilites.FileManager;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static com.autify.constants.AppConstants.HTML_FILE_EXT;

public class DownloadWebPageServiceImpl implements DownloadWebPageService {
    @Override
    public void downloadWebPage(String webPage) {
        try {
            // Create URL object
            URLConnection connection = new URL(webPage).openConnection();
            // Added this property in order to resolve 403 FORBIDDEN issue
            connection.addRequestProperty("User-Agent", "Chrome");
            BufferedReader readr = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String fileName = FileManager.getFileNameFromUrl(connection.getURL());
            // Enter filename in which you want to download
            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(fileName + HTML_FILE_EXT));

            // read each line from stream till end
            String line;
            while ((line = readr.readLine()) != null) {
                writer.write(line);
            }

            readr.close();
            writer.close();
            System.out.println("Successfully Downloaded !");
        }
        // Exceptions
        catch (MalformedURLException e) {
            System.out.println("Malformed URL Exception raised - " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("IOException raised - " + e.getMessage());
        }
    }
}
