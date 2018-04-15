package com.example.noor.viewpager;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {
    ViewPager mPager;
    private static int currentPage=0;
    private static final Integer [] pic={
            R.drawable.minion,
            R.drawable.minion_on_rocket,
            R.drawable.supper_minion,
            R.drawable.minion_with_baloons
    };
    ArrayList<Integer> picArray=new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slideImage();
    }

    private void slideImage() {
        for (int i = 0; i <pic.length ; i++) {
            picArray.add(pic[i]);
        }
        mPager = findViewById(R.id.viewPager);
        mPager.setAdapter(new MyAdapter(this,picArray));
        CircleIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        final Handler handler=new Handler();
        final Runnable update=new Runnable() {
            @Override
            public void run() {
                if (currentPage==pic.length){
                    currentPage=0;
                }
                mPager.setCurrentItem(currentPage++,true);
            }
        };
        Timer swipeTimer=new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },3000,3000);
    }
}
