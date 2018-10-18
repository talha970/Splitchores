package com.split.osiris.splitchores.ui.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.split.osiris.splitchores.Main2Activity;
import com.split.osiris.splitchores.MainApplication;
import com.split.osiris.splitchores.R;
import com.split.osiris.splitchores.databinding.LoginActivityBinding;
import com.split.osiris.splitchores.utils.SnackbarMessage;
import com.split.osiris.splitchores.utils.SnackbarUtils;

import javax.inject.Inject;

import static com.split.osiris.splitchores.MainApplication.getAppComponent;

public class LoginActivity extends AppCompatActivity implements LoginInterface,Observer<Boolean> {
    private LoginActivityBinding binding;
    @Inject
    LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAppComponent().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.login_activity);
        binding.setListener(this);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        setupSnackbar();
        viewModel.singleLiveEvent.observe(this, this);

    }


    private void setupSnackbar() {
        viewModel.getSnackbarMessage().observe(this, new SnackbarMessage.SnackbarObserver() {
            @Override
            public void onNewMessage(@StringRes int snackbarMessageResourceId) {
                Log.d("mine", String.valueOf(snackbarMessageResourceId));
                Toast.makeText(LoginActivity.this, getString(R.string.invalid_email_password), Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public void login() {
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        if (viewModel.isEmailAndPasswordValid(email, password)) {
            viewModel.login(email, password);
        } else {
            Toast.makeText(this, getString(R.string.invalid_email_password), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onChanged(@Nullable Boolean aBoolean) {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}
