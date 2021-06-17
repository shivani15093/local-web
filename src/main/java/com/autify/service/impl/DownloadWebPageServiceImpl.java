package com.autify.service.impl;

import com.autify.service.DownloadWebPageService;
import com.autify.utilites.FileManager;
import com.autify.utilites.UrlManager;

import java.net.URLConnection;

public class DownloadWebPageServiceImpl implements DownloadWebPageService {
    UrlManager urlManager = new UrlManager();
    FileManager fileManager = new FileManager();
    @Override
    public String downloadWebPage(String webPage) {
        // Opening a connection with server
        URLConnection connection = urlManager.openUrlConnection(webPage);
        // Read content from server and write in local file
        return fileManager.readContentFromUrlAndWriteToLocal(connection);
    }
}
