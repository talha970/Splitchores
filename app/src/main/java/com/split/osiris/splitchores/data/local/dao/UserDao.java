package com.split.osiris.splitchores.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.split.osiris.splitchores.data.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUser(User user);

    @Query("SELECT * FROM user")
    public LiveData<User> loadUser();

    @Query("SELECT groups FROM user where userId = :userId")
    public List<String> getGroups(String userId);

    @Query("SELECT tasks FROM user where userId = :userId")
    public LiveData<List<String>> getTasks(String userId);
}
