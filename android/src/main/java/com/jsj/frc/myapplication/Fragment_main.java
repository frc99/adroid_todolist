package com.jsj.frc.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fragment_main extends Fragment {
    @Nullable
    private SwipeMenuRecyclerView recyclerView;
    private ceAdapter mAdapter;
    private List<String> list=new ArrayList();
    private LinearLayoutManager layoutManager;
    String addTask;
    private EditText editText;
    private Button button;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        editText=getActivity().findViewById(R.id.etNewItem);
        button=getActivity().findViewById(R.id.btnAddItem);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask=editText.getText().toString().trim();
                list.add(1,addTask);
                mAdapter.notifyItemInserted(1);
                mAdapter.notifyItemRangeChanged(1,list.size()-1);
                mAdapter.notifyDataSetChanged();

            }
        });
        addData();
        loadCH();
        /*滑动清除
        recyclerView.setItemViewSwipeEnabled(true); // 侧滑删除，默认关闭。

        OnItemMoveListener mItemMoveListener = new OnItemMoveListener() {
            @Override
            public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
                // 此方法在Item拖拽交换位置时被调用。
                // 第一个参数是要交换为之的Item，第二个是目标位置的Item。

                // 交换数据，并更新adapter。
                int fromPosition = srcHolder.getAdapterPosition();
                int toPosition = targetHolder.getAdapterPosition();
                Collections.swap(list, fromPosition, toPosition);
                mAdapter.notifyItemMoved(fromPosition, toPosition);

                // 返回true，表示数据交换成功，ItemView可以交换位置。
                return true;
            }

            @Override
            public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
                // 此方法在Item在侧滑删除时被调用。

                // 从数据源移除该Item对应的数据，并刷新Adapter。
                int position = srcHolder.getAdapterPosition();
                list.remove(position);
                mAdapter.notifyItemRemoved(position);
            }
        };
        recyclerView.setOnItemMoveListener(mItemMoveListener);// 监听拖拽，更新UI。
        */
        //list = new ArrayList();
/*
        list.add("item1");

        list.add("item2");

        list.add("item3");
        list.add("item4");
        list.add("item5");
*/
        /*
        recyclerView = (SwipeRecyclerView) getActivity().findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setLongPressDragEnabled(true);// 开启长按拖拽
        recyclerView.setItemViewSwipeEnabled(true);// 开启滑动删除。
        myAdapter = new MyAdapter(list);
        recyclerView.setAdapter(myAdapter);*/
        /*
        recyclerView.setOnItemMoveListener(onItemMoveListener);// 监听拖拽和侧滑删除，更新UI和数据。
        */
       /* recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycleView);
//设置RecyclerView管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//初始化适配器
        myAdapter = new MyAdapter(list);
//设置添加或删除item时的动画，这里使用默认动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//设置适配器
        recyclerView.setAdapter(myAdapter);*/
    }
//侧滑
    private void loadCH() {
        recyclerView = getActivity().findViewById(R.id.recycleView);
        mAdapter = new ceAdapter(getActivity(),list);
        //设置 recyclerView 的 布局管理器
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        // 创建菜单：
        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {

                int width = getResources().getDimensionPixelOffset(R.dimen.dp_100);
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                SwipeMenuItem addItem = new SwipeMenuItem(getActivity())
                        .setBackground(R.color.colorAccent)
                        .setImage(R.drawable.ic_action_delete)
                        .setText("删除")
                        .setTextColor(getResources().getColor(R.color.white))
                        .setWidth(width)
                        .setHeight(height);
                rightMenu.addMenuItem(addItem); // 添加菜单到右侧。
            }
        };
// 设置监听器。
        recyclerView.setSwipeMenuCreator(mSwipeMenuCreator);
        SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
            public void onItemClick(SwipeMenuBridge menuBridge) {
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection();
                int adapterPosition = menuBridge.getAdapterPosition(); // Item的position。
                int menuPosition = menuBridge.getPosition(); // 菜单的Position。
                list.remove(adapterPosition) ;
                mAdapter.notifyDataSetChanged();
            }
        };
        // 菜单点击监听。
        recyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);

        // 添加适配器
        recyclerView.setAdapter(mAdapter);

    }
    private void addData() {
        for (int i = 0; i <10; i++) {
            list.add("task：" + i);
        }

    }


}


