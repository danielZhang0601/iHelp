package cn.zxd.older.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;
import cn.zxd.older.R;
import cn.zxd.older.model.User;
import cn.zxd.older.util.ValidateHelper;

/**
 * Created by admin on 16/7/16.
 */

public class SignUpNextActivity extends BaseTimerActivity {

    private static final int STATE_SMS = 1;
    private static final int STATE_PASSWORD = 2;
    private int state = STATE_SMS;

    @BindView(R.id.tv_sign_up_2_account)
    TextView tv_sign_up_2_account;

    @BindView(R.id.et_sign_up_2_sms)
    EditText et_sign_up_2_sms;

    @BindView(R.id.rl_sign_up_2_password)
    RelativeLayout rl_sign_up_2_password;

    @BindView(R.id.btn_sign_up_get_sms)
    Button btn_sign_up_get_sms;

    @BindView(R.id.et_sign_up_2_password)
    EditText et_sign_up_2_password;

    @BindView(R.id.btn_sign_up_2_submit)
    Button btn_sign_up_2_submit;

    private String account = null;

    public static void launchForResult(Activity activity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(activity, SignUpNextActivity.class);
        if (null != bundle && !bundle.isEmpty()) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_2);
        ButterKnife.bind(this);
        account = getIntent().getExtras().getString(User.USER_MOBILE);
        if (null != account && !account.isEmpty()) {
            tv_sign_up_2_account.setText(account);
        }
        startTimer();
    }

    @Override
    protected void smsTimerTaskRun() {
        if (--tickCount > 0) {
            btn_sign_up_get_sms.setEnabled(false);
            btn_sign_up_get_sms.setText(String.format("(%d)重获验证码", tickCount));
        } else {
            stopTimer();
            btn_sign_up_get_sms.setText(String.format("重新获取验证码"));
            btn_sign_up_get_sms.setEnabled(true);
        }
    }

    @OnClick(R.id.iv_sign_up_2_title_back)
    void onBack() {
        onBackPressed();
    }

    @OnClick(R.id.tv_sign_up_2_cancel)
    void onCancel() {
        setResult(RESULT_FIRST_USER);
        finish();
    }

    @OnClick(R.id.btn_sign_up_get_sms)
    void onGetSms() {

    }

    @OnClick(R.id.btn_sign_up_2_submit)
    void onSubmit(final View view) {
        if (state == STATE_SMS) {
            if (!ValidateHelper.validateSMS(et_sign_up_2_sms.getText().toString())) {
                Toast.makeText(view.getContext(), "请输入正确的验证码", Toast.LENGTH_SHORT).show();
            } else {
                BmobSMS.verifySmsCode(view.getContext(), account, et_sign_up_2_sms.getText().toString(), new VerifySMSCodeListener() {
                    @Override
                    public void done(BmobException e) {
                        if (null == e) {
                            //锁定验证码 显示密码
                            state = STATE_PASSWORD;
                            et_sign_up_2_sms.setEnabled(false);
                            rl_sign_up_2_password.setVisibility(View.VISIBLE);
                        } else {
                            //TODO 测试代码开始
                            state = STATE_PASSWORD;
                            et_sign_up_2_sms.setEnabled(false);
                            rl_sign_up_2_password.setVisibility(View.VISIBLE);
                            //TODO 测试代码结束
                            Toast.makeText(view.getContext(), "验证码不正确", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } else if (state == STATE_PASSWORD) {
            if (!ValidateHelper.validatePassword(et_sign_up_2_password.getText().toString())) {
                Toast.makeText(view.getContext(), "密码包含字母数字,长度为8到20位", Toast.LENGTH_SHORT).show();
            } else {
                User user = new User();
                user.setUsername(account);
                user.setPassword(et_sign_up_2_password.getText().toString());
                user.setMobilePhoneNumber(account);
                user.setMobilePhoneNumberVerified(true);
                user.signUp(view.getContext(), new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(view.getContext(), "注册成功", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(view.getContext(), s, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}
