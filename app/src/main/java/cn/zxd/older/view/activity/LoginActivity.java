package cn.zxd.older.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import cn.bmob.v3.listener.SaveListener;
import cn.zxd.older.R;
import cn.zxd.older.model.User;
import cn.zxd.older.util.SoftInputHelper;
import cn.zxd.older.util.ValidateHelper;

/**
 */
public class LoginActivity extends BaseActivity {

    private static final int REQUEST_CODE_SIGN_UP = 100;
    private static final int REQUEST_CODE_GET_PASSWORD = 101;

    @BindView(R.id.til_login_account)
    protected TextInputLayout til_login_account;

    @BindView(R.id.til_login_password)
    protected TextInputLayout til_login_password;

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
    }

    @OnClick(R.id.rl_login_root)
    protected void rootClick(View view) {
        SoftInputHelper.closeSoftInput(view);
    }

    @OnClick(R.id.rl_title_left)
    protected void backClick() {
        onBackPressed();
    }

    @OnClick(R.id.btn_login_submit)
    protected void submitClick(View view) {
        SoftInputHelper.closeSoftInput(view);
        if (ValidateHelper.validatePhone(til_login_account.getEditText().getText().toString())
                && ValidateHelper.validatePassword(til_login_password.getEditText().getText().toString())) {
            User user = new User();
            user.setUsername(til_login_account.getEditText().getText().toString());
            user.setMobilePhoneNumber(til_login_account.getEditText().getText().toString());
            user.setPassword(til_login_password.getEditText().getText().toString());
            user.login(view.getContext(), new SaveListener() {
                @Override
                public void onSuccess() {
                    LoginActivity.this.finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            });
        } else if (!ValidateHelper.validatePhone(til_login_account.getEditText().getText().toString())) {
            accountEditDone(false);
        } else {
            passwordEditDone(false);
        }
    }

    @OnClick(R.id.tv_login_sign_up)
    protected void signUpClick(View view) {
        SignUpActivity.launchForResult(LoginActivity.this, REQUEST_CODE_SIGN_UP);
    }

    @OnClick(R.id.tv_login_get_password)
    protected void getPasswordClick(View view) {
        GetPasswordActivity.launch(view.getContext());
    }

    @OnFocusChange(R.id.et_login_account)
    protected void accountEditDone(boolean hasFocus) {
        if (!hasFocus && !ValidateHelper.validatePhone(til_login_account.getEditText().getText().toString())) {
            til_login_account.setError("input valid phone");
        } else {
            til_login_account.setErrorEnabled(false);
            til_login_account.setError(null);
        }
    }

    @OnFocusChange(R.id.et_login_password)
    protected void passwordEditDone(boolean hasFocus) {
        if (!hasFocus && !ValidateHelper.validatePassword(til_login_password.getEditText().getText().toString())) {
            til_login_password.setError("input valid password, must between 8 to 20 characters");
        } else {
            til_login_password.setErrorEnabled(false);
            til_login_password.setError(null);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_SIGN_UP) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        } else if (requestCode == REQUEST_CODE_GET_PASSWORD) {

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}