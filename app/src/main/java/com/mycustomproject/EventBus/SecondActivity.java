package com.mycustomproject.EventBus;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.mycustomproject.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends Activity {
    @BindView(R.id.textView3)
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ButterKnife.bind(this).unbind();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void wgh2(TestEvent event) {
        textView3.setText("同样接收到了msg=" + event.getMsg());
    }

    @OnClick(R.id.textView3)
    public void onViewClicked() {

    }
}
