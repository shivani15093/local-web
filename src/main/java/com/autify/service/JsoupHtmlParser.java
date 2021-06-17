package com.autify.service;

import org.jsoup.nodes.Document;

public interface JsoupHtmlParser {
    public Document parse(String htmlFile);
}
