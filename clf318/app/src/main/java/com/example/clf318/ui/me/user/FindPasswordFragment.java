package com.example.clf318.ui.me.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.clf318.R;
import com.example.clf318.base.BaseFragment2;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;


public class FindPasswordFragment extends BaseFragment2 {

    private EditText editText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_find_password, container, false);
        editText=root.findViewById(R.id.editText3);
        Button button=root.findViewById(R.id.button);
        //设置监听
        button.setOnClickListener(this::resetPasswordByEmail);
        return root;
    }
    /**
     * 邮箱重置密码
     */
    private void resetPasswordByEmail(View view) {
        String email = editText.getText().toString();
        if (TextUtils.isEmpty(email)){
            editText.setError("Email cannot be empty");
            return;
        }

        BmobUser.resetPasswordByEmail(email, new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Snackbar.make(view, "Password reset request successful, please go to" + email + "E-mail for password reset operation", Snackbar.LENGTH_LONG).show();
                    Navigation.findNavController(view).navigateUp();
                } else {
                    Log.e("BMOB", e.toString());
                    Snackbar.make(view, Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}