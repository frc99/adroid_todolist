package com.jsj.frc.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fragment_discovery extends Fragment {


    private Context mContext = getActivity();
    private GridView grid_choice;
    private BaseAdapter gridAdapter = null;
    private ArrayList<Icon> mData = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        grid_choice = (GridView) getActivity().findViewById(R.id.grid_choice);

        mData = new ArrayList<Icon>();
        mData.add(new Icon(R.drawable.noodles, "信息管理"));
        mData.add(new Icon(R.drawable.cheese, "计算机"));
        mData.add(new Icon(R.drawable.tomato, "软件工程"));
        mData.add(new Icon(R.drawable.donute, "电子商务"));
        mData.add(new Icon(R.drawable.noodel, "其他"));
        mData.add(new Icon(R.drawable.milk, "其他"));


        gridAdapter = new gridAdapter<Icon>(mData, R.layout.grid_item) {
            @Override
            public void bindView(ViewHolder holder, Icon obj) {
                holder.setImageResource(R.id.img_icon, obj.getiId());
                holder.setText(R.id.txt_icon, obj.getiName());
            }
        };

        grid_choice.setAdapter(gridAdapter);

        grid_choice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(getActivity(),CsListActivity.class);
                startActivity(it);
            }
        });

    }
}

