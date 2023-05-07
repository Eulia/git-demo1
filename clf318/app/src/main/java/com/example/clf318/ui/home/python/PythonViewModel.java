package com.example.clf318.ui.home.python;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.clf318.bean.NewsBean;
import com.example.clf318.bean.PythonBean;
import com.example.clf318.utils.NetUtils;
import com.github.leonardoxh.livedatacalladapter.Resource;

import java.util.List;

public class PythonViewModel extends ViewModel {
    public LiveData<List<PythonBean>> getPythonList() {

        return Transformations.map(NetUtils.get().getPythonList(), Resource::getResource);
    }
}