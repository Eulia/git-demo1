package com.example.clf318.utils;



import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory;
import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 拼接
 */

public class NetUtils {
    public static final String BASE_URL="http://10.0.2.2:8080/topline/";
    public static GetRequest get(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //数据变换  retrofit->LiveData
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create()) //add this line to you httpService
                .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(GetRequest.class);
    }
}
