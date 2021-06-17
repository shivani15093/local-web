package com.autify.service.impl;

import com.autify.service.JsoupHtmlParser;
import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class JsoupHtmlParserTests {
    @Mock
    Jsoup jsoup;
    @InjectMocks
    JsoupHtmlParser jsoupHtmlParser = new JsoupHtmlParserImpl();
    @Test
    public void parserPositiveTest(){
        Assert.assertNotNull(jsoupHtmlParser.parse("./src/test/resources/www.geeksforgeeks.org.html"));
    }
    @Test
    public void parserNegativeTest(){
        Assert.assertNull(jsoupHtmlParser.parse("./src/test/resources/www.google.com.html"));
    }
}
