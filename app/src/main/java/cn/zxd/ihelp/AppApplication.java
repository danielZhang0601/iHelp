package cn.zxd.ihelp;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 */

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Bmob
        BmobConfig config =new BmobConfig.Builder(this)
                .setApplicationId(Config.BMOB_APPLICATION_ID)
                .setConnectTimeout(Config.CONNECT_TIME_OUT / 1000)
                .build();
        Bmob.initialize(config);
        //初始化Umeng
        MobclickAgent.setDebugMode(BuildConfig.DEBUG);
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.enableEncrypt(true);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        MobclickAgent.onKillProcess(this);
    }
}
