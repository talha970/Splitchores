package com.split.osiris.splitchores;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.split.osiris.splitchores.data.local.room.AppDatabase;
import com.split.osiris.splitchores.data.model.User;
import com.split.osiris.splitchores.ui.login.LoginActivity;

import javax.inject.Inject;

public class LauncherActivity extends AppCompatActivity {

    public static final String ACTIVE_USER = "user";
    @Inject
    AppDatabase appDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        MainApplication.getAppComponent().inject(this);
        appDatabase.userDao().loadUser().observe(this, user -> RouteToActivity(user));


    }

    private void RouteToActivity(User user) {
        Intent intent ;
        if(user == null){
            intent = new Intent(this,LoginActivity.class);
        }
        else{
            Bundle bundle = new Bundle();
            bundle.putString(ACTIVE_USER,user.getUserId());
            intent = new Intent(this,Main2Activity.class);
            intent.putExtras(bundle);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


}
