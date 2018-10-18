package com.split.osiris.splitchores;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.split.osiris.splitchores.adapters.GroupsRecyclerViewAdapter;
import com.split.osiris.splitchores.data.model.GroupwithTasks;
import com.split.osiris.splitchores.di.MainActivityModule;
import com.split.osiris.splitchores.di.MainActivitySubComponent;
import com.split.osiris.splitchores.di.utils.DaggerUtil;
import com.split.osiris.splitchores.ui.main.MainFragment;
import com.split.osiris.splitchores.ui.main.MainViewModel;
import com.split.osiris.splitchores.ui.tasks.TasksFragment;

import javax.inject.Inject;

import retrofit2.http.PUT;

public class Main2Activity extends AppCompatActivity implements GroupsRecyclerViewAdapter.OnGroupClickListener {


    public static final String GROUP_BUNDLE_KEY = "GROUP_BUNDLE_KEY";
    private String currentFragmentTag;
    private static String FRAGMENT_STATE_KEY = "FRAGMENT_STATE_KEY";
    private static final String GROUP_FRAGMENT_TAG = "GROUP_FRAGMENT_TAG";
    private static final String TASK_FRAGMENT_TAG = "TASK_FRAGMENT_TAG";
    private MainActivitySubComponent mainActivitySubComponent;
    @Inject
    MainViewModel mainViewModel;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_dashboard:

                    return true;
            }
            return false;
        }
    };

    private void initMainSubComponent() {
        mainActivitySubComponent = MainApplication.getAppComponent().sharedComponent()
                .mainActivityModule(new MainActivityModule(this)).build();
    }
    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStackImmediate();

        }
        else{
            super.onBackPressed();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initMainSubComponent();
        getMainSubComponent().inject(this);

        //  MainApplication.getAppComponent().inject(this);
        mainViewModel.currentUser.setValue(getIntent().getExtras().getString(LauncherActivity.ACTIVE_USER));
        Log.d("mine",mainViewModel.currentUser.getValue());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            currentFragmentTag = GROUP_FRAGMENT_TAG;
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, MainFragment.newInstance(),currentFragmentTag)
                   // .addToBackStack(currentFragmentTag)
                    .commit();

        }

        else {
            currentFragmentTag = savedInstanceState.getString(FRAGMENT_STATE_KEY);
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(currentFragmentTag);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(currentFragmentTag)
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(FRAGMENT_STATE_KEY, currentFragmentTag);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        currentFragmentTag = savedInstanceState.getString(FRAGMENT_STATE_KEY);
        super.onRestoreInstanceState(savedInstanceState);
    }

    public MainActivitySubComponent getMainSubComponent() {
        return mainActivitySubComponent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainViewModel.getUserTasks().observe(this,tasks->{
            TextView textView = (TextView) findViewById(R.id.tasksNumber);
            String str = getResources().getString(R.string.main_tasks_text);
            assert tasks != null;
            textView.setText(String.format(str, String.valueOf(tasks.size())));
        });
    }

    @Override
    public void onGroupClicked(GroupwithTasks groupwithTasks) {
        Log.d("mine","rvclick");
        currentFragmentTag = TASK_FRAGMENT_TAG;
        Bundle bundle = new Bundle();
        bundle.putString(GROUP_BUNDLE_KEY,groupwithTasks.group.getId());
        TasksFragment fragment = new TasksFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,fragment)
                .addToBackStack(currentFragmentTag)
                .commit();

    }
}
