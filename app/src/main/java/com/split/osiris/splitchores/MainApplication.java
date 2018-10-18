package com.split.osiris.splitchores;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.split.osiris.splitchores.di.AppComponent;
import com.split.osiris.splitchores.di.AppModule;
import com.split.osiris.splitchores.di.DaggerAppComponent;
import com.split.osiris.splitchores.ui.login.LoginModule;

public class MainApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .loginModule(new LoginModule())
                .build();
        Stetho.initializeWithDefaults(this);
        getAppComponent().inject(this);

    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}