package com.autify.service.impl;

import com.autify.model.WebPageMetaDataModel;
import com.autify.service.WebPageMetaDataService;
import com.autify.utilites.FileManager;
import com.autify.utilites.UrlManager;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class WebPageMetaDataServiceTests {
    @Mock
    UrlManager urlManager;
    @Mock
    FileManager fileManager;
    @Mock
    JsoupHtmlParserImpl jsoupHtmlParser = new JsoupHtmlParserImpl();
    @Mock
    JsoupDocumentQuery jsoupDocumentQuery;
    @InjectMocks
    WebPageMetaDataService webPageMetaDataService = new WebPageMetaDataServiceImpl();
    @Test
    public void testGetMetaData() throws IOException {
        Date lastModDate = new Date();
        lastModDate.setTime(1623938983719L);
        Mockito.when(urlManager.createUrlObjFromUrlStr("https://www.google.com"))
                .thenReturn(new URL("https://www.google.com/"));
        Mockito.when(fileManager.createFileNameFromUrl(new URL("https://www.google.com/")))
                .thenReturn("www.google.com.html");
        Mockito.when(jsoupHtmlParser.parse("www.google.com.html")).thenReturn(new Document(""));
        Mockito.when(jsoupDocumentQuery.getNumOfLinks(any(Document.class))).thenReturn(5);
        Mockito.when(jsoupDocumentQuery.getNumOfImages(any(Document.class))).thenReturn(15);
        Mockito.when(fileManager.getLastModifiedDate("www.google.com.html"))
                .thenReturn(lastModDate);
        WebPageMetaDataModel response = webPageMetaDataService.getMetaData("https://www.google.com");
        Assert.assertEquals(5, response.getNum_links());
        Assert.assertEquals(15, response.getImages());
        Assert.assertEquals(1623938983719L, response.getLast_fetch().getTime());
    }
}
