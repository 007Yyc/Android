package com.example.yyc.b626;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import junit.framework.Test;

public class MainActivity extends AppCompatActivity {

    ProgressBar ProgressBar;
    TextView TextView;
    Handler Handler;
    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("加载中ing...");


        ProgressBar = findViewById(R.id.progressBar);
        TextView = findViewById(R.id.textView);

         Handler = new Handler(){
             @Override
             public void handleMessage(Message msg) {
                 super.handleMessage(msg);

                 ProgressBar.setProgress(msg.what);

                 if (msg.what == 10){
                     TextView.setText("正在加载串口");
                 }if(msg.what == 30){
                     TextView.setText("串口配置加载完成");
                 }if (msg.what == 50){
                     TextView.setText("界面配置加载完成");
                 }if (msg.what == 70){
                     TextView.setText("正在初始化界面");
                 }if (msg.what == 90){
                     TextView.setText("初始化界面完成");
                 }if (msg.what == 99){
                     TextView.setText("进入系统中...");
                     startActivity(new Intent(MainActivity.this,Login.class));
                 }
             }
         };

         new Thread(){
             @Override
             public void run() {
                 super.run();
                 while (x++ <= 99){
                     Message message = new Message();
                     message.what = x;
                     Handler.sendMessage(message);
                     try {
                         sleep(30);
                     }catch (InterruptedException e){
                         e.printStackTrace();
                     }
                 }
             }
         }.start();

    }
}
