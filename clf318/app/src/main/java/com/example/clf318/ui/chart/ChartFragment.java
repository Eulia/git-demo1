package com.example.clf318.ui.chart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.clf318.R;
import com.example.clf318.bean.BoomMenuItemBean;
import com.example.clf318.databinding.FragmentChartBinding;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

public class ChartFragment extends Fragment {

    private FragmentChartBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ChartViewModel chartViewModel =
                new ViewModelProvider(this).get(ChartViewModel.class);
        binding = FragmentChartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        BoomMenuButton bmb=root.findViewById(R.id.bmb);
        chartViewModel.getBoomMenuItemList().observe(getViewLifecycleOwner(),boomMenuItemBeans -> {
            for (BoomMenuItemBean boomMenuItemBean:boomMenuItemBeans) {
                TextInsideCircleButton.Builder builder=new TextInsideCircleButton.Builder();
                builder.normalText(boomMenuItemBean.getTitle())
                        .normalImageRes(boomMenuItemBean.getImageId())
                        //动作的设置
                        .listener(index -> {
                            switch (index){
                                case 0:
                                    Navigation.findNavController(root).navigate(
                                            R.id.action_navigation_chart_to_lineFragment);
                                    break;
                                case 1:
                                    Navigation.findNavController(root).navigate(
                                            R.id.action_navigation_chart_to_barFragment);
                                    break;
                                case 2:
                                    Navigation.findNavController(root).navigate(
                                            R.id.action_navigation_chart_to_pieFragment);
                                    break;
                            }
                        });
                bmb.addBuilder(builder);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}