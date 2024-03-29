package com.mycustomproject.recyclerView;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mycustomproject.R;

import java.util.List;

public class AutoPollAdapter extends RecyclerView.Adapter<AutoPollAdapter.BaseViewHolder> {
    private final List<String> mData;

    public AutoPollAdapter(List<String> list) {
        this.mData = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        BaseViewHolder holder = new BaseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        String data = mData.get(position % mData.size());
        holder.tv.setText(data);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    class BaseViewHolder extends RecyclerView.ViewHolder{
        Button tv;
        public BaseViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.main_item_bt);
        }
    }
}
