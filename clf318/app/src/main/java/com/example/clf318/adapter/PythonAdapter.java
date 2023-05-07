package com.example.clf318.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.clf318.R;
import com.example.clf318.bean.PythonBean;

import java.util.List;

public class PythonAdapter extends BaseQuickAdapter<PythonBean, BaseViewHolder> {

    public PythonAdapter(@NonNull List<PythonBean> data) {
        super(R.layout.item_python,data);
    }

    //数据绑定
    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, PythonBean pythonBean) {
        baseViewHolder.setText(R.id.textView,pythonBean.getAddress());
        baseViewHolder.setText(R.id.textView2,pythonBean.getContent());
        baseViewHolder.setText(R.id.textView3,pythonBean.getOpen_class());
    }
}
