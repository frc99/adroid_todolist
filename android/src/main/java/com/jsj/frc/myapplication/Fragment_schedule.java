package com.jsj.frc.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_schedule extends Fragment {
    private TextView txt_8;
    private CalendarView calendarView;
    private SeekBar seekBar;
    private SeekBar seekBar2;
    private SeekBar seekBar3;
    private TextView txt_7;
    private TextView txt_9;
    private Context mContext = getContext();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        calendarView = (CalendarView) getActivity().findViewById(R.id.calendarView);
        seekBar = (SeekBar) getActivity().findViewById(R.id.seekBar);
        bindViews();


    }

    private void bindViews() {
        seekBar = (SeekBar) getActivity().findViewById(R.id.seekBar);
        seekBar2 = (SeekBar) getActivity().findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar) getActivity().findViewById(R.id.seekBar3);
        txt_7 = (TextView) getActivity().findViewById(R.id.txt_7);
        txt_8 = (TextView) getActivity().findViewById(R.id.txt_8);
        txt_9 = (TextView) getActivity().findViewById(R.id.txt_9);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_9.setText("当前进度值:" + progress + "  / 100 ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_8.setText("当前进度值:" + progress + "  / 100 ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_7.setText("当前进度值:" + progress + "  / 100 ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
