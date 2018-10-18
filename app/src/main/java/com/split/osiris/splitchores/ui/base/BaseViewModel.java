package com.split.osiris.splitchores.ui.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.split.osiris.splitchores.data.DataManager;




public abstract class BaseViewModel extends ViewModel {


    private DataManager dataManager;

    public BaseViewModel(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public BaseViewModel(){
        this.isLoading.setValue(false);
    }

    public void setIsLoading(Boolean isLoading) {
        this.isLoading.setValue(isLoading);
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }
}
