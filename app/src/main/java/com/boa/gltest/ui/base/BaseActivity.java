package com.boa.gltest.ui.base;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.boa.gltest.R;

public abstract class BaseActivity extends AppCompatActivity {
    public void showLoading() {
    }

    public void hideLoading() {
    }

    public void showError(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        hideLoading();
    }


    public void showOfflineMessage() {
        Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show();
        hideLoading();
    }
}
