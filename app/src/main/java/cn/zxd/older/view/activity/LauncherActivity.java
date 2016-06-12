package cn.zxd.older.view.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.badoo.mobile.util.WeakHandler;

public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        View contentView = new View(this);
        setContentView(contentView);
        contentView.setBackgroundColor(Color.WHITE);
        WeakHandler mHandler = new WeakHandler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.launch(LauncherActivity.this);
                LauncherActivity.this.finish();
            }
        }, 500);
    }
}
