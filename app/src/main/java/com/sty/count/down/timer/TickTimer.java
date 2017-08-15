package com.sty.count.down.timer;

import android.os.CountDownTimer;
import android.util.Log;

/**
 * Created by shity on 2017/8/15/0015.
 */

public class TickTimer {
    private int timeout;

    private Timer timer;
    private OnTickTimerListener listener;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public interface OnTickTimerListener{
        void onTick(long leftTime);

        void onFinish();
    }

    private class Timer extends CountDownTimer {
        private OnTickTimerListener listener;

        Timer(long timeout, long tickInterval){
            super(timeout * 1000, tickInterval * 1000);
        }

        void setTimeCountListener(OnTickTimerListener listener){
            this.listener = listener;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if(listener != null){
                listener.onTick(millisUntilFinished / 1000);
            }
            Log.i("Tag", "millisUntilFinished:-->" + millisUntilFinished);
        }

        @Override
        public void onFinish() {
            if(listener != null){
                listener.onFinish();
            }
        }
    }

    public TickTimer(int timeout, OnTickTimerListener listener){
        this.timeout = timeout;
        this.listener = listener;
    }

    public void start(){
        if(timer != null){
            timer.cancel();
        }
        updateTimer(timeout);
        timer.start();
    }

    public void stop(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

    private void updateTimer (int timeout){
        if(timeout <= 0){
            return;
        }
        timer = new Timer(timeout, 1);
        timer.setTimeCountListener(listener);
    }
}
