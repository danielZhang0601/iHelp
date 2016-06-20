package cn.zxd.older.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.zxd.older.R;

/**
 * Created by Daniel on 2016/6/21.
 */
public class GetPasswordActivity extends BaseTimerActivity {

    public static void launch(Context context) {
        context.startActivity(new Intent(context, GetPasswordActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_or_get_password);
    }
}
