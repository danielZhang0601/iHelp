package cn.zxd.older.view.activity;

import android.content.Context;
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

    @BindView(R.id.et_sign_up_1_account)
    EditText et_sign_up_1_account;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, SignUpActivity.class));
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
                        SignUpNextActivity.launch(SignUpActivity.this, data);
//                    }
//                }
//            });
        }
    }

//    @Override
//    protected void smsTimerTaskRun() {
////        if (tickCount > 0) {
////            tickCount--;
////            btn_get_sms.setText(tickCount + "s");
////        } else {
////            stopTimer();
////            btn_get_sms.setText(R.string.get_sms_code);
////        }
//    }
//
//    @OnClick(R.id.btn_get_sms)
//    void getSmsCode(View view){
//        SoftInputHelper.closeSoftInput(view);
//        if (ValidateHelper.validatePhone(til_account.getEditText().getText().toString())) {
//            BmobSMS.requestSMSCode(this, til_account.getEditText().getText().toString(), "爱无疆", new RequestSMSCodeListener(){
//
//                @Override
//                public void done(Integer integer, BmobException e) {
//                    if (null == e) {
//                        startTimer();
//                    }
//                }
//            });
//        } else {
//            accountEditDone(false);
//        }
//    }
//
//    @OnClick(R.id.btn_submit)
//    void submit(final View view) {
//        SoftInputHelper.closeSoftInput(view);
//        if (ValidateHelper.validatePhone(til_account.getEditText().getText().toString())
//                && ValidateHelper.validateSMS(til_sms.getEditText().getText().toString())
//                && ValidateHelper.validatePassword(til_password.getEditText().getText().toString())) {
//            BmobSMS.verifySmsCode(view.getContext(), til_account.getEditText().getText().toString(),
//                    til_sms.getEditText().getText().toString(), new VerifySMSCodeListener(){
//
//                        @Override
//                        public void done(BmobException e) {
//                            if (null == e) {
//                                User user = new User();
//                                user.setUsername(til_account.getEditText().getText().toString());
//                                user.setPassword(til_password.getEditText().getText().toString());
//                                user.signUp(view.getContext(), new SaveListener(){
//
//                                    @Override
//                                    public void onSuccess() {
//                                        Toast.makeText(view.getContext(), "sign up success", Toast.LENGTH_SHORT).show();
//                                        SignUpActivity.this.finish();
//                                    }
//
//                                    @Override
//                                    public void onFailure(int i, String s) {
//                                        Toast.makeText(view.getContext(), s, Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            }
//                        }
//                    });
//        }
//    }
//
//    @OnFocusChange(R.id.et_account)
//    void accountEditDone(boolean hasFocus) {
//        if (!hasFocus && !ValidateHelper.validatePhone(til_account.getEditText().getText().toString())) {
//            til_account.setError("input valid phone");
//        } else {
//            til_account.setErrorEnabled(false);
//            til_account.setError(null);
//        }
//    }
//
//    @OnFocusChange(R.id.et_sms)
//    void smsEditDone(boolean hasFocus) {
//        if (!hasFocus && !ValidateHelper.validateSMS(til_sms.getEditText().getText().toString())) {
//            til_sms.setError("input valid sms");
//        } else {
//            til_sms.setErrorEnabled(false);
//            til_sms.setError(null);
//        }
//    }
//
//    @OnFocusChange(R.id.et_password)
//    void passwordEditDone(boolean hasFocus) {
//        if (!hasFocus && !ValidateHelper.validatePassword(til_password.getEditText().getText().toString())) {
//            til_password.setError("input valid password, must between 8 to 20 characters");
//        } else {
//            til_password.setErrorEnabled(false);
//            til_password.setError(null);
//        }
//    }

}
