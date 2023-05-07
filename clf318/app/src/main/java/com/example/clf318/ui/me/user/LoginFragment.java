package com.example.clf318.ui.me.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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
import android.widget.TextView;

import com.example.clf318.R;
import com.example.clf318.base.BaseFragment2;
import com.example.clf318.bean.User;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class LoginFragment extends BaseFragment2 {

    private EditText editText;
    private EditText editText2;

    private CheckBox checkBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View root=inflater.inflate(R.layout.fragment_login, container, false);
        editText=root.findViewById(R.id.editText);
        editText2=root.findViewById(R.id.editText2);
        checkBox=root.findViewById(R.id.checkBox1);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    //如果选中，显示密码
                    editText2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //否则隐藏密码
                    editText2.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }

            }
        });



        TextView textView=root.findViewById(R.id.textView);
        textView.setOnClickListener(view -> Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_regiestFragment));
        TextView textView2=root.findViewById(R.id.textView2);
        textView2.setOnClickListener(view -> Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_findPasswordFragment));
        Button button=root.findViewById(R.id.button);
        //设置监听
        button.setOnClickListener(this::login);
        return root;
    }
    /**
     * 账号密码登录
     */
    private void login(final View view) {
        //获取数据
        String name = editText.getText().toString();
        String password = editText2.getText().toString();
        if (TextUtils.isEmpty(name)){
            editText.setError("Account can't be empty");
            return;
        }
        if (TextUtils.isEmpty(password)){
            editText2.setError("Passwords can't be empty");
            return;
        }
        final User user = new User();

        user.setUsername(name);

        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User bmobUser, BmobException e) {
                if (e == null) {
                    User user = BmobUser.getCurrentUser(User.class);
                    Snackbar.make(view, "Login successful:" + user.getUsername(), Snackbar.LENGTH_LONG).show();
                    //登录成功导航会上级
                    Navigation.findNavController(view).navigateUp();
                } else {
                    Snackbar.make(view, "Login failed:" + e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}