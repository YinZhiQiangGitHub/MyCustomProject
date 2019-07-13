package com.mycustomproject.EventBus;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.mycustomproject.R;
import com.mycustomproject.main.baseUtil.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class EventBusActivity extends BaseActivity {
    public ProgressBar progressBar = null;
    public int time = 0;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_event_bus;
    }

    @Override
    public void initBindingID() {  }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(TestEvent event) {
        progressBar.setProgress(event.getMsg());

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.button, R.id.button1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (time < 100) {
                            time += 15;
                            EventBus.getDefault().post(new TestEvent(time));
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                break;
            case R.id.button1:
                EventBus.getDefault().postSticky(new TestEvent(time));
                Intent intent = new Intent(EventBusActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
        }
    }
}
