package com.split.osiris.splitchores.utils;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.databinding.BindingAdapter;
import android.databinding.adapters.Converters;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.split.osiris.splitchores.adapters.GroupsRecyclerViewAdapter;
import com.split.osiris.splitchores.data.model.GroupwithTasks;
import com.split.osiris.splitchores.ui.main.MainFragment;

import java.util.List;

public class BindingUtils extends Converters {
  /*  @BindingAdapter({"app:setGroupAdapter","app:setFragment"})
    public static void setGroupAdapter(RecyclerView recyclerView) {
        GroupsRecyclerViewAdapter adapter = new GroupsRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        Log.d("mine","setgroupadater");
    }*/

}
