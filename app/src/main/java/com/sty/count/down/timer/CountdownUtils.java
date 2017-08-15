package com.sty.count.down.timer;


import android.os.Handler;
import android.util.Log;

/**
 * Created by shity on 2017/8/15/0015.
 */

public class CountdownUtils {
    private int mTotal;
    private int mCurrent;
    private long mStartTime;
    private Handler mHandler;
    private ICountdownListener listener;

    public CountdownUtils(int total, ICountdownListener listener){
        this.mTotal = total;
        this.mCurrent = 0;
        this.listener = listener;
        this.mHandler = new Handler();
    }

    public interface ICountdownListener{
        public void onProgress(int leftTime);

        public void onFinish();
    }

    public void start(){
        if(mHandler != null){
            mHandler.removeCallbacks(countdownRunnable);
        }
        mCurrent = 0;
        mStartTime = System.currentTimeMillis();
        if(listener != null){
            listener.onProgress(mTotal);
            mCurrent ++ ;
        }
        mHandler.postDelayed(countdownRunnable, 1000);
    }

    private Runnable countdownRunnable = new Runnable() {
        @Override
        public void run() {
            int t = mTotal - mCurrent;
            if(listener != null){
                if(t > 0){
                    listener.onProgress(t);
                    mCurrent ++ ;
                    long interval = (mCurrent * 1000) - ((System.currentTimeMillis() - mStartTime));
                    Log.i("Tag", "countdownTime: "+ t + " next interval: " + interval);
                    mHandler.postDelayed(countdownRunnable, interval);
                }else{
                    listener.onFinish();
                    long endTime = System.currentTimeMillis();
                    Log.i("Tag", "totalTime: " + (endTime - mStartTime) );
                }
            }

        }
    };

    public void stop(){
        if(mHandler != null){
            mHandler.removeCallbacks(countdownRunnable);
        }
    }
}
