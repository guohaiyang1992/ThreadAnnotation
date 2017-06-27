package com.simon.thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.simon.threadlib.annotation.Background;
import com.simon.threadlib.annotation.MethodWatch;
import com.simon.threadlib.annotation.UiThread;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testUiThread();
        testBackThread();
        testMethodTag();

    }

    @MethodWatch
    private void testMethodTag() {
    }

    @MethodWatch(tag = "ghy")
    @UiThread(delayTime = 1000)
    public void testUiThread() {
        Log.v("ghy", "uiThread:" + Thread.currentThread().getName());
    }
    @MethodWatch(tag = "ghy")
    @Background
    public void testBackThread() {
        Log.v("ghy", "backThread:" + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
