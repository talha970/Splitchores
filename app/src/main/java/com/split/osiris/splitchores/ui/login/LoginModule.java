package com.split.osiris.splitchores.ui.login;

import com.split.osiris.splitchores.data.DataManager;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    LoginViewModel provideLoginViewModel(DataManager dataManager) {
        return new LoginViewModel(dataManager);
    }
}