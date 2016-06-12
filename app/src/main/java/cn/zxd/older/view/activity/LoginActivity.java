package cn.zxd.older.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
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
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login_get_sms)
    protected void getSmsCode(View view) {
        if (ValidateHelper.validatePhone(et_login_account.getText().toString())){
            UserManager.getInstance(LoginActivity.this).getSMS(et_login_account.getText().toString());
        } else {
            Toast.makeText(view.getContext(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_login_submit)
    protected void submit(View view) {

    }

}