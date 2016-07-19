package cn.zxd.older.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zxd.older.R;
import cn.zxd.older.model.User;
import cn.zxd.older.util.SoftInputHelper;
import cn.zxd.older.util.ValidateHelper;

/**
 * Created by Daniel on 2016/6/21.
 */
public class SignUpActivity extends BaseActivity {

    public static final int REQUEST_CODE_NEXT = 100;

    @BindView(R.id.et_sign_up_1_account)
    EditText et_sign_up_1_account;

    public static void launchForResult(Activity activity, int requestCode) {
        activity.startActivityForResult(new Intent(activity, SignUpActivity.class), requestCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_1);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_sign_up_1_cancel)
    void onCancel() {
        onBackPressed();
    }

    @OnClick(R.id.tv_sign_up_1_login)
    void toLogin() {
        onBackPressed();
    }

    @OnClick(R.id.btn_sign_up_1_submit)
    void next(View view) {
        //关闭软键盘
        SoftInputHelper.closeSoftInput(view);
        //校验手机号码
        if (ValidateHelper.validatePhone(et_sign_up_1_account.getText().toString())) {
//            BmobSMS.requestSMSCode(view.getContext(), et_sign_up_1_account.getText().toString(), "爱无疆", new RequestSMSCodeListener() {
//                @Override
//                public void done(Integer integer, BmobException e) {
//                    if (null == e) {
            Bundle data = new Bundle();
            data.putString(User.USER_MOBILE, et_sign_up_1_account.getText().toString());
            SignUpNextActivity.launchForResult(SignUpActivity.this, data, REQUEST_CODE_NEXT);
//                    }
//                }
//            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_NEXT) {
            if (resultCode == RESULT_OK || resultCode == RESULT_FIRST_USER) {
                setResult(resultCode);
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
