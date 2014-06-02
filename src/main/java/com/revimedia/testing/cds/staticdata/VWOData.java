package com.revimedia.testing.cds.staticdata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dstoianov on 5/19/2014, 3:44 PM.
 */
public class VWOData {

    public static Map<String, List<String>> map = new HashMap<String, List<String>>();

    public static List<String> AUTO_MFS = Arrays.asList("cds-auto-mfs", "cds-auto-mfs-step2", "cds-auto-mfs-step3", "cds-auto-mfs-step4");
    private static List<String> AUTO_S = Arrays.asList("cds-auto-s", "cds-auto-s-step2", "cds-auto-s-step3", "cds-auto-s-step4");


    static {
        map.put("auto/s", AUTO_S);
        map.put("auto/mfs", AUTO_MFS);
    }
}
