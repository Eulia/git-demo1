package com.example.clf318.ui.chart.line;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clf318.R;
import com.example.clf318.base.BaseFragment2;
import com.example.clf318.bean.LineBean;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class LineFragment extends BaseFragment2 {



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_line, container, false);
        LineChart chart=root.findViewById(R.id.LineChart);
        LineViewModel lineViewModel=new ViewModelProvider(this).get(LineViewModel.class);
        lineViewModel.getLineList().observe(getViewLifecycleOwner(), lineBeans -> {
            List<Entry> entries=new ArrayList<>();
            for (int i = 0; i <lineBeans.size() ; i++) {
                entries.add(new Entry(i,lineBeans.get(i).getSalaries()));
            }
            LineDataSet dataSet=new LineDataSet(entries,"工资");
            dataSet.setColor(R.color.turquoise);
            dataSet.setValueTextColor(Color.RED); // styling, ...
            dataSet.setValueTextSize(12f);
            dataSet.setLineWidth(6f);
            LineData lineData = new LineData(dataSet);
            chart.setData(lineData);
            chart.invalidate(); // refresh
            XAxis xAxis = chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setTextColor(Color.BLACK);
            xAxis.setAxisLineColor(Color.BLACK);
            xAxis.setAxisLineWidth(3f);
            xAxis.enableGridDashedLine(10f,10f,0f);
            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return lineBeans.get((int)value).getYear();
                }
            });
            chart.getAxisRight().setEnabled(false);
            YAxis yAxis = chart.getAxisLeft();
            yAxis.setTextColor(Color.BLACK);
            yAxis.setAxisLineColor(Color.BLACK);
            yAxis.setAxisLineWidth(3f);
            yAxis.setAxisMinimum(0f);
            yAxis.setSpaceTop(38.2f);
            yAxis.enableGridDashedLine(10f,10f,0f);
            LimitLine limitLine=new LimitLine(10000f,"厦门市平均工资");
            limitLine.setLineWidth(2f);
            limitLine.setLineColor(Color.MAGENTA);
            yAxis.addLimitLine(limitLine);
            //图例
            Legend l=chart.getLegend();
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            Description description=chart.getDescription();
            description.setText("Java工程师经验与工资对应的情况");
            description.setTextSize(16f);
            description.setTextColor(Color.BLACK);
            description.setTextAlign(Paint.Align.CENTER);
            description.setPosition(540f,100f);
            chart.animateX(5000);

//... and more
        });
        return root;
    }


}