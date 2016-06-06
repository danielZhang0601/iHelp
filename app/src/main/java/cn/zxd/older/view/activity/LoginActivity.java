package cn.zxd.older.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import cn.zxd.older.R;
import cn.zxd.older.manager.UserManager;
import cn.zxd.older.view.util.ValidateHelper;

/**
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_login_account)
    protected EditText et_login_account;

    @BindView(R.id.et_login_sms_code)
    protected EditText et_login_sms_code;

    protected static void launch(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @OnClick(R.id.btn_login_get_sms)
    protected void getSmsCode(View view) {
        if (ValidateHelper.validatePhone(et_login_account.getText().toString())){
            UserManager.getInstance(LoginActivity.this).getSMS(et_login_account.getText().toString());
        }
    }

    @OnClick(R.id.btn_login_submit)
    protected void submit(View view) {

    }

}