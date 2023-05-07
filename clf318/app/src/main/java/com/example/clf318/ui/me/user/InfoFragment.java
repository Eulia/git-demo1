package com.example.clf318.ui.me.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.clf318.R;
import com.example.clf318.base.BaseFragment2;
import com.example.clf318.bean.User;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.BmobUser;


public class InfoFragment extends BaseFragment2 {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_info, container, false);
        TextView tediText=root.findViewById(R.id.textView);
        TextView tediText2=root.findViewById(R.id.textView2);
        TextView tediText3=root.findViewById(R.id.textView3);
        TextView tediText4=root.findViewById(R.id.textView4);
        TextView tediText5=root.findViewById(R.id.textView5);
        if (BmobUser.isLogin()){
            User user=BmobUser.getCurrentUser(User.class);
            tediText.setText(user.getUsername());
            tediText2.setText(user.getNickName());
            tediText3.setText(user.isSex()?"Male":"Female");
            tediText4.setText(user.getEmail());
            tediText5.setText(user.getInfo());
        }
        Button button=root.findViewById(R.id.button);
        //设置监听
        button.setOnClickListener(this::logOut);
        return root;
    }

    /**
     * 账号退出
     * @param view
     */
    private void logOut(View view) {
        BmobUser.logOut();
        Navigation.findNavController(view).navigateUp();
        Snackbar.make(view, "Log out：" , Snackbar.LENGTH_LONG).show();
    }
}