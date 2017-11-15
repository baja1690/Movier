package com.bz.movier.common.util;

/**
 * Created by Cuong Pham on 11/9/17.
 */

public class UtilConverter {
    public static String convertImageUrl(String backdropPath) {
        return Config.BASE_URL_IMAGE + backdropPath;
    }
}
