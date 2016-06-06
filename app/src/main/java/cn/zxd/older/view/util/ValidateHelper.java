package cn.zxd.older.view.util;

import java.util.regex.Pattern;

/**
 * Created by danielzhang on 16/6/6.
 */

public class ValidateHelper {

    //
    public static final String VALIDATE_SMS = "^[0-9]{6}$";

    public static final String VALIDATE_PHONE = "^1(3[0-9]|5[0-35-9]|8[025-9])[0-9]{8}$";

    public static boolean validate(String srcStr, String patternStr) {
        Pattern pattern = Pattern.compile(patternStr);
        return pattern.matcher(srcStr).matches();
    }

    public static boolean validatePhone(String phone) {
        return validate(phone, VALIDATE_PHONE);
    }

    public static boolean validateSMS(String sms) {
        return validate(sms, VALIDATE_SMS);
    }

}
