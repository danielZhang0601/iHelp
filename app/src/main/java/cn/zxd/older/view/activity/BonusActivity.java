package cn.zxd.older.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zxd.older.R;
import cn.zxd.older.view.adapter.BonusAdapter;

/**
 * Created by danielzhang on 16/7/21.
 */

public class BonusActivity extends BaseActivity {

    @BindView(R.id.tv_title_center)
    TextView tv_title_center;

    @BindView(R.id.lv_bonus)
    ListView lv_bonus;

    private BonusAdapter bonusAdapter;

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
        ButterKnife.bind(this);
        initViews();
    }

    void initViews() {
        tv_title_center.setText("优惠券");
        bonusAdapter = new BonusAdapter(this);
        lv_bonus.setAdapter(bonusAdapter);
    }
}
