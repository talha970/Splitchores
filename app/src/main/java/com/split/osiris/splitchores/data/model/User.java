package com.split.osiris.splitchores.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import android.widget.ListAdapter;

import com.split.osiris.splitchores.data.local.room.SplitChoresTypeConverters;

import java.util.List;
@TypeConverters(SplitChoresTypeConverters.class)
@Entity(tableName = "user")
public class User {

    private String token;
    private List<String> groups;
    private List<String> tasks;
    private String email;
    @NonNull
    @PrimaryKey
    private String userId;

    public User(String token, List<String> groups, List<String> tasks, String email, @NonNull String userId) {
        this.token = token;
        this.groups = groups;
        this.tasks = tasks;
        this.email = email;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public List<String> getGroups() {
        return groups;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
