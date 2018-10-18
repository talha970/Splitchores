package com.split.osiris.splitchores.data.model;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;
import com.split.osiris.splitchores.data.DataManager;
import com.split.osiris.splitchores.ui.main.MainViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {

    private final DataManager dataManager;

    @Inject
    public ViewModelFactory(@NonNull Context context, @NonNull  DataManager dataManager) {
        super((Application) context);
        this.dataManager = dataManager;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == MainViewModel.class) {
            return (T) new MainViewModel(dataManager);
        }
        return super.create(modelClass);
    }
}
