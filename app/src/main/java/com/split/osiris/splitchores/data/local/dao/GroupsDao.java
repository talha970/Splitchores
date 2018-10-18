package com.split.osiris.splitchores.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.split.osiris.splitchores.data.model.Group;
import com.split.osiris.splitchores.data.model.GroupwithTasks;
import com.split.osiris.splitchores.data.model.Task;

import java.util.List;

@Dao
public abstract class GroupsDao {

    public void insertAll(List<Group> groups) {
        for(Group group:groups) {
            if(group.tasks != null) {
                insertTasksForGroups(group, group.tasks);
            }
        }
        _insertAllGroups(groups);
    }

    private void insertTasksForGroups(Group group, List<Task> tasks) {
        for(Task task : tasks){
            task.setGroupId(group.getId());
        }
        _insertAllTasks(tasks);
    }

    public LiveData<List<GroupwithTasks>> getGroupwithTasks(){
         return getAllGroups();
    }
    public LiveData<List<Task>> getGroupwithTasks(String groupId){
        return abstractGetTasksOfGroup(groupId);
    }
    @Transaction
    @Query("select * from groups")
    abstract LiveData<List<GroupwithTasks>> getAllGroups();

    @Transaction
    @Query("select * from Task where groupId = :groupId")
    abstract LiveData<List<Task>> abstractGetTasksOfGroup(String groupId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void _insertAllTasks(List<Task> tasks);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void _insertAllGroups(List<Group> groups);

}
