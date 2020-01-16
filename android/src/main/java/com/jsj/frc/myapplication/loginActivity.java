package com.jsj.frc.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class loginActivity extends AppCompatActivity {

    public static final String SP_INFO = "mysharefile";
    public static final String USERID = "userId";
    public static final String USERPASS = "userPass";
    TextView textView;
    EditText etUsername;
    EditText etPassword;
    CheckBox cb;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Toolbar myTool = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myTool);
        cb = (CheckBox) findViewById(R.id.cbremember);
        etUsername = (EditText) this.findViewById(R.id.etUsername);
        etPassword = (EditText) this.findViewById(R.id.etPassword);
        textView=(TextView)this.findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forget=new Intent(loginActivity.this,ForgetActivity.class);
                startActivity(forget);
            }
        });
        checkIfRemember();
        Button btreg = (Button) findViewById(R.id.btreg);
        Button btlogin = (Button) findViewById(R.id.btlogin);
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUsername.getText().toString().trim();
                String passWord = etPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(passWord)) {
                    ArrayList<User> data = getAllData();
                    boolean match = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (userName.equals(user.getUsername()) && passWord.equals(user.getPassword())) {
                            match = true;
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        Toast.makeText(loginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        /*
                        Bundle bundle = new Bundle();
                        bundle.putString("name",userName);
                        fragment_main.setArguments(bundle);
                        */
                        Intent intent = new Intent(loginActivity.this,MainActivity.class);
                        intent.putExtra("name",userName);
                        intent.putExtra("pass",passWord);
                        startActivity(intent);
                        finish();//销毁此Activity
                    } else {
                        Toast.makeText(loginActivity.this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(loginActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(loginActivity.this, registerActivity.class);
                startActivity(it);
            }
        });
        myTool.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add_item:
                        Toast.makeText(loginActivity.this, "add sth", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.about_me:
                        Intent it_1 = new Intent(loginActivity.this, MeActivity.class);
                        startActivity(it_1);
                        Toast.makeText(loginActivity.this, "talk about me", Toast.LENGTH_SHORT).show();
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


    public ArrayList<User> getAllData() {

        ArrayList<User> list = new ArrayList<User>();
        DBhelper dbHelper = new DBhelper(loginActivity.this, "user", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("user", null, null, null, null, null, "username DESC");
        while (cursor.moveToNext()) {
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(username, password));
        }
        return list;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (cb.isChecked()) {
            String uid = this.etUsername.getText().toString().trim();
            String pwd = this.etPassword.getText().toString().trim();
            rememberMe(uid, pwd);
        }
    }

    private void rememberMe(String uid, String pwd) {
        SharedPreferences sp = getSharedPreferences(SP_INFO, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USERID, uid);
        editor.putString(USERPASS, pwd);
        editor.commit();
    }

    private void checkIfRemember() {
        SharedPreferences sp = getSharedPreferences(SP_INFO, MODE_PRIVATE);
        String uid = sp.getString(USERID, null);
        String pwd = sp.getString(USERPASS, null);
        if (uid != null && pwd != null) {
            etUsername.setText(uid);
            etPassword.setText(pwd);
            cb.setChecked(true);

        }
    }
}