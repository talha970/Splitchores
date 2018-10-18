package com.split.osiris.splitchores.ui.tasks;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.split.osiris.splitchores.Main2Activity;
import com.split.osiris.splitchores.MainApplication;
import com.split.osiris.splitchores.R;

import com.split.osiris.splitchores.adapters.TasksRecycleViewAdapter;
import com.split.osiris.splitchores.databinding.TasksLayoutBinding;
import com.split.osiris.splitchores.di.utils.DaggerUtil;
import com.split.osiris.splitchores.ui.main.MainViewModel;


import javax.inject.Inject;

public class TasksFragment extends Fragment {
    private TasksLayoutBinding binding;
    @Inject
    MainViewModel mainViewModel;
    @Inject
    TasksRecycleViewAdapter adapter;

    String groupId;

    public TasksFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerUtil.getMainSubComponent(getActivity()).inject(this);
       // mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        Bundle bundle = getArguments();
        groupId = bundle.getString(Main2Activity.GROUP_BUNDLE_KEY);
        mainViewModel.getTasks(groupId).observe(getActivity(), tasks -> adapter.setTasks(tasks));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tasks_layout,
                container, false);
        binding.setFragment(this);
        adapter = new TasksRecycleViewAdapter();
        binding.rvTasks.setAdapter(adapter);
        binding.setViewModel(mainViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

}
