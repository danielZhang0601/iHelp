package cn.zxd.older.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zxd.older.R;
import cn.zxd.older.model.User;

/**
 * Created by admin on 16/7/16.
 */

public class SignUpNextActivity extends BaseTimerActivity {

    @BindView(R.id.tv_sign_up_2_account)
    TextView tv_sign_up_2_account;

    public static void launch(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SignUpNextActivity.class);
        if (null != bundle && !bundle.isEmpty()) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_2);
        ButterKnife.bind(this);
        String account = getIntent().getExtras().getString(User.USER_MOBILE);
        if (null != account && !account.isEmpty()) {
            tv_sign_up_2_account.setText(account);
        }
    }

    @Override
    protected void smsTimerTaskRun() {

    }

    @OnClick(R.id.iv_sign_up_2_title_back)
    void onBack() {
        onBackPressed();
    }

    @OnClick(R.id.tv_sign_up_2_cancel)
    void onCancel() {

    }

    @OnClick(R.id.btn_sign_up_2_submit)
    void onSubmit(View view) {

    }
}
