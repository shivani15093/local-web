package com.autify.service.impl;

import com.autify.service.DownloadWebPageService;
import com.autify.utilites.FileManager;
import com.autify.utilites.UrlManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

@RunWith(MockitoJUnitRunner.class)
public class DownloadWebPageServiceTests {
    @Mock
    UrlManager urlManager;
    @Mock
    FileManager fileManager;
    @InjectMocks
    DownloadWebPageService downloadWebPageService = new DownloadWebPageServiceImpl();
    @Test
    public void testDownloadWebPage() throws IOException {
        URLConnection conn = new URL("https://www.google.com/").openConnection();
        Mockito.when(urlManager.openUrlConnection("https://www.google.com"))
                .thenReturn(conn);
        Mockito.when(fileManager.readContentFromUrlAndWriteToLocal(conn))
                .thenReturn("www.google.com.html");
        Assert.assertEquals("www.google.com.html", downloadWebPageService.
                downloadWebPage("https://www.google.com"));
    }
}
