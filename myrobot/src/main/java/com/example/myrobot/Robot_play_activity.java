package com.example.myrobot;

import android.content.Intent;
import android.media.JetPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Robot_play_activity extends AppCompatActivity {
    private OkHttpClient mokHttpClient ;
    private Request request ;
    private List<ListData> listDatas ; //创建集合泛型指向封装好的数据
    private ListView lv ;
    private EditText sendText ;
    private Button sendButton ;
    private String url ;
    private String context_str ;
    private TextAdapter myAdapter ;
    private String[] Welcome_array ;
    private RecyclerView recyclerView ;
    private MyAdapter meadapter;
    private String robot_name ="Coco";
    private String user_name = "我";
    private Button btn_back;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            listDatas.add((ListData)msg.obj);
            myAdapter.notifyDataSetChanged();//进行适配
//            meadapter.notifyDataSetChanged();

             Log.e(""+((ListData)msg.obj).getFlag()+":",((ListData)msg.obj).getContent());
        }
    };
  //  private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot_play_activity);

        initView();
        lisner();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Robot_play_activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    //随机问候
    private String getRandomWelcomeTips(){
        String welcome_tip = null;
        Welcome_array = this.getResources().getStringArray(R.array.Welcome_tip);
        int index = (int) (Math.random()*(Welcome_array.length-1));
        welcome_tip = Welcome_array[index];
        return welcome_tip;
    }
    private void lisner() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   initView();
                context_str = sendText.getText().toString();
                ListData listData = new ListData(context_str,ListData.SEND);
                listData.setData(Robot_play_activity.getDate());
                listData.setName(user_name);
               // sendText.setText("");

                Message message = handler.obtainMessage();
                message.obj = listData;
                handler.sendMessage(message);
                gethttp();
//                listDatas.add(listData);
//                myAdapter.notifyDataSetChanged();//进行适配
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                listDatas.add(listData);
//                                myAdapter.notifyDataSetChanged();
//                            }
//                        });
//                        try {
//                            Thread.sleep(500);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
            }
        });

    }

    public void gethttp(){
        mokHttpClient = new OkHttpClient();
        context_str = sendText.getText().toString();

        String a = context_str.replace(" ","");//把空格去掉
        String str = a.replace("\n","");
        Log.e("lalala",str);
       // url = "http://www.tuling123.com/openapi/api?key=7a121a0031f4403c912a795e0e2dc8c5&info="+sendText.getText().toString().trim();
        url= "http://www.tuling123.com/openapi/api?key=7a121a0031f4403c912a795e0e2dc8c5&info="+str;
        request = new Request.Builder().url(url).build();
        Call call = mokHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("失败啊啊啊啊啊啊啊","重来吧");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String e = response.body().string();
                parseText(e);
                //Log.e("成功了",e);
            }
        });
        sendText.setText("");
    }
    public void parseText(String str){
        try {
            JSONObject jb = new JSONObject(str);
           // Log.e("解析后：",jb.getString("text"));
            ListData listData = new ListData(jb.getString("text"),ListData.GET);
            listData.setData(Robot_play_activity.getDate());
            listData.setName(robot_name);
//            listDatas.add(listData);
//            myAdapter.notifyDataSetChanged();
            Message message = handler.obtainMessage();
            message.obj = listData;
            handler.sendMessage(message);
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            listDatas.add(listData);
//                            myAdapter.notifyDataSetChanged();
//                        }
//                    });
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //初始化List
    private void initView(){
        btn_back = (Button) findViewById(R.id.btn_back);
        lv = (ListView) findViewById(R.id.listview);
        sendText = (EditText) findViewById(R.id.et_sendmessage);
        sendButton = (Button) findViewById(R.id.btn_send);
        listDatas = new ArrayList<ListData>();
        myAdapter = new TextAdapter(listDatas,this);
        lv.setAdapter(myAdapter);
        ListData listData = new ListData(getRandomWelcomeTips(),ListData.GET);
        listData.setName(robot_name);
        listData.setData(getDate());

        listDatas.add(listData);

        lv.setTextFilterEnabled(true);
//        lv.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
//        lv.setStackFromBottom(true);
//        recyclerView = (RecyclerView) findViewById(R.id.listview);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        meadapter = new MyAdapter(listDatas,this);
//        recyclerView.setAdapter(meadapter);
    }
//    @Override
//    public void getDataUrl(String data) {
//        String s = "11";
//        s = s+data;
//        Log.i("待解析的数据是::",s);
//    }
    public static String getDate() {
        Calendar c = Calendar.getInstance();

        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH));
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String mins = String.valueOf(c.get(Calendar.MINUTE));


        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":" + mins);


    return sbBuffer.toString();
    }
}
