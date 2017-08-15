package com.sty.count.down.timer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView mTvShow1;
    private TextView mTvShow2;
    private TextView mTvShow3;
    private TextView mTvShow4;

    Timer timer1 = new Timer();
    int recLen = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvShow1 = (TextView) findViewById(R.id.time_task_show_tv);
        mTvShow2 = (TextView) findViewById(R.id.show2);
        mTvShow3 = (TextView) findViewById(R.id.show3);
        mTvShow4 = (TextView) findViewById(R.id.show4);
    }

    //--------------------------------------1-------------------------------
    public void restart1(View v){
        timer1.schedule(task, 1000, 1000);
    }

    TimerTask task = new TimerTask(){

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recLen--;
                    mTvShow1.setText("倒计时：" + recLen + "s");
                    if(recLen < 0){
                        timer1.cancel();
                        mTvShow1.setText("获取验证码");
                    }
                }
            });
        }
    };

    //--------------------------------------2-------------------------------
    /**
     * 开始倒计时
     */
    public void restart2(View v){
        timer2.start();
    }

    /**
     * 取消倒计时
     */
    public void onCancel2(View v){
        timer2.cancel();
    }

    private CountDownTimer timer2 = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mTvShow2.setText((millisUntilFinished / 1000) + "s后重发");
        }

        @Override
        public void onFinish() {
            mTvShow2.setEnabled(true);
            mTvShow2.setText("获取验证码");
        }
    };


    //--------------------------------------3-------------------------------
    /**
     * 开始倒计时
     */
    public void restart3(View v){
        timer3.start();
    }

    /**
     * 取消倒计时
     */
    public void onCancel3(View v){
        timer3.stop();
    }

    private TickTimer timer3 = new TickTimer(10, new TickTimer.OnTickTimerListener() {
        @Override
        public void onTick(long leftTime) {
            String tick = leftTime + "s";
            mTvShow3.setText(tick + "后重发");
        }

        @Override
        public void onFinish() {
            String tick = "0s";
            mTvShow3.setText(tick + "后重发");
            mTvShow3.setText("获取验证码");
        }
    });

    //--------------------------------------4-------------------------------
    /**
     * 开始倒计时
     */
    public void restart4(View v){
        timer4.start();
    }

    /**
     * 取消倒计时
     */
    public void onCancel4(View v){
        timer4.stop();
    }

    public CountdownUtils timer4 = new CountdownUtils(10, new CountdownUtils.ICountdownListener() {
        @Override
        public void onProgress(int leftTime) {
            mTvShow4.setText(leftTime + "s后重发");
        }

        @Override
        public void onFinish() {
            mTvShow4.setText("0s后重发");
            mTvShow4.setText("获取验证码");
        }
    });

}
