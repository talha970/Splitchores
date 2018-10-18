package com.split.osiris.splitchores.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.split.osiris.splitchores.MainApplication;
import com.split.osiris.splitchores.R;
import com.split.osiris.splitchores.adapters.GroupsRecyclerViewAdapter;
import com.split.osiris.splitchores.data.model.GroupwithTasks;
import com.split.osiris.splitchores.databinding.FragmentMainBinding;
import com.split.osiris.splitchores.di.utils.DaggerUtil;

import java.util.List;

import javax.inject.Inject;


public class MainFragment extends Fragment {
    @Inject
    MainViewModel mainViewModel;
    FragmentMainBinding binding;
    @Inject
    GroupsRecyclerViewAdapter adapter;

    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MainApplication.getAppComponent().inject(this);
        DaggerUtil.getMainSubComponent(getActivity()).inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,
                container, false);
        binding.setFragment(this);
        adapter = new GroupsRecyclerViewAdapter();
        adapter.setListener((GroupsRecyclerViewAdapter.OnGroupClickListener) getActivity());
        binding.rvGroup.setAdapter(adapter);
        binding.setViewModel(mainViewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();

        mainViewModel.getGroupMutableLiveData().observe(this, groupwithTasks -> adapter.setGroups(groupwithTasks));

    }
}
