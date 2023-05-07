package com.example.clf318.adapter;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.clf318.R;
import com.example.clf318.bean.NewsBean;
import com.example.clf318.utils.NetUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HomeAdapter extends BaseMultiItemQuickAdapter<NewsBean, BaseViewHolder> {

    public HomeAdapter(@Nullable List<NewsBean> data) {
        super(data);
        addItemType(1, R.layout.item_home1);
        addItemType(2, R.layout.item_home2);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, NewsBean newsBean) {
        switch (baseViewHolder.getItemViewType()){
            case 1:
                baseViewHolder.setText(R.id.textView,newsBean.getNewsName());
                baseViewHolder.setText(R.id.textView2,newsBean.getNewsTypeName());
                Glide.with(getContext()).load(NetUtils.BASE_URL+newsBean.getImg1())
                        .into((ImageView)baseViewHolder.getView(R.id.imageView));
                break;
            case 2:
                baseViewHolder.setText(R.id.textView,newsBean.getNewsName());
                baseViewHolder.setText(R.id.textView2,newsBean.getNewsTypeName());
                Glide.with(getContext()).load(NetUtils.BASE_URL+newsBean.getImg1())
                        .into((ImageView)baseViewHolder.getView(R.id.imageView));
                Glide.with(getContext()).load(NetUtils.BASE_URL+newsBean.getImg2())
                        .into((ImageView)baseViewHolder.getView(R.id.imageView2));
                Glide.with(getContext()).load(NetUtils.BASE_URL+newsBean.getImg3())
                        .into((ImageView)baseViewHolder.getView(R.id.imageView3));
                break;
        }
    }
}
