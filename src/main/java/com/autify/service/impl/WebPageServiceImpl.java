package com.autify.service.impl;

import com.autify.model.MetaDataModel;
import com.autify.service.WebPageService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import static com.autify.constants.AppConstants.HTML_FILE_EXT;

public class WebPageServiceImpl implements WebPageService {
    Date lastModifiedDate = null;
    @Override
    public void downloadWebPage(String webPage) {
        try {
            // Create URL object
            URL url = new URL(webPage);
            BufferedReader readr =
                    new BufferedReader(new InputStreamReader(url.openStream()));


            File oldFile = new File(webPage.substring(8) + HTML_FILE_EXT);
            if(oldFile.exists()){
                long lastModified = oldFile.lastModified();
                lastModifiedDate = new Date(lastModified);
            }
            // Enter filename in which you want to download
            BufferedWriter writer =
                      new BufferedWriter(new FileWriter(webPage.substring(8) + HTML_FILE_EXT));

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
        catch (MalformedURLException mue) {
            System.out.println("Malformed URL Exception raised !");
        }
        catch (IOException ie) {
            System.out.println("IOException raised !");
        }
    }

    @Override
    public MetaDataModel getMetaData(String webPage) {
        Document doc = null;
        String body = "";
        downloadWebPage(webPage);
        try {
              doc = Jsoup.parse(new File(webPage.substring(8) + ".html"), "utf-8");
        }
        catch (IOException e) {
            System.out.println("IOException raised !");
            return null;
        }
        Elements links = doc.select("a[href]");
        Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        return MetaDataModel.builder()
                .site(webPage)
                .num_links(links.size())
                .images(images.size())
                .last_fetch(lastModifiedDate)
                .build();
    }
}
