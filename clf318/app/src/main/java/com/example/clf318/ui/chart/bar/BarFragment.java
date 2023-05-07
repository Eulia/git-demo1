package com.example.clf318.ui.chart.bar;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clf318.R;
import com.example.clf318.base.BaseFragment2;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class BarFragment extends BaseFragment2 {

    private BarViewModel mViewModel;

    public static BarFragment newInstance() {
        return new BarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_bar, container, false);
        BarChart chart=root.findViewById(R.id.barChart);
        BarViewModel barViewModel=new ViewModelProvider(this).get(BarViewModel.class);
        barViewModel.getBarList().observe(getViewLifecycleOwner(),barBeans -> {
            List<BarEntry> entries1=new ArrayList<>();
            List<BarEntry> entries2=new ArrayList<>();
            for (int i = 0; i <barBeans.size() ; i++) {
                entries1.add(new BarEntry(i,barBeans.get(i).getLineBean1().getSalaries()));
                entries2.add(new BarEntry(i,barBeans.get(i).getLineBean2().getSalaries()));
            }
            //数据集
            BarDataSet dataSet1=new BarDataSet(entries1,"Java工资");
            dataSet1.setColor(R.color.hotpink);
            dataSet1.setValueTextColor(Color.RED); // styling, ...
            dataSet1.setValueTextSize(12f);
            BarDataSet dataSet2=new BarDataSet(entries2,"PhP工资");
            dataSet2.setColor(R.color.aquamarine);
            dataSet2.setValueTextColor(Color.GREEN); // styling, ...
            dataSet2.setValueTextSize(12f);

            BarData barData = new BarData(dataSet1,dataSet2);
            barData.setBarWidth(0.45f);
            chart.setData(barData);
            //设置两个柱之间的间隔
            chart.groupBars(-0.5f,0.04f,0.03f);
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
                    return barBeans.get((int)value).getLineBean1().getYear();
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
            chart.animateY(3000);

        });
        return root;
    }


}