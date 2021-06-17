package com.autify.service.impl;

import org.jsoup.nodes.Document;

import static com.autify.constants.AppConstants.IMAGES_CSS_QUERY;
import static com.autify.constants.AppConstants.LINKS_CSS_QUERY;

public class JsoupDocumentQuery {

    public int getNumOfLinks(Document doc) {
        return doc.select(LINKS_CSS_QUERY).size();
    }

    public int getNumOfImages(Document doc) {
        return doc.select(IMAGES_CSS_QUERY).size();
    }

}
