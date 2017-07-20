package com.example.myrobot;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by sean-duan on 2017/7/12.
 */

public class TextAdapter extends BaseAdapter {
    static class viewHoler{
        public TextView tx ;
        public TextView name ;
        public TextView time ;
        public int flag ;
    }
    private List<ListData> mlist;
    private Context context ;
    private LayoutInflater mInflater;
    private View view ;
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            viewholer.tx.setText((String)msg.obj);
//        }
//    };
    public TextAdapter(List<ListData> mlist,Context context) {
        this.mlist = mlist;
        this.context = context ;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        String s = mlist.get(position).getContent();
        int flag = mlist.get(position).getFlag();
//
//
//        viewHoler viewholer = null;
//
//        if(convertView==null){
//            //判断是谁发送的信息
//            viewholer = new viewHoler();
//
//            //viewHoler_left.tx =(TextView) view_left.findViewById(R.id.tv_chatcontent_left);
//           // viewHoler_right.tx = (TextView) view_right.findViewById(R.id.tv_chatcontent_right);
//            if(flag==ListData.GET){
//               // view_right.setVisibility(View.GONE);
//
//                convertView = mInflater.inflate(R.layout.chatting_item_msg_text_left,null);
//                viewholer.tx = (TextView)convertView.findViewById(R.id.tv_chatcontent_left);
//                //view_left.setTag(viewHoler_left);
//               // convertView = view_left;
//
//            }else if(flag==ListData.SEND){
//               // view_left.setVisibility(View.GONE);
//                convertView = mInflater.inflate(R.layout.chatting_item_msg_text_right,null);
//                viewholer.tx = (TextView)convertView.findViewById(R.id.tv_chatcontent_right);
//              //  view_right.setTag(viewHoler_right);
//             //  convertView = view_right;
//            }
//
//
//
//
//        }else{
//
//
//            viewholer = (viewHoler) convertView.getTag();
//        }
////        Message message = handler.obtainMessage();
////        message.obj = s;
////        message.what = 1 ;
////        handler.sendMessage(message);
//        if(viewholer!=null) viewholer.tx.setText(s);
//
//        return convertView;
        TextView txx = null;
        TextView name = null ;
        TextView time = null ;
        if(flag==ListData.GET){
            view = mInflater.inflate(R.layout.chatting_item_msg_text_left,null);
            txx = (TextView) view.findViewById(R.id.tv_chatcontent_left);

        }
        if(flag==ListData.SEND){
            view = mInflater.inflate(R.layout.chatting_item_msg_text_right,null);
             txx = (TextView) view.findViewById(R.id.tv_chatcontent_right);
        }
        name = (TextView) view.findViewById(R.id.tv_username);
        time = (TextView) view.findViewById(R.id.tv_sendtime);
        txx.setText(mlist.get(position).getContent());
        name.setText(mlist.get(position).getName());
        time.setText(mlist.get(position).getData());
        return view;
    }


}
