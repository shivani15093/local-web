package com.autify.service.impl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static com.autify.constants.AppConstants.IMAGES_CSS_QUERY;
import static com.autify.constants.AppConstants.LINKS_CSS_QUERY;
@RunWith(MockitoJUnitRunner.class)
public class JsoupDocumentQueryTests {
    @Mock
    Document document = new Document("");
    @InjectMocks
    JsoupDocumentQuery jsoupDocumentQuery;
    Elements elements = new Elements();
    @Before
    public void setUp(){
        elements.add(new Element("ele_1"));
        elements.add(new Element("ele_2"));
        elements.add(new Element("ele_3"));
        elements.add(new Element("ele_4"));
        elements.add(new Element("ele_5"));
    }
    @Test
    public void testGetNumOfLinks(){
        Mockito.when(document.select(LINKS_CSS_QUERY)).thenReturn(elements);
        Assert.assertEquals(5, jsoupDocumentQuery.getNumOfLinks(document));
    }
    @Test
    public void testGetNumOfImages(){
        Mockito.when(document.select(IMAGES_CSS_QUERY)).thenReturn(elements);
        Assert.assertEquals(5, jsoupDocumentQuery.getNumOfImages(document));
    }
}
