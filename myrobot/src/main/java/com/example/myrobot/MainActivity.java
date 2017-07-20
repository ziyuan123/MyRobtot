package com.example.myrobot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();//初始化所有组件
        Listener();//创建监听器
    }

    private void Listener() {
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Robot_play_activity.class);
                startActivity(intent);
            }
        });
    }

    public void init(){
        btn_ok = (Button) findViewById(R.id.button_1);
    }

}
