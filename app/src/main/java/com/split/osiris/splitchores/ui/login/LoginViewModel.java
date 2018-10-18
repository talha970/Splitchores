package com.split.osiris.splitchores.ui.login;


import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;
import android.util.Log;


import com.split.osiris.splitchores.R;
import com.split.osiris.splitchores.data.DataManager;
import com.split.osiris.splitchores.data.model.Group;
import com.split.osiris.splitchores.data.model.api.LoginRequest;
import com.split.osiris.splitchores.ui.base.BaseViewModel;
import com.split.osiris.splitchores.ui.base.SingleLiveEvent;
import com.split.osiris.splitchores.utils.SnackbarMessage;
import com.split.osiris.splitchores.utils.ErrorRetrievingDataException;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel {

    public SingleLiveEvent<Boolean> singleLiveEvent = new SingleLiveEvent<>();
    private static final String TAG = LoginViewModel.class.getSimpleName();

    private final SnackbarMessage mSnackbarText = new SnackbarMessage();

    public LoginViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void login(String userId, String password) {
        setIsLoading(true);

        getDataManager()
                .doLoginCall(new LoginRequest(userId, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(groups -> {
                    // handle data fetched successfully and API call completed
                    Log.d(TAG,groups.get(0).getGroupName());
                    singleLiveEvent.setValue(true);
                    setIsLoading(false);
                }, throwable -> {
                    // handle error event
                    Log.d(TAG,"login error "+ throwable);
                    showSnackbarMessage(R.string.failed_login);

                    setIsLoading(false);
                });

    }

    SnackbarMessage getSnackbarMessage() {
        return mSnackbarText;
    }

    private void showSnackbarMessage(Integer message) {
        mSnackbarText.setValue(message);
    }

    public boolean isEmailAndPasswordValid(String userId, String password) {
        // validate email and password
        if (TextUtils.isEmpty(userId)) {
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }
}
