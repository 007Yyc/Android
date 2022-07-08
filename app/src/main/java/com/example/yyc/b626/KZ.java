package com.example.yyc.b626;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

import java.util.Date;

public class KZ extends AppCompatActivity {

    Button FS,SDD,MJ,BJD,K,G,T;
    TextView WD,WD2,SD,SD2,RTHW,RTHW2,GZ,GZ2,DKJ,DKJ2,DKJ3,PM,PM2,CO2,CO22,YW,YW2,QY,QY2,RQ,RQ2,SJ;
    Handler Handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kz);
        getSupportActionBar().setTitle("控 制");

        K = findViewById(R.id.K);
        G = findViewById(R.id.G);
        T = findViewById(R.id.T);
        FS = findViewById(R.id.FS);
        SDD = findViewById(R.id.SDD);
        MJ = findViewById(R.id.MJ);
        BJD = findViewById(R.id.BJD);

        WD = findViewById(R.id.WD);
        WD2 = findViewById(R.id.WD2);
        SD = findViewById(R.id.SD);
        SD2 = findViewById(R.id.SD2);
        RTHW = findViewById(R.id.RTHW);
        RTHW2 = findViewById(R.id.RTHW2);
        GZ = findViewById(R.id.GZ);
        GZ2 = findViewById(R.id.GZ2);
        WD = findViewById(R.id.WD);
        WD = findViewById(R.id.WD);
        DKJ = findViewById(R.id.DKJ);
        DKJ2 = findViewById(R.id.DKJ2);
        DKJ3 = findViewById(R.id.DKJ3);
        PM = findViewById(R.id.PM);
        PM2 = findViewById(R.id.PM2);
        CO2 = findViewById(R.id.CO2);
        CO22 = findViewById(R.id.CO22);
        YW = findViewById(R.id.YW);
        YW2 = findViewById(R.id.YW2);
        QY = findViewById(R.id.QY);
        QY2 = findViewById(R.id.QY2);
        RQ = findViewById(R.id.RQ);
        RQ2 = findViewById(R.id.RQ2);

        //显示时间
        SJ = findViewById(R.id.SJ);
        Handler = new Handler();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Handler.postDelayed(this,1000);
                Date date = new Date();
                String time = date.toLocaleString();
                SJ.setText(time);
            }
        });


        //获取数据
        ControlUtils.getData();
        //获取人脸数据
        ControlUtils.getFaceData();
        //开始从服务器获取数据
        SocketClient.getInstance().getData(new DataCallback() {
            @Override
            public void onResult(Object o) {
                //新建可操作UI的线程
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //获取打卡ID、打卡时间
                        DKJ2.setText(DeviceBean.getName());
                        DKJ3.setText(DeviceBean.getTime());

                        if (DeviceBean.getDevice().size() >0){
                            for (int i = 0; i < DeviceBean.getDevice().size();i++){
                                if (DeviceBean.getDevice().get(i).getBoardId().equals("1")){
                                    if (DeviceBean.getDevice().get(i).getSensorType().equals(ConstantUtil.Temperature)){
                                        WD2.setText(DeviceBean.getDevice().get(i).getValue());
                                    }else {
                                        SD2.setText(DeviceBean.getDevice().get(i).getValue());
                                    }
                                }
                                if (DeviceBean.getDevice().get(i).getBoardId().equals("2")){
                                    GZ2.setText(DeviceBean.getDevice().get(i).getValue());
                                }
                                if (DeviceBean.getDevice().get(i).getBoardId().equals("3")){
                                    YW2.setText(DeviceBean.getDevice().get(i).getValue());
                                }
                                if (DeviceBean.getDevice().get(i).getBoardId().equals("4")){
                                    RQ2.setText(DeviceBean.getDevice().get(i).getValue());
                                }
                                if (DeviceBean.getDevice().get(i).getBoardId().equals("5")){
                                    PM2.setText(DeviceBean.getDevice().get(i).getValue());
                                }
                                if (DeviceBean.getDevice().get(i).getBoardId().equals("6")){
                                    CO22.setText(DeviceBean.getDevice().get(i).getValue());
                                }
                                if (DeviceBean.getDevice().get(i).getBoardId().equals("7")){
                                    QY2.setText(DeviceBean.getDevice().get(i).getValue());
                                }
                                if (DeviceBean.getDevice().get(i).getBoardId().equals("8")){
                                    if (!TextUtils.isEmpty(DeviceBean.getDevice().get(i).getValue()) && !DeviceBean.getDevice().get(i).getValue().equals(ConstantUtil.Close)){
                                        RTHW2.setText("有人");
                                    }else {
                                        RTHW2.setText("没人");
                                    }
                                }
                            }
                        }
                    }
                });
            }
        });

        BJD.setOnClickListener(new View.OnClickListener() {
            int x;
            @Override
            public void onClick(View v) {
                if (x == 0){
                    //控制指令（设备类型，传感器板号ID，cmd指令，通道，开关指令）
                    ControlUtils.control(ConstantUtil.Relay,"11",ConstantUtil.CmdCode_1,ConstantUtil.Channel_ALL,ConstantUtil.Open);
                    x = 1;
                }else {
                    //控制指令（设备类型，传感器板号ID，cmd指令，通道，开关指令）
                    ControlUtils.control(ConstantUtil.Relay,"11",ConstantUtil.CmdCode_1,ConstantUtil.Channel_ALL,ConstantUtil.Close);
                    x = 0;
                }
            }
        });

        MJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //控制指令（设备类型，传感器板号ID，cmd指令，通道，开关指令）
                ControlUtils.control(ConstantUtil.RFID_Door,"14",ConstantUtil.CmdCode_2,ConstantUtil.Channel_1,ConstantUtil.Open);
            }
        });

        SDD.setOnClickListener(new View.OnClickListener() {
            int x;
            @Override
            public void onClick(View v) {
                if (x == 0){
                    //控制指令（设备类型，传感器板号ID，cmd指令，通道，开关指令）
                    ControlUtils.control(ConstantUtil.Relay,"10",ConstantUtil.CmdCode_1,ConstantUtil.Channel_ALL,ConstantUtil.Open);
                    x = 1;
                }else {
                    //控制指令（设备类型，传感器板号ID，cmd指令，通道，开关指令）
                    ControlUtils.control(ConstantUtil.Relay,"10",ConstantUtil.CmdCode_1,ConstantUtil.Channel_ALL,ConstantUtil.Close);
                    x = 0;
                }
            }
        });

        FS.setOnClickListener(new View.OnClickListener() {
            int x;
            @Override
            public void onClick(View v) {
                if (x == 0){
                    //控制指令（设备类型，传感器板号ID，cmd指令，通道，开关指令）
                    ControlUtils.control(ConstantUtil.Relay,"9",ConstantUtil.CmdCode_1,ConstantUtil.Channel_ALL,ConstantUtil.Open);
                    x = 1;
                }else {
                    //控制指令（设备类型，传感器板号ID，cmd指令，通道，开关指令）
                    ControlUtils.control(ConstantUtil.Relay,"9",ConstantUtil.CmdCode_1,ConstantUtil.Channel_ALL,ConstantUtil.Close);
                    x = 0;
                }
            }
        });

        K.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //控制指令（设备类型，传感器板号ID，cmd指令，通道，开关指令）
                ControlUtils.control(ConstantUtil.Relay,"12",ConstantUtil.CmdCode_3,ConstantUtil.Channel_3,ConstantUtil.Channel_3);
            }
        });
        T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //控制指令（设备类型，传感器板号ID，cmd指令，通道，开关指令）
                ControlUtils.control(ConstantUtil.Relay,"12",ConstantUtil.CmdCode_3,ConstantUtil.Channel_3,ConstantUtil.Channel_2);
            }
        });
        G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //控制指令（设备类型，传感器板号ID，cmd指令，通道，开关指令）
                ControlUtils.control(ConstantUtil.Relay,"12",ConstantUtil.CmdCode_3,ConstantUtil.Channel_3,ConstantUtil.Channel_1);
            }
        });

    }
}
