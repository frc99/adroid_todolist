package com.jsj.frc.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CsListActivity extends AppCompatActivity {
    private RecyclerView cv_recycyclerview;
    private Toolbar myTool;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cs_recycler);
        myTool = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myTool);
        myTool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        myTool.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add_item:
                        Toast.makeText(CsListActivity.this, "add sth", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.about_me:
                        Intent it_1 = new Intent(CsListActivity.this, MeActivity.class);
                        startActivity(it_1);
                        Toast.makeText(CsListActivity.this, "talk about me", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                }
                return true;
            }
        });
        cv_recycyclerview = findViewById(R.id.cv_recyclerview);
        //设置LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cv_recycyclerview.setLayoutManager(linearLayoutManager);
        //绑定adapter
        mAdapter myAdapter = new mAdapter(this);
        cv_recycyclerview.setAdapter(myAdapter);
        ItemTouchHelper.Callback callback = new myItemTouchHelperCallBack(myAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(cv_recycyclerview);

        /*
        cardView=(CardView) getActivity().findViewById(R.id.cardView1);
        imageView=(ImageView)getActivity().findViewById(R.id.imageView1);
        cardView.setRadius(8);//设置图片圆角的半径大小
        cardView.setCardElevation(8);//设置阴影部分大小
        cardView.setContentPadding(5,5,5,5);//设置图片距离阴影大小
        */


    }

    //adapter
    class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder> implements ItemTouchHelperAdapter {
        LayoutInflater mInflater;
        List<MData> mList = addData();

        public mAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public mAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.cv_item, parent, false);
            mAdapter.ViewHolder viewHolder = new mAdapter.ViewHolder(view);
            return viewHolder;
        }

        public void onBindViewHolder(mAdapter.ViewHolder holder, int position) {
            holder.mName.setText(mList.get(position).getUserName());
            holder.mDescribe.setText(mList.get(position).getDescription());
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public void onItemMove(int fromPosition, int toPosition) {
            //交换位置
            Collections.swap(mList, fromPosition, toPosition);
            notifyItemMoved(fromPosition, toPosition);
        }

        public void onItemDelete(int position) {
            //移除数据
            mList.remove(position);
            notifyItemRemoved(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView mName;
            private TextView mDescribe;

            public ViewHolder(View itemView) {
                super(itemView);
                mName = itemView.findViewById(R.id.txt_name);
                mDescribe = itemView.findViewById(R.id.txt_describe);
            }
        }
    }
    private class MData {
        String userName;
        String description;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    private List<MData> addData() {
        List<MData> list = new ArrayList();

        MData mData = new MData();
        mData.setUserName("安卓期末大作业");
        mData.setDescription("截止时间：xx月xx日");
        list.add(mData);

        mData = new MData();
        mData.setUserName("物联网实验报告");
        mData.setDescription("截止时间：xx月xx日");
        list.add(mData);

        mData = new MData();
        mData.setUserName("数据库上机考试");
        mData.setDescription("截止时间：xx月xx日");
        list.add(mData);

        mData = new MData();
        mData.setUserName("数据库设计答辩");
        mData.setDescription("截止时间：xx月xx日");
        list.add(mData);

        mData = new MData();
        mData.setUserName("班级会议");
        mData.setDescription("截止时间：xx月xx日");
        list.add(mData);

        mData = new MData();
        mData.setUserName("WEB期末大作业");
        mData.setDescription("截止时间：xx月xx日");
        list.add(mData);

        mData = new MData();
        mData.setUserName("小学期报名");
        mData.setDescription("截止时间：xx月xx日");
        list.add(mData);

        return list;
    }
}
