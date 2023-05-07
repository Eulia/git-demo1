package com.example.clf318.ui.video;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.clf318.bean.NewsBean;
import com.example.clf318.bean.VideoBean;
import com.example.clf318.utils.NetUtils;
import com.github.leonardoxh.livedatacalladapter.Resource;

import java.util.List;

public class VideoViewModel extends ViewModel {

    public LiveData<List<VideoBean>> getVideoList() {

        return Transformations.map(NetUtils.get().getVideoList(), Resource::getResource);
    }
}