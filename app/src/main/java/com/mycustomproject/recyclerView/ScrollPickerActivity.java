package com.mycustomproject.recyclerView;

import android.view.View;

import com.mycustomproject.R;
import com.mycustomproject.main.baseUtil.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ScrollPickerActivity extends BaseActivity {


    private StringScrollPicker mPickerHorizontal3;
    @Override
    public int getLayoutResId() {
        return R.layout.activity_recycler_view;
    }

    @Override
    public void initBindingID() {
        mPickerHorizontal3 = (StringScrollPicker) findViewById(R.id.picker_05_horizontal);
   }

    public void initView() {

        List<String> list0=new ArrayList<>();
        for(int i=1;i<11;i++){
            list0.add(""+i);
        }
        List<CharSequence> newList = new ArrayList<>();
        for (String s : list0) {
            newList.add(s);
        }
        mPickerHorizontal3.setData(newList,1);
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastShortShow(""+mPickerHorizontal3.getSelectedItem());
            }
        });

    }


}
