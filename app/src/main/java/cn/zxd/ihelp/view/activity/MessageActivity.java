package cn.zxd.ihelp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by danielzhang on 16/7/25.
 */

public class MessageActivity extends BaseActivity {

    public static void launch(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MessageActivity.class);
        if (null != bundle && !bundle.isEmpty()) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
