package cn.zxd.older;

import android.app.Application;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 */

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BmobConfig config =new BmobConfig.Builder(this)
                .setApplicationId(Config.BMOB_APPLICATION_ID)
                .setConnectTimeout(Config.CONNECT_TIME_OUT / 1000)
                .build();
        Bmob.initialize(config);
    }
}
