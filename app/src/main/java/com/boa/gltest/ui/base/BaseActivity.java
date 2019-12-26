package com.boa.gltest.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected int layoutId;

    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(layoutId);
        ButterKnife.inject(this);
    }

    public void showLoading() {
    }

    public void hideLoading() {
    }

    public void showError(Exception e) {
        hideLoading();
    }


    public void showOfflineMessage() {
        hideLoading();
    }
}
