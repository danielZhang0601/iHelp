package cn.zxd.older.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.badoo.mobile.util.WeakHandler;

public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = new View(this);
        setContentView(contentView);
        contentView.setBackgroundColor(Color.WHITE);
        WeakHandler mHandler = new WeakHandler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toNext();
            }
        }, 500);
    }

    private void toNext() {
        LoginActivity.launch(this);
    }
}
