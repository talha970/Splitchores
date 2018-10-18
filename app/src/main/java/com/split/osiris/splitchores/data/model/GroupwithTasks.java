package com.split.osiris.splitchores.data.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Relation;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.split.osiris.splitchores.data.local.room.SplitChoresTypeConverters;

import java.util.List;

public class GroupwithTasks {
    @Embedded
    public Group group;

    @Relation(parentColumn = "id", entityColumn = "groupId", entity = Task.class)
    public List<Task> tasks;

}
