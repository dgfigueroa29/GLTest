package com.boa.gltest.ui.feature.detail;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

import com.boa.gltest.ui.base.BaseActivity;

public class DetailActivity extends BaseActivity implements DetailContract.View {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        showLoading();
    }

    @Override
    public void showItem() {
        hideLoading();
    }
}
