package com.boa.gltest.ui.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.boa.gltest.BuildConfig;
import com.boa.gltest.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.inject(this);
    }

    public void showLoading() {
    }

    public void hideLoading() {
    }

    public void showError(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        if (BuildConfig.ShowLogs) {
            System.out.println("ERROR: " + e.getMessage());
        }

        hideLoading();
    }


    public void showOfflineMessage() {
        Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show();
        hideLoading();
    }
}
