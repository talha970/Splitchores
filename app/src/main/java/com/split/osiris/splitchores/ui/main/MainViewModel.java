package com.split.osiris.splitchores.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.provider.ContactsContract;
import android.util.Log;

import com.split.osiris.splitchores.data.DataManager;
import com.split.osiris.splitchores.data.local.dao.UserDao;
import com.split.osiris.splitchores.data.model.Group;
import com.split.osiris.splitchores.data.model.GroupwithTasks;
import com.split.osiris.splitchores.data.model.Task;
import com.split.osiris.splitchores.ui.base.BaseViewModel;

import java.util.List;

public class MainViewModel  extends ViewModel{

    public LiveData<List<GroupwithTasks>> groupMutableLiveData;
    public MutableLiveData<String> currentUser = new MutableLiveData<>();
    private DataManager dataManager;

    public MainViewModel(DataManager dataManager) {
     this.dataManager = dataManager;
    }

    public LiveData<List<GroupwithTasks>> getGroupMutableLiveData() {
         groupMutableLiveData = dataManager.getGroupsFromRoom();
         return groupMutableLiveData;
    }



    public LiveData<List<Task>> getTasks(String groupId) {
        return dataManager.getTasks(groupId);
    }

    public LiveData<List<String>> getUserTasks() {
        if(currentUser.getValue()!=null){
            return dataManager.getUserTasks(currentUser.getValue());
        }
        else
            return null;

    }
}
