package com.example.myrobot;

import android.content.Context;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sean-duan on 2017/7/13.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<ListData> mlist;
    private Context context ;
    private LayoutInflater mInflater;
    public MyAdapter(List<ListData> l,Context context){
        this.mlist = l;
        this.context = context ;
        mInflater = LayoutInflater.from(context);
    }
    static  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tx ;
        public TextView name ;
        public TextView time ;
        public int flag ;
        public ViewHolder(View itemView) {
            super(itemView);
            tx = (TextView) itemView.findViewById(R.id.tv_chatcontent);
            name = (TextView) itemView.findViewById(R.id.tv_username);
            time = (TextView) itemView.findViewById(R.id.tv_sendtime);

        }
        public ViewHolder getholder(ViewHolder holder){
            return holder;
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListData listData = mlist.get(viewType);
        int flag = listData.getFlag();
        View view = null;
        if(flag==ListData.GET){
            view = mInflater.inflate(R.layout.chatting_item_msg_text_left,null);
        }else{
            view = mInflater.inflate(R.layout.chatting_item_msg_text_right,null);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListData listData = mlist.get(position);

        holder.tx.setText(listData.getContent());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
