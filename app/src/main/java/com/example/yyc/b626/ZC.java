package com.example.yyc.b626;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ZC extends AppCompatActivity {

    Button ZC,FH;
    EditText User,PassWd,PassWd2;
    FF FF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zc);
        getSupportActionBar().setTitle("注 册");

        ZC = findViewById(R.id.ZC);
        FH = findViewById(R.id.FH);
        User = findViewById(R.id.User);
        PassWd = findViewById(R.id.PassWd);
        PassWd2 = findViewById(R.id.PassWd2);
        FF = new FF(ZC.this);

        ZC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = User.getText().toString();
                String passwd = PassWd.getText().toString();
                String passwd2 = PassWd2.getText().toString();

                if (TextUtils.isEmpty(user)){
                    Toast.makeText(ZC.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwd)){
                    Toast.makeText(ZC.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.equals(passwd,passwd2)){
                    if (passwd.length() < 6){
                        Toast.makeText(ZC.this,"密码长度不足六位数",Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        boolean b = FF.quer(user);
                        if (b){
                            Toast.makeText(ZC.this,"用户已存在",Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            FF.insert(user,passwd);
                            Toast.makeText(ZC.this,"注册成功",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ZC.this,Login.class));
                        }
                    }
                }else {
                    Toast.makeText(ZC.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        FH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ZC.this,Login.class));
            }
        });
    }
}
