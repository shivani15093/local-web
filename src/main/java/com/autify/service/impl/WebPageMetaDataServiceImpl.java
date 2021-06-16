package com.autify.service.impl;

import com.autify.model.WebPageMetaDataModel;
import com.autify.service.WebPageMetaDataService;
import com.autify.utilites.FileManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import static com.autify.constants.AppConstants.*;

public class WebPageMetaDataServiceImpl implements WebPageMetaDataService {
    @Override
    public WebPageMetaDataModel getMetaData(String webPage) {
        Document doc = null;
        URL url = null;
        try {
            url = new URL(webPage);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL Exception raised - " + e.getMessage());
        }
        String fileName = FileManager.getFileNameFromUrl(url);
        try {
            doc = Jsoup.parse(new File(fileName + HTML_FILE_EXT), "utf-8");
        }
        catch (IOException e) {
            System.out.println("IOException raised - " + e.getMessage());
            return null;
        }
        Elements links = doc.select(LINKS_CSS_QUERY);
        Elements images = doc.select(IMAGES_CSS_QUERY);
        Date lastModifiedDate = FileManager.getLastModifiedDate(fileName);
        return WebPageMetaDataModel.builder()
                .site(webPage)
                .num_links(links.size())
                .images(images.size())
                .last_fetch(lastModifiedDate)
                .build();
    }
}
