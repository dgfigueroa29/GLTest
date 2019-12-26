package com.boa.gltest.ui.feature.detail;

import android.content.Context;

import com.boa.gltest.datasource.preferences.GetItemPrefImpl;
import com.boa.gltest.global.App;
import com.boa.gltest.global.di.RootComponent;

public class DetailPresenter implements DetailContract.Presenter {
    private DetailContract.View view;
    protected Context context;

    public DetailPresenter(Context context) {
        this.context = context;
        getComponent().inject(this);
    }

    @Override
    public void getItem() {
        view.showItem(new GetItemPrefImpl(context).get());
    }

    @Override
    public void initialize() {
        getItem();
    }

    @Override
    public void resume() {
        getItem();
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void attach(DetailContract.View view) {
        this.view = view;
    }

    public RootComponent getComponent() {
        return ((App) context.getApplicationContext()).getRootComponent();
    }
}
