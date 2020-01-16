package com.jsj.frc.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetActivity extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget);
        Toolbar myTool=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(myTool);
        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        Button btset=(Button)findViewById(R.id.reset);
        btset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DBhelper dbHelper=new DBhelper(ForgetActivity.this,"user",null,1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String userName=etUsername.getText().toString().trim();
                String userPass=etPassword.getText().toString().trim();
                String sql = "update user set password=? where username=?";
                db.execSQL(sql, new String[]{userPass,userName});
                Toast.makeText(ForgetActivity.this, "修改成功", Toast.LENGTH_LONG).show();
            }
                                 });
        myTool.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add_item:
                        Toast.makeText(ForgetActivity.this, "add sth", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.about_me:
                        Intent it_1 = new Intent(ForgetActivity.this, MeActivity.class);
                        startActivity(it_1);
                        Toast.makeText(ForgetActivity.this, "talk about me", Toast.LENGTH_SHORT).show();
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
