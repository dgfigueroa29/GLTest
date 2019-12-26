package com.boa.gltest.ui.feature.list;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

import com.boa.gltest.ui.base.BaseActivity;

public class ListActivity extends BaseActivity implements ListContract.View {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        showLoading();
    }

    @Override
    public void showList() {
        hideLoading();
    }
}
