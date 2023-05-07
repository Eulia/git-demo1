package com.example.clf318.ui.video.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clf318.R;
import com.example.clf318.adapter.VideoListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VideoListFragment extends Fragment {

    private final VideoDetailFragment videoDetailFragment;
    private List<String> list;
    private Object url0="http://7xjmzj.com1.z0.glb.clouddn.com/20171026175005_JObCxCE2.mp4";
    private Object url1="http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    public VideoListFragment(String[] list, VideoDetailFragment videoDetailFragment) {
        this.list=Arrays.asList(list);
        this.videoDetailFragment=videoDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_video_list, container, false);
         RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
         recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         recyclerView.addItemDecoration(new DividerItemDecoration(root.getContext(),
                 DividerItemDecoration.VERTICAL));
        VideoListAdapter videoListAdapter = new VideoListAdapter(list);
        recyclerView.setAdapter(videoListAdapter);
        videoListAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (position%2==0){
                videoDetailFragment.playNewUrl((String) url0);
            }
            else {
                videoDetailFragment.playNewUrl((String) url1);
            }
        });
        return root;
    }
}