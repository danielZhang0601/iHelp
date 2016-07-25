package cn.zxd.ihelp.util;

import java.util.regex.Pattern;

/**
 * Created by danielzhang on 16/6/6.
 */

public class ValidateHelper {

    //
    public static final String VALIDATE_SMS = "^[0-9]{6}$";

    public static final String VALIDATE_PHONE = "^1(3[0-9]|5[0-35-9]|8[025-9])[0-9]{8}$";

    public static final String VALIDATE_PASSWORD = "^[0-9a-zA-Z]{8,20}$";

    public static boolean validate(String srcStr, String patternStr) {
        if (null == srcStr) return false;
        Pattern pattern = Pattern.compile(patternStr);
        return pattern.matcher(srcStr).matches();
    }

    public static boolean validatePhone(String phone) {
        return validate(phone, VALIDATE_PHONE);
    }

    public static boolean validatePassword(String password) {
        return validate(password, VALIDATE_PASSWORD);
    }

    public static boolean validateSMS(String sms) {
        return validate(sms, VALIDATE_SMS);
    }

}
