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
            System.out.println("Malformed URL Exception raised while creating URL object - "
                    + e.getMessage());
            System.exit(0);
        }
        return url;
    }
    public URLConnection openUrlConnection(String urlString){
        URLConnection connection = null;
        try {
            connection = createUrlObjFromUrlStr(urlString).openConnection();
        } catch (IOException e) {
            System.out.println("IOException raised while opening URLConnection - " + e.getMessage());
            System.exit(0);
        }
        // Added this property in order to resolve 403 FORBIDDEN issue
        connection.addRequestProperty("User-Agent", "Chrome");
        return connection;
    }
}
