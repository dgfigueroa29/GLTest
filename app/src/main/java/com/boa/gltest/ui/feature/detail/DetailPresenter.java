package com.boa.gltest.ui.feature.detail;

import android.content.Context;

import com.boa.gltest.global.App;
import com.boa.gltest.global.di.RootComponent;
import com.boa.gltest.global.model.Item;
import com.boa.gltest.usecase.GetItem;

public class DetailPresenter implements DetailContract.Presenter {
    private DetailContract.View view;
    protected Context context;
    protected GetItem interactor;

    private RootComponent component;

    public DetailPresenter(Context context, GetItem interactor) {
        this.context = context;
        this.interactor = interactor;
        getComponent().inject(this);
    }

    @Override
    public void getItem() {
        interactor.getAsync(new GetItem.Listener() {
            @Override
            public void onItemReceived(Item item) {
                view.showItem(item);
            }

            @Override
            public void onError(Exception e) {
                view.showListError(e);
            }

            @Override
            public void onNoInternetAvailable() {
                view.showNoInternetMessage();
            }
        });
    }

    @Override
    public void initialize() {
        getItem();
    }

    @Override
    public void resume() {
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
