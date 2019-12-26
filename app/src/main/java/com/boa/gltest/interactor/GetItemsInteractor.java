package com.boa.gltest.interactor;

import com.boa.gltest.datasource.api.NullListener;
import com.boa.gltest.global.model.Item;
import com.boa.gltest.usecase.GetItems;

import java.util.List;

public class GetItemsInteractor implements Interactor, GetItems, GetItems.Listener {
    GetItems.Listener listener = new NullListener();
    GetItems getItems;
    Executor executor;
    MainThread mainThread;

    public GetItemsInteractor(GetItems dataSource, Executor executor, MainThread mainThread) {
        this.getItems = dataSource;
        this.executor = executor;
        this.mainThread = mainThread;
    }

    @Override
    public void run() {
        getItems.getAsync(listener);
    }

    @Override
    public List<Item> get() {
        return getItems.get();
    }

    @Override
    public void getAsync(Listener listener) {
        if (listener != null) {
            this.listener = listener;
        }

        this.executor.run(this);
    }

    @Override
    public void onItemsReceived(List<Item> items, boolean isCached) {
        listener.onItemsReceived(items, isCached);
    }

    @Override
    public void onError(Exception e) {
        listener.onError(e);
    }

    @Override
    public void onNoInternetAvailable() {
        listener.onNoInternetAvailable();
    }
}
