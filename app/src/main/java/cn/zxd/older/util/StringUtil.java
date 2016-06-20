package cn.zxd.older.util;

/**
 * Created by danielzhang on 16/6/13.
 */

public class StringUtil {

    public static boolean isNull(String string) {
        if (null == string)
            return true;
        else if (string.isEmpty() || string.length() < 1)
            return true;
        else
            return false;
    }

}
