package com.autify.service.impl;

import com.autify.service.JsoupHtmlParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

import static com.autify.constants.AppConstants.UTF_8;

public class JsoupHtmlParserImpl implements JsoupHtmlParser {
    @Override
    public Document parse(String htmlFile){
        Document doc = null;
        try {
            doc = Jsoup.parse(new File(htmlFile), UTF_8);
        }
        catch (IOException e) {
            System.out.println("IOException raised - " + e.getMessage());
            System.exit(0);
        }
        return doc;
    }
}
