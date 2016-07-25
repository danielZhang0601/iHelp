package cn.zxd.ihelp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zxd.ihelp.R;

/**
 * Created by danielzhang on 16/7/25.
 */

public class MessageActivity extends BaseActivity {

    @BindView(R.id.tv_title_center)
    TextView tv_title_center;

    @BindView(R.id.lv_message)
    ListView lv_message;

    public static void launch(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MessageActivity.class);
        if (null != bundle && !bundle.isEmpty()) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    @OnClick(R.id.rl_title_left)
    void onBack(){
        onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        initViews();
    }

    void initViews() {
        tv_title_center.setText("消息");
    }
}
