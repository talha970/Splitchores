package com.split.osiris.splitchores.data;

import android.arch.lifecycle.LiveData;

import com.split.osiris.splitchores.data.model.Group;
import com.split.osiris.splitchores.data.model.GroupwithTasks;
import com.split.osiris.splitchores.data.model.api.LoginRequest;

import java.util.List;

import io.reactivex.Single;

public interface IDataManager {
   Single<List<Group>> doLoginCall(LoginRequest loginRequest);
   void doLogoutCall();
   Single<List<Group>> getGroups(List<String> grpIds);
   LiveData<List<GroupwithTasks>> getGroupsFromRoom();

}
