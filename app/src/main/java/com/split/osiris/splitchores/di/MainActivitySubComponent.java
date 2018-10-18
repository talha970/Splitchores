package com.split.osiris.splitchores.di;



import com.split.osiris.splitchores.Main2Activity;
import com.split.osiris.splitchores.ui.main.MainFragment;
import com.split.osiris.splitchores.ui.tasks.TasksFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {
        MainActivityModule.class
})
public interface MainActivitySubComponent {

    void inject(Main2Activity main2Activity);

    void inject(MainFragment mainFragment);

    void inject(TasksFragment tasksFragment);



    @Subcomponent.Builder
    interface Builder {
        Builder mainActivityModule(MainActivityModule module);
        MainActivitySubComponent build();
    }
}
