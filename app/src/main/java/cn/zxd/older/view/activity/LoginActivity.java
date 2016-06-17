package cn.zxd.older.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.UserManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.zxd.older.R;
import cn.zxd.older.model.User;
import cn.zxd.older.view.util.ValidateHelper;

/**
 */
public class LoginActivity extends BaseActivity {

    public static final int MSG_SMS_SENDED = 1;
    public static final int MSG_SMS_COUNT = 2;
    public static final int MSG_SMS_DONE = 3;

    @BindView(R.id.et_login_account)
    protected EditText et_login_account;

    @BindView(R.id.et_login_sms_code)
    protected EditText et_login_sms_code;

    private LoginHandler loginHandler = new LoginHandler(this);

    protected static void launch(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginHandler.removeCallbacksAndMessages(null);
    }

    @OnClick(R.id.btn_login_get_sms)
    protected void getSmsCode(final View view) {
        view.setEnabled(false);
        if (ValidateHelper.validatePhone(et_login_account.getText().toString())) {
            User user = new User();
            user.setMobilePhoneNumber(et_login_account.getText().toString());
        } else {
            Toast.makeText(view.getContext(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_login_submit)
    protected void submit(View view) {

    }

    private static class LoginHandler extends Handler {
        private final WeakReference<LoginActivity> activity;

        public LoginHandler(LoginActivity act) {
            activity = new WeakReference<LoginActivity>(act);
        }

        @Override
        public void handleMessage(Message msg) {
        }
    }

}