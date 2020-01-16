package com.jsj.frc.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private  Fragment_main fragment_main;
    private Fragment_schedule fragment_schedule;
    private Fragment_discovery fragment_discovery;
    private Fragment_mine fragment_mine;
    private Fragment[] fragments;
    private int lastfragment;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent intent =getIntent();
        String data = intent.getStringExtra("name");
        String pass=intent.getStringExtra("pass");
        //Toolbar
        Toolbar myTool=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(myTool);
        //navigation
       /* BottomNavigationView bottomNavigationView;
        ViewPagerAdapter viewPagerAdapter;
        ViewPager viewPager;
        MenuItem menuItem;
*/

        /*mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //报错
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        */
        initFragment();
        //传值
        Bundle b=new Bundle();
        b.putString("name",data);
        b.putString("pass",pass);
        fragment_mine.setArguments(b);

        myTool.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.add_item:
                        Toast.makeText(MainActivity.this, "Log In", Toast.LENGTH_SHORT).show();
                        Intent it_2=new Intent(MainActivity.this,loginActivity.class);
                        startActivity(it_2);
                        break;
                    case R.id.about_me:
                        Intent it_1=new Intent(MainActivity.this,MeActivity.class);
                        startActivity(it_1);
                        Toast.makeText(MainActivity.this, "talk about me", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                }
                return true;
            }
        });
    }
    private void initFragment()
    {

        fragment_main = new Fragment_main();
        fragment_schedule = new Fragment_schedule();
        fragment_discovery = new Fragment_discovery();
        fragment_mine = new Fragment_mine();
        fragments = new Fragment[]{fragment_main,fragment_schedule,fragment_discovery,fragment_mine};
        lastfragment=0;
        getSupportFragmentManager().beginTransaction().replace(R.id.mainView,fragment_main).show(fragment_main).commit();

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(changeFragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId())
            {
                case R.id.navigation_home:
                {
                    if(lastfragment!=0)
                    {
                        switchFragment(lastfragment,0);
                        lastfragment=0;

                    }

                    return true;
                }
                case R.id.navigation_pen:
                {
                    if(lastfragment!=1)
                    {
                        switchFragment(lastfragment,1);
                        lastfragment=1;

                    }

                    return true;
                }
                case R.id.navigation_discovery:
                {
                    if(lastfragment!=2)
                    {
                        switchFragment(lastfragment,2);
                        lastfragment=2;

                    }

                    return true;
                }
                case R.id.navigation_mine:
                {
                    if(lastfragment!=3)
                    {
                        switchFragment(lastfragment,3);
                        lastfragment=3;

                    }

                    return true;
                }



            }


            return false;
        }
    };
    private void switchFragment(int lastfragment,int index)
    {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if(fragments[index].isAdded()==false)
        {
            transaction.add(R.id.mainView,fragments[index]);


        }
        transaction.show(fragments[index]).commitAllowingStateLoss();


    }

}

