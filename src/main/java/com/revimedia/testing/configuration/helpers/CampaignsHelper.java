package com.revimedia.testing.configuration.helpers;

/**
 * Created by dstoianov on 6/2/2014, 3:17 PM.
 */
public class CampaignsHelper {

    public static String getCampaignName(String url) {
        // receive  "http://development.stagingrevi.com/auto/s/";
        // return   "auto/mfs"
        int i = url.indexOf(".com") + 5;
        return url.substring(i, url.length() - 1);
    }

}
