package com.split.osiris.splitchores.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.split.osiris.splitchores.R;
import com.split.osiris.splitchores.data.model.Group;
import com.split.osiris.splitchores.data.model.GroupwithTasks;
import com.split.osiris.splitchores.data.model.Task;
import com.split.osiris.splitchores.databinding.GroupItemsBinding;
import com.split.osiris.splitchores.databinding.TaskItemsBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TasksRecycleViewAdapter extends RecyclerView.Adapter<TasksRecycleViewAdapter.ViewHolder>{

    private List<Task> tasks;
    @Inject
    public TasksRecycleViewAdapter() {
        tasks = new ArrayList<>();
    }

    @Override
    public TasksRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
       TaskItemsBinding binding = DataBindingUtil.inflate(inflater, R.layout.task_items,
                parent, false);
        return new TasksRecycleViewAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(TasksRecycleViewAdapter.ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.bind(task);
    }

    public void setTasks(@NonNull List<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TaskItemsBinding mBinding;

        ViewHolder(TaskItemsBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(@NonNull final Task task) {
            mBinding.setTask(task);
            mBinding.executePendingBindings();
        }
    }
}

