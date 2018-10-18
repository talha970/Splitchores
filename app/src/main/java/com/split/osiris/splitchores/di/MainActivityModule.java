package com.split.osiris.splitchores.di;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;


import com.split.osiris.splitchores.data.model.ViewModelFactory;
import com.split.osiris.splitchores.ui.main.MainViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private final FragmentActivity activityContext;

    public MainActivityModule(FragmentActivity context) {
        activityContext = context;
    }

    @Provides
    MainViewModel providerMainViewModel(ViewModelFactory factory) {
        return ViewModelProviders.of(activityContext, factory).get(MainViewModel.class);
    }
}
