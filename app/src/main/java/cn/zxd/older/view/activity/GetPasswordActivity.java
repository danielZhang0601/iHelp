package cn.zxd.older.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;
import cn.zxd.older.R;
import cn.zxd.older.util.ValidateHelper;

/**
 * Created by Daniel on 2016/6/21.
 */
public class GetPasswordActivity extends BaseTimerActivity {

    @BindView(R.id.til_account)
    TextInputLayout til_account;

    @BindView(R.id.btn_get_sms)
    Button btn_get_sms;

    @BindView(R.id.til_sms)
    TextInputLayout til_sms;

    @BindView(R.id.til_password)
    TextInputLayout til_password;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, GetPasswordActivity.class));
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
        if (!hasFocus && !ValidateHelper.validatePhone(til_sms.getEditText().getText().toString())) {
            til_sms.setError("input valid sms");
        } else {
            til_sms.setErrorEnabled(false);
            til_sms.setError(null);
        }
    }

    @OnFocusChange(R.id.et_password)
    void passwordEditDone(boolean hasFocus) {
        if (!hasFocus && !ValidateHelper.validatePhone(til_password.getEditText().getText().toString())) {
            til_password.setError("input valid password, must between 8 to 20 characters");
        } else {
            til_password.setErrorEnabled(false);
            til_password.setError(null);
        }
    }
}
