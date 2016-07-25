package cn.zxd.ihelp.view.activity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Daniel on 2016/6/21.
 */
public abstract class BaseTimerActivity extends BaseActivity {

    public static final int TIME_COUNT = 60;
    protected int tickCount = TIME_COUNT;
    protected Timer timer;

    protected void startTimer() {
        if (null == timer) {
            timer = new Timer();
            timer.schedule(new SmsTimerTask(), 0, 1000);
        }
    }

    protected void stopTimer() {
        if (null != timer) {
            timer.cancel();
            timer = null;
        }
    }

    protected abstract void smsTimerTaskRun();

    class SmsTimerTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    smsTimerTaskRun();
                }
            });
        }
    }

}
