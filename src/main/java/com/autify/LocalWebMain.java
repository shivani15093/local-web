package com.autify;

import com.autify.service.WebPageService;
import com.autify.service.impl.WebPageServiceImpl;

import static com.autify.constants.AppConstants.*;

public class LocalWebMain {
    public static void main(String[] args)
    {
        WebPageService webPageService = new WebPageServiceImpl();
        if(System.getProperty(META_DATA)!=null
                && System.getProperty(META_DATA).equalsIgnoreCase(TRUE)){
            for(String url : args){
                System.out.println(webPageService.getMetaData(url));
            }
        }
        else{
            for(String url : args){
                webPageService.downloadWebPage(url);
            }
        }
    }
}
