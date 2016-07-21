package cn.zxd.older.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.zxd.older.R;

/**
 * Created by danielzhang on 16/7/21.
 */

public class BonusActivity extends BaseActivity {

    public static void launch(Context context, Bundle data) {
        Intent intent = new Intent(context, BonusActivity.class);
        if (null != data && !data.isEmpty()) {
            intent.putExtras(data);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);
    }
}
