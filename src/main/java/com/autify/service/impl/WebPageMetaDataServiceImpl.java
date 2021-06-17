package com.autify.service.impl;

import com.autify.model.WebPageMetaDataModel;
import com.autify.service.JsoupHtmlParser;
import com.autify.service.WebPageMetaDataService;
import com.autify.utilites.FileManager;
import com.autify.utilites.UrlManager;
import org.jsoup.nodes.Document;

import java.net.URL;

public class WebPageMetaDataServiceImpl implements WebPageMetaDataService {
    JsoupHtmlParser jsoupHtmlParser = new JsoupHtmlParserImpl();
    JsoupDocumentQuery jsoupDocumentQuery = new JsoupDocumentQuery();
    UrlManager urlManager = new UrlManager();
    FileManager fileManager = new FileManager();

    @Override
    public WebPageMetaDataModel getMetaData(String webPage) {
        // Creating URL object from input url string
        URL url = urlManager.createUrlObjFromUrlStr(webPage);
        // Creating file name from url string
        String fileName = fileManager.createFileNameFromUrl(url);
        // Parsing html file for extraction of meta data
        Document doc = jsoupHtmlParser.parse(fileName);
        return WebPageMetaDataModel.builder()
                .site(webPage)
                .num_links(jsoupDocumentQuery.getNumOfLinks(doc))
                .images(jsoupDocumentQuery.getNumOfImages(doc))
                .last_fetch(fileManager.getLastModifiedDate(fileName))
                .build();

    }
}
