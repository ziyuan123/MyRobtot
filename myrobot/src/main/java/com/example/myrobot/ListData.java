package com.example.myrobot;

import java.util.List;

/**
 * Created by sean-duan on 2017/7/12.
 */

public class ListData {
    //存载数据
    private String content;
    //设置常量判断消息属性
    private String data ;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name ;

    public static final int SEND = 1 ;
    public static final int GET = 2;
    private int flag =0;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    //添加方法读取属性
    public ListData(String content,int x){
        setContent(content);
        this.flag = x ;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

}
