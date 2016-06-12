package cn.zxd.older.manager;

import android.content.Context;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SaveListener;

/**
 */
public class UserManager {

    private BmobUser user;

    private boolean isLogined = false;

    private Context mContext;

    private static UserManager ourInstance;

    public static UserManager getInstance(Context context) {
        if (null == ourInstance) {
            ourInstance = new UserManager(context);
        }
        return ourInstance;
    }

    private UserManager(Context context) {
        user = new BmobUser();
        mContext = context;
    }

    public boolean isLogined() {
        return isLogined;
    }

    public void getSMS(String mobile) {
        user.setMobilePhoneNumber(mobile);
        BmobSMS.requestSMSCode(mContext, user.getMobilePhoneNumber(), "模板名称", new RequestSMSCodeListener() {
            @Override
            public void done(Integer integer, BmobException e) {

            }
        });
    }

    public void login(String mobile, String smsCode, final SaveListener saveListener) {
        user.signOrLogin(mContext, smsCode, new SaveListener() {
            @Override
            public void onSuccess() {
                isLogined = true;
                saveListener.onSuccess();
            }

            @Override
            public void onFailure(int i, String s) {
                isLogined = false;
                saveListener.onFailure(i, s);
            }
        });
    }
}
