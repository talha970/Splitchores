package com.split.osiris.splitchores.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.split.osiris.splitchores.data.local.room.SplitChoresTypeConverters;

import java.util.List;

@Entity(tableName = "groups")
public class Group implements Parcelable {
    @TypeConverters(SplitChoresTypeConverters.class)
    @SerializedName("members")
    private final List<String> groupMembers;
    @SerializedName("name")
    private final String groupName;
    @PrimaryKey
    @NonNull
    @SerializedName("_id")
    private final String id;


    @SerializedName("tasks")
    @Ignore
    public List<Task> tasks;

    public Group(List<String> groupMembers, String groupName, String id) {
        this.groupMembers = groupMembers;
        this.groupName = groupName;
        this.id = id;
        this.tasks = tasks;
    }


    protected Group(Parcel in) {
        groupMembers = in.createStringArrayList();
        groupName = in.readString();
        id = in.readString();
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

    public List<String> getGroupMembers() {
        return groupMembers;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getId() {
        return id;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(groupMembers);
        dest.writeString(groupName);
        dest.writeString(id);
    }

}
