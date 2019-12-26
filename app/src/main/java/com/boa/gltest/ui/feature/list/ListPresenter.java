package com.boa.gltest.ui.feature.list;

import android.content.Context;

import com.boa.gltest.global.App;
import com.boa.gltest.global.di.RootComponent;
import com.boa.gltest.global.model.Item;
import com.boa.gltest.interactor.GetItemsInteractor;
import com.boa.gltest.usecase.GetItems;

import java.util.List;

import javax.inject.Inject;

public class ListPresenter implements ListContract.Presenter {
    private ListContract.View view;

    protected GetItems interactor;
    protected Context context;

    private RootComponent component;

    @Inject
    public ListPresenter(Context ctx, GetItemsInteractor getItemsInteractor) {
        context = ctx;
        interactor = getItemsInteractor;
        getComponent().inject(this);
    }

    @Override
    public void getList() {
        interactor.getAsync(new GetItems.Listener() {
            @Override
            public void onItemsReceived(List<Item> items, boolean isCached) {
                view.showList(items);
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
        getList();
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
    public void attach(ListContract.View view) {
        this.view = view;
    }

    public void onItemClicked(Item item) {
        view.goToDetail(item);
    }

    public RootComponent getComponent() {
        return ((App) context.getApplicationContext()).getRootComponent();
    }
}
