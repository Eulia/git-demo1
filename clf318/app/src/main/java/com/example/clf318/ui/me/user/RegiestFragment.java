package com.example.clf318.ui.me.user;

import android.os.Bundle;

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.navigation.Navigation;

import com.example.clf318.R;
import com.example.clf318.base.BaseFragment2;
import com.example.clf318.bean.User;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class RegiestFragment extends BaseFragment2 {

    private EditText editText;
    private EditText editText2;
    private EditText editText3;

    private EditText editText4;

    private CheckBox checkBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_regiest, container, false);
        editText=root.findViewById(R.id.editText);
        editText2=root.findViewById(R.id.editText2);
        editText3=root.findViewById(R.id.editText3);
        editText4=root.findViewById(R.id.editText4);

        checkBox=root.findViewById(R.id.checkBox1);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    //如果选中，显示密码
                    editText2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    editText3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //否则隐藏密码
                    editText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    editText3.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }

            }
        });

        Button button=root.findViewById(R.id.button);
        //设置监听
        button.setOnClickListener(this::signUp);
        return root;
    }
    /**
     * 账号密码注册
     */
    private void signUp(final View view) {
        //获取数据
        String name = editText.getText().toString();
        String password = editText2.getText().toString();
        String password2=editText4.getText().toString();
        String email = editText3.getText().toString();




        if (password!=password2)
        {
            editText.setError("The 2 passwords are different");
            return;
        }

        if (TextUtils.isEmpty(name)){
            editText.setError("Account can't be empty");
            return;
        }
        if (TextUtils.isEmpty(password)){
            editText2.setError("Password can't be empty");
            return;
        }
        if (TextUtils.isEmpty(email)){
            editText3.setError("Email can't be empty");
            return;
        }
        final User user = new User();
        //设置值
        user.setUsername(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setNickName(name);
        user.setSex(true);
        user.setInfo("this guy is very lazy, leaving nothing");
        //逻辑判断
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    Snackbar.make(view, "Registration successful", Snackbar.LENGTH_LONG).show();
                    Navigation.findNavController(view).navigateUp();
                } else {
                    Snackbar.make(view, "Not yet failed：" + e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}