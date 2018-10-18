package com.split.osiris.splitchores.data.local.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.split.osiris.splitchores.data.local.dao.GroupsDao;
import com.split.osiris.splitchores.data.local.dao.UserDao;
import com.split.osiris.splitchores.data.model.Group;
import com.split.osiris.splitchores.data.model.Task;
import com.split.osiris.splitchores.data.model.User;

@TypeConverters({SplitChoresTypeConverters.class})
@Database(entities = {Group.class,Task.class,User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GroupsDao groupsDao();
    public abstract UserDao userDao();

}