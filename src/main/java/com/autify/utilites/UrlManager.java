package com.autify.utilites;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlManager {
    public URL createUrlObjFromUrlStr(String urlString){
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL Exception raised - " + e.getMessage());
        }
        return url;
    }
    public URLConnection openUrlConnection(String urlString){
        URLConnection connection = null;
        try {
            connection = createUrlObjFromUrlStr(urlString).openConnection();
        } catch (IOException e) {
            System.out.println("IOException raised - " + e.getMessage());
        }
        // Added this property in order to resolve 403 FORBIDDEN issue
        connection.addRequestProperty("User-Agent", "Chrome");
        return connection;
    }
}
