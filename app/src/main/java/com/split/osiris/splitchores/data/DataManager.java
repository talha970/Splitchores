package com.split.osiris.splitchores.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.split.osiris.splitchores.data.local.prefs.PreferencesHelper;
import com.split.osiris.splitchores.data.local.room.AppDatabase;
import com.split.osiris.splitchores.data.model.Group;
import com.split.osiris.splitchores.data.model.GroupwithTasks;
import com.split.osiris.splitchores.data.model.Task;
import com.split.osiris.splitchores.data.model.api.LoginRequest;
import com.split.osiris.splitchores.data.remote.WebService;
import com.split.osiris.splitchores.utils.ErrorRetrievingDataException;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;


public class DataManager implements IDataManager {

    private static final String TAG = DataManager.class.getSimpleName();

    private final AppDatabase appDatabase;
    private final PreferencesHelper mPreferencesHelper;
    private final WebService webService;


    @Inject
    public DataManager(AppDatabase appDatabase, PreferencesHelper mPreferencesHelper, WebService webService) {
        this.mPreferencesHelper = mPreferencesHelper;
        this.webService = webService;
        this.appDatabase = appDatabase;
    }



    @Override
    public Single<List<Group>> doLoginCall(final LoginRequest loginRequest) {
        return webService.login(loginRequest).flatMap(user -> {
            appDatabase.userDao().insertUser(user);
            Log.d("mine",user.getGroups().get(0));

            return getGroups(user.getGroups())
                    .onErrorResumeNext(throwable ->
                    Single.error(new ErrorRetrievingDataException()));
        });
    }

    @Override
    public void doLogoutCall() {

    }

    @Override
    public Single<List<Group>> getGroups(List<String> grpIds) {
        Single<List<Group>> groups = webService.getGroups(grpIds);
        appDatabase.groupsDao().insertAll(groups.blockingGet());
        return groups;
    }

    @Override
    public LiveData<List<GroupwithTasks>> getGroupsFromRoom() {

       return appDatabase.groupsDao().getGroupwithTasks();
    }

    public LiveData<List<Task>> getTasks(String groupId) {
        return appDatabase.groupsDao().getGroupwithTasks(groupId);
    }
    public LiveData<List<String>> getUserTasks(String userId) {
        return appDatabase.userDao().getTasks(userId);
    }

}
