package com.example.clf318.ui.video;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clf318.R;
import com.example.clf318.adapter.VideoAdapter;
import com.example.clf318.bean.NewsBean;
import com.example.clf318.bean.VideoBean;
import com.example.clf318.databinding.FragmentVideoBinding;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {

    private FragmentVideoBinding binding;

    private VideoViewModel videoViewModel;
    private VideoAdapter videoAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         videoViewModel =
                new ViewModelProvider(this).get(VideoViewModel.class);

        binding = FragmentVideoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RefreshLayout refreshLayout = (RefreshLayout)root.findViewById(R.id.refreshLayout);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                getVideoList();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                Toast.makeText(getActivity(),"没有更多数据",Toast.LENGTH_SHORT).show();
            }
        });
        getVideoList();
        RecyclerView recyclerView=root.findViewById(R.id.recyclerView);
        videoAdapter = new VideoAdapter(null);
        recyclerView.setAdapter(videoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        videoAdapter.setOnItemClickListener((adapter, view, position) -> {
            //添加参数
            Bundle bundle=new Bundle();
            VideoBean videoBean=videoAdapter.getData().get(position);
            bundle.putString("image",videoBean.getImg());
            bundle.putString("name",videoBean.getName());
            bundle.putString("intro",videoBean.getIntro());
            List<String> list=new ArrayList<>();
            for (VideoBean.VideoDetailListBean videoDetailListBean:
                    videoBean.getVideoDetailList() ) {
                list.add(videoDetailListBean.getVideo_name());
            }
            bundle.putStringArray("list",list.toArray(new String[0]));
            Navigation.findNavController(root).navigate(
                    R.id.action_navigation_video_to_videoDetailFragment,bundle
            );
        });
        return root;
    }

    private void getVideoList() {
        videoViewModel.getVideoList().observe(getViewLifecycleOwner(), videoBeans ->  {
           videoAdapter.setList(videoBeans);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}