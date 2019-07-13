package com.mycustomproject.HorizontalselectedView;

import androidx.appcompat.app.AppCompatActivity;

import com.mycustomproject.R;
import com.mycustomproject.main.baseUtil.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SelectViewActivity extends BaseActivity {
    private List<String> strings = new ArrayList<String>();
    private HorizontalselectedView matching_hsMain;
    @Override
    public int getLayoutResId() {
        return R.layout.activity_select_view;
    }

    @Override
    public void initBindingID() {
        matching_hsMain = (HorizontalselectedView) findViewById(R.id.matching_edit);
    }

    @Override
    public void initView() {
        initdata(5);
    }

    private void initdata(int pos) {
        strings.clear();
        int num=0;
        for (int i = 1; i <11; i++) {
            strings.add(i + "");
        }
        if (pos<=10){
            num=1;
        }else {
            num=pos/10;
        }
        Log_i("---------matching_hsMain.setData---------->"+strings.size());
        Log_i("---------matching_hsMain.setData---------->"+num);
        matching_hsMain.setData(strings,num);
    }

}
