package cn.zxd.older.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;
import cn.zxd.older.R;
import cn.zxd.older.model.User;
import cn.zxd.older.util.SoftInputHelper;
import cn.zxd.older.util.ValidateHelper;

/**
 * Created by Daniel on 2016/6/21.
 */
public class SignUpActivity extends BaseTimerActivity {

    @BindView(R.id.til_account)
    TextInputLayout til_account;

    @BindView(R.id.btn_get_sms)
    Button btn_get_sms;

    @BindView(R.id.til_sms)
    TextInputLayout til_sms;

    @BindView(R.id.til_password)
    TextInputLayout til_password;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, SignUpActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_or_get_password);
        ButterKnife.bind(this);
    }

    @Override
    protected void smsTimerTaskRun() {
        if (tickCount > 0) {
            tickCount--;
            btn_get_sms.setText(tickCount + "s");
        } else {
            stopTimer();
            btn_get_sms.setText(R.string.get_sms_code);
        }
    }

    @OnClick(R.id.btn_get_sms)
    void getSmsCode(View view){
        SoftInputHelper.closeSoftInput(view);
        if (ValidateHelper.validatePhone(til_account.getEditText().getText().toString())) {
            BmobSMS.requestSMSCode(this, til_account.getEditText().getText().toString(), "爱无疆", new RequestSMSCodeListener(){

                @Override
                public void done(Integer integer, BmobException e) {
                    if (null == e) {
                        startTimer();
                    }
                }
            });
        } else {
            accountEditDone(false);
        }
    }

    @OnClick(R.id.btn_submit)
    void submit(final View view) {
        SoftInputHelper.closeSoftInput(view);
        if (ValidateHelper.validatePhone(til_account.getEditText().getText().toString())
                && ValidateHelper.validateSMS(til_sms.getEditText().getText().toString())
                && ValidateHelper.validatePassword(til_password.getEditText().getText().toString())) {
            BmobSMS.verifySmsCode(view.getContext(), til_account.getEditText().getText().toString(),
                    til_sms.getEditText().getText().toString(), new VerifySMSCodeListener(){

                        @Override
                        public void done(BmobException e) {
                            if (null == e) {
                                User user = new User();
                                user.setUsername(til_account.getEditText().getText().toString());
                                user.setPassword(til_password.getEditText().getText().toString());
                                user.signUp(view.getContext(), new SaveListener(){

                                    @Override
                                    public void onSuccess() {
                                        Toast.makeText(view.getContext(), "sign up success", Toast.LENGTH_SHORT).show();
                                        SignUpActivity.this.finish();
                                    }

                                    @Override
                                    public void onFailure(int i, String s) {
                                        Toast.makeText(view.getContext(), s, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });
        }
    }

    @OnFocusChange(R.id.et_account)
    void accountEditDone(boolean hasFocus) {
        if (!hasFocus && !ValidateHelper.validatePhone(til_account.getEditText().getText().toString())) {
            til_account.setError("input valid phone");
        } else {
            til_account.setErrorEnabled(false);
            til_account.setError(null);
        }
    }

    @OnFocusChange(R.id.et_sms)
    void smsEditDone(boolean hasFocus) {
        if (!hasFocus && !ValidateHelper.validateSMS(til_sms.getEditText().getText().toString())) {
            til_sms.setError("input valid sms");
        } else {
            til_sms.setErrorEnabled(false);
            til_sms.setError(null);
        }
    }

    @OnFocusChange(R.id.et_password)
    void passwordEditDone(boolean hasFocus) {
        if (!hasFocus && !ValidateHelper.validatePassword(til_password.getEditText().getText().toString())) {
            til_password.setError("input valid password, must between 8 to 20 characters");
        } else {
            til_password.setErrorEnabled(false);
            til_password.setError(null);
        }
    }

}
