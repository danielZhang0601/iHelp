package cn.zxd.older.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import cn.zxd.older.R;

public class SearchActivity extends BaseActivity {

    public static void launch(Activity activity , Bundle bundle, int requestCode) {
        Intent intent = new Intent(activity, SearchActivity.class);
        if (null != bundle) intent.putExtras(bundle);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
