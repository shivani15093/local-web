package com.autify.service;

import com.autify.model.MetaDataModel;

public interface WebPageService {
    public void downloadWebPage(String webPage);
    public MetaDataModel getMetaData(String webPage);
}
