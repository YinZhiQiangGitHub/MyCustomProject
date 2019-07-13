package com.mycustomproject.main;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mycustomproject.R;
import com.mycustomproject.listener.View1ClickListener;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.Holder> {

    private Context context;
    private List<String> list;
    private View1ClickListener listener;
    public MainAdapter(Context context,List<String> list){
        this.context=context;
        this.list=list;
    }

    public  void setlView1ClickListener(View1ClickListener listener){
        this.listener=listener;
    }

    @Override
    public MainAdapter.Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View  view=LayoutInflater.from(context).inflate(R.layout.main_item,viewGroup,false);
        Holder holder=new Holder(view);
        holder.bt= view.findViewById(R.id.main_item_bt);
        return holder;
    }

    @Override
    public void onBindViewHolder(MainAdapter.Holder holder, final int i) {
            holder.bt.setText(list.get(i));
            holder.bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCliick(i);
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        Button bt;
        public Holder(View itemView) {
            super(itemView);
        }
    }

}
