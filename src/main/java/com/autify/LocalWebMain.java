package com.autify;

import com.autify.service.DownloadWebPageService;
import com.autify.service.WebPageMetaDataService;
import com.autify.service.impl.DownloadWebPageServiceImpl;
import com.autify.service.impl.WebPageMetaDataServiceImpl;

import static com.autify.constants.AppConstants.META_DATA;
import static com.autify.constants.AppConstants.TRUE;

public class LocalWebMain {
    public static void main(String[] args)
    {
        DownloadWebPageService downloadWebPageService = new DownloadWebPageServiceImpl();
        WebPageMetaDataService webPageMetaDataService = new WebPageMetaDataServiceImpl();
        if(System.getProperty(META_DATA)!=null
                && System.getProperty(META_DATA).equalsIgnoreCase(TRUE)){
            for(String url : args){
                System.out.println(webPageMetaDataService.getMetaData(url));
            }
        }
        else{
            for(String url : args){
                downloadWebPageService.downloadWebPage(url);
            }
        }
    }
}
