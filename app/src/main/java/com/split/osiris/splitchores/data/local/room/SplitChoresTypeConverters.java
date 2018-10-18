package com.split.osiris.splitchores.data.local.room;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.split.osiris.splitchores.data.model.Task;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class SplitChoresTypeConverters {

    @TypeConverter
    public String fromTaskListToString(List<Task> value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }
    @TypeConverter
    public List<String> fromString(String value) {
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromStringList(List<String> value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }
    @TypeConverter
    public List<Task> fromTaskStringToList(String tasks){
        if (tasks == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Task>>() {}.getType();
        Gson gson = new Gson();
        return gson.fromJson(tasks, listType);
    }
}
