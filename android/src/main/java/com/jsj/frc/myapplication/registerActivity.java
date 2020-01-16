package com.jsj.frc.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registerActivity extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;
    EditText etRePassword;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Toolbar myTool=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(myTool);
        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etRePassword=(EditText)findViewById(R.id.etRepeat);
        Button btreg=(Button)findViewById(R.id.reg);
        btreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhelper dbHelper=new DBhelper(registerActivity.this,"user",null,1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String userName=etUsername.getText().toString().trim();
                String userPass=etPassword.getText().toString().trim();
                String userRePass=etRePassword.getText().toString().trim();
                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userPass)){
                    if (!userPass.equals(userRePass)) {
                        Toast.makeText(registerActivity.this, "两次密码不相同，请重新输入", Toast.LENGTH_LONG).show();
                    } else {
                        String sql = "insert into user(username,password) values(?,?)";
                        db.execSQL(sql, new String[]{userName, userPass});
                        Toast.makeText(registerActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                        Intent sign = new Intent(registerActivity.this, loginActivity.class);
                        startActivity(sign);
                    }
                }
                else{
                    Toast.makeText(registerActivity.this, "用户名或密码不能为空", Toast.LENGTH_LONG).show();
                }
                db.close();
            }
        });
        myTool.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add_item:
                        Toast.makeText(registerActivity.this, "add sth", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.about_me:
                        Intent it_1 = new Intent(registerActivity.this, MeActivity.class);
                        startActivity(it_1);
                        Toast.makeText(registerActivity.this, "talk about me", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                }
                return true;
            }
        });
    myTool.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });
    }
}
