package com.split.osiris.splitchores.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.split.osiris.splitchores.BuildConfig;
import com.split.osiris.splitchores.R;
import com.split.osiris.splitchores.data.DataManager;
import com.split.osiris.splitchores.data.IDataManager;
import com.split.osiris.splitchores.data.local.prefs.AppPreferencesHelper;
import com.split.osiris.splitchores.data.local.prefs.PreferencesHelper;
import com.split.osiris.splitchores.data.local.room.AppDatabase;
import com.split.osiris.splitchores.data.remote.WebService;
import com.split.osiris.splitchores.ui.login.LoginViewModel;
import com.split.osiris.splitchores.ui.main.MainViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                                : HttpLoggingInterceptor.Level.NONE)).build();
    }

    @Singleton
    @Provides
    WebService provideWebService(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.DEBUG ?
                        context.getResources().getString(R.string.base_url_staging) :
                        context.getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(WebService.class);
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "splitchores-app")
               // .fallbackToDestructiveMigration()
                .build();
    }
    @Provides
    @Singleton
    IDataManager provideDataManager(DataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(Context context) {
        return new AppPreferencesHelper(context);
    }

}
