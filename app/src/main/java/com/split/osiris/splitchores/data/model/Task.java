package com.split.osiris.splitchores.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Task")
public class Task implements Parcelable {
    @PrimaryKey
    @NonNull
    @SerializedName("_id")
    private  String taskId;
    private  String groupId;
    @SerializedName("name")
    private  String name;
    private   String schedule;
    private  String assignedTo;

    protected Task(Parcel in) {
        taskId = in.readString();
        groupId = in.readString();
        name = in.readString();
        schedule = in.readString();
        assignedTo = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Task(String taskId, String name, String schedule, String assignedTo) {
        this.taskId = taskId;
        this.name = name;
        this.schedule = schedule;
        this.assignedTo = assignedTo;
        this.groupId = null;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(taskId);
        dest.writeString(groupId);
        dest.writeString(name);
        dest.writeString(schedule);
        dest.writeString(assignedTo);
    }
}
