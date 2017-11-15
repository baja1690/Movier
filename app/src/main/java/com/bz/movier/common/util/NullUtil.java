package com.bz.movier.common.util;

import java.util.Collection;

/**
 * Created by Cuong Pham on 11/9/17.
 */

public class NullUtil {
    public static boolean isNullOrEmpty(Collection collection){
        return collection == null || collection.isEmpty();
    }
    public static boolean isNullOrEmpty(String string){
        return string == null || (string.trim()).length() == 0;
    }
}
