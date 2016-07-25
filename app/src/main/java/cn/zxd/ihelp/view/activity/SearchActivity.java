package cn.zxd.ihelp.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zxd.ihelp.R;

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
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_title_left)
    void backClick(View view) {
        onBackPressed();
    }

}
