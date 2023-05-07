package com.example.clf318.base;

import android.content.Context;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.clf318.MainActivity;

public class BaseFragment2 extends Fragment implements OnFragmentKeyDownListener {
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity=(MainActivity) getActivity();
        activity.setOnFragmentKeyDownListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
