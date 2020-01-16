package com.jsj.frc.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Context.NOTIFICATION_SERVICE;

public class Fragment_mine extends Fragment {
    private ImageView imageView;
    private TextView textView1;
    private TextView textView2;
    private Button button;
    String id = "channel_001";
    String name = "name";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mine,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView1=(TextView)getActivity().findViewById(R.id.textView10);
        textView2=(TextView)getActivity().findViewById(R.id.textView11);
        imageView=(ImageView)getActivity().findViewById(R.id.imageView4);
        String str = (String)getArguments().get("name");
        String str1 = (String)getArguments().get("pass");
        textView1.setText(str);
        textView2.setText(str1);
        //notification
        NotificationManager notificationManager = (NotificationManager)
                getActivity().getSystemService(NOTIFICATION_SERVICE);

        Notification notification = null;
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getContext())
                    .setContentTitle("登录")
                    .setContentText("欢迎您回来")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setOngoing(true)
                    .setChannelId(id);//无效
            notification = notificationBuilder.build();
        notificationManager.notify(1,notification);
    }
}
