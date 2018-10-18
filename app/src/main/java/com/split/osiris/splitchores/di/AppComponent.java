package com.split.osiris.splitchores.di;

import com.split.osiris.splitchores.LauncherActivity;
import com.split.osiris.splitchores.Main2Activity;
import com.split.osiris.splitchores.MainActivity;
import com.split.osiris.splitchores.MainApplication;
import com.split.osiris.splitchores.ui.login.LoginActivity;
import com.split.osiris.splitchores.ui.login.LoginModule;
import com.split.osiris.splitchores.ui.main.MainFragment;
import com.split.osiris.splitchores.ui.tasks.TasksFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,LoginModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);


    void inject(LoginActivity loginActivity);


    void inject(MainApplication mainApplication);



    void inject(LauncherActivity launcherActivity);



    MainActivitySubComponent.Builder sharedComponent();


}
