package cn.zxd.older.view.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.zxd.older.R;
import cn.zxd.older.model.User;
import cn.zxd.older.view.fragment.DemandFragment;
import cn.zxd.older.view.fragment.SupplyFragment;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.iv_title_left)
    ImageView iv_title_left;

    @BindView(R.id.nav_view)
    NavigationView nav_view;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    DemandFragment demandFragment;
    SupplyFragment supplyFragment;


    protected static void launch(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        setDefaultFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        BmobUser user = BmobUser.getCurrentUser(this);
        if (null != user) {
            TextView textView = (TextView) nav_view.findViewById(R.id.textViewName);
            textView.setText("User" + user.getUsername());
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (null == User.getCurrentUser(this)) {
            LoginActivity.launch(this);
            return true;
        } else {
            switch (item.getItemId()) {
                case R.id.nav_wallet:
                    return true;
                case R.id.nav_bonus:
                    return true;
                case R.id.nav_message:
                    return true;
                case R.id.nav_share:
                    return true;
                case R.id.nav_join:
                    return true;
                case R.id.nav_settings:
                    return true;
                default:
                    return false;
            }
        }
    }

    private void initViews() {
        iv_title_left.setImageResource(R.mipmap.ic_contact_picture);
        nav_view.setNavigationItemSelectedListener(this);
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        demandFragment = new DemandFragment();
        transaction.replace(R.id.fl_main, demandFragment);
        transaction.commit();
    }

    @OnClick(R.id.rl_title_left)
    void titleLeftClick(View view) {
        drawer_layout.openDrawer(GravityCompat.START);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        demandFragment.onActivityResult(requestCode, resultCode, data);
    }
}
