package com.split.osiris.splitchores.di.utils;

import android.app.Activity;

import com.split.osiris.splitchores.Main2Activity;
import com.split.osiris.splitchores.di.MainActivitySubComponent;

public class DaggerUtil {

    public static MainActivitySubComponent getMainSubComponent(Activity activity) {
        MainActivitySubComponent subComponent = null;
        if (null == activity) {
            return subComponent;
        }

        if (activity instanceof Main2Activity) {
            subComponent = ((Main2Activity) activity).getMainSubComponent();
        }
        return subComponent;
    }
}
