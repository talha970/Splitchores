package com.split.osiris.splitchores.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.split.osiris.splitchores.data.model.Group;
import com.split.osiris.splitchores.data.model.GroupwithTasks;
import com.split.osiris.splitchores.databinding.GroupItemsBinding;
import com.split.osiris.splitchores.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GroupsRecyclerViewAdapter extends RecyclerView.Adapter<GroupsRecyclerViewAdapter.ViewHolder>{

        private List<GroupwithTasks> groups;
        private OnGroupClickListener listener;
    @Inject
    public GroupsRecyclerViewAdapter() {
        groups = new ArrayList<>();
        }

        @Override
        public GroupsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            GroupItemsBinding binding = DataBindingUtil.inflate(inflater, R.layout.group_items,
                    parent, false);
            return new GroupsRecyclerViewAdapter.ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(GroupsRecyclerViewAdapter.ViewHolder holder, int position) {
            GroupwithTasks group = groups.get(position);
            holder.bind(group,listener);
        }

        public void setGroups(@NonNull List<GroupwithTasks> groups) {
            this.groups.clear();
            this.groups.addAll(groups);
            notifyDataSetChanged();
        }

    public void setListener(OnGroupClickListener listener) {
        this.listener = listener;
    }

    @Override
        public int getItemCount() {
            return groups.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {

            private final GroupItemsBinding mBinding;

            ViewHolder(GroupItemsBinding binding) {
                super(binding.getRoot());
                mBinding = binding;
            }

            void bind(@NonNull final GroupwithTasks group, OnGroupClickListener listener) {
                mBinding.setGroup(group);
                mBinding.setListener(listener);
                mBinding.executePendingBindings();
            }
        }

        public interface OnGroupClickListener{
            void onGroupClicked(GroupwithTasks groupwithTasks);
        }
    }

