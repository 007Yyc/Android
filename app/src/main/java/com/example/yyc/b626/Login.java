package com.example.yyc.b626;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

public class Login extends AppCompatActivity {

    Button DL, ZC;
    EditText User, PassWd, IPDZ;
    SharedPreferences JUser, JPassWd, JIPDZ;
    FF FF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("登 录");

        DL = findViewById(R.id.DL);
        ZC = findViewById(R.id.ZC);
        User = findViewById(R.id.User);
        PassWd = findViewById(R.id.PassWd);
        IPDZ = findViewById(R.id.IPDZ);
        FF = new FF(Login.this);
        JUser = getPreferences(MODE_PRIVATE);
        JPassWd = getPreferences(MODE_PRIVATE);
        JIPDZ = getPreferences(MODE_PRIVATE);

        if (User.equals("")) {
            User.setText("");
        } else {
            User.setText(JUser.getString("User", ""));
        }
        if (PassWd.equals("")) {
            PassWd.setText("");
        } else {
            PassWd.setText(JPassWd.getString("PassWd", ""));
        }
        if (IPDZ.equals("")) {
            IPDZ.setText("");
        } else {
            IPDZ.setText(JIPDZ.getString("IPDZ", ""));
        }


        DL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = User.getText().toString();
                String passwd = PassWd.getText().toString();
                String ipdz = IPDZ.getText().toString();

                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(Login.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwd)) {
                    Toast.makeText(Login.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ipdz)) {
                    Toast.makeText(Login.this, "IP地址不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean ss = FF.query(user, passwd);
                if (ss) {
                    //设置用户名、密码、IP地址
                    ControlUtils.setUser("bizideal", "123456", ipdz);
                    //创建链接
                    SocketClient.getInstance().creatConnect();
                    //获取链接状态、登录服务器
                    SocketClient.getInstance().login(new LoginCallback() {
                        @Override
                        public void onEvent(final String s) {
                            //新建可操作UI的线程
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (true) {
//                                        if (s.equals(ConstantUtil.Success)){
                                        startActivity(new Intent(Login.this, KZ.class));
                                        Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
                                        SharedPreferences.Editor et = JUser.edit();
                                        SharedPreferences.Editor ett = JPassWd.edit();
                                        SharedPreferences.Editor ettt = JIPDZ.edit();

                                        et.putString("User", User.getText().toString());
                                        et.putBoolean("istrue", true);

                                        ett.putString("PassWd", PassWd.getText().toString());
                                        ett.putBoolean("istruer", true);

                                        ettt.putString("IPDZ", IPDZ.getText().toString());
                                        ettt.putBoolean("istruere", true);

                                        et.commit();
                                        ett.commit();
                                        ettt.commit();
                                    } else {
                                        Toast.makeText(Login.this, "IP地址错误", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            });
                        }
                    });
                } else {
                    Toast.makeText(Login.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    return;
                }

            }

        });
        ZC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ZC.class));
            }
        });
    }
}