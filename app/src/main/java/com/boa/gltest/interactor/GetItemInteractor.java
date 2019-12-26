package com.boa.gltest.interactor;

import com.boa.gltest.global.model.Item;
import com.boa.gltest.usecase.GetItem;

public class GetItemInteractor implements Interactor, GetItem, GetItem.Listener {
    GetItem.Listener listener;
    GetItem getItem;
    Executor executor;
    MainThread mainThread;

    public GetItemInteractor(GetItem getItem, Executor executor, MainThread mainThread) {
        this.getItem = getItem;
        this.executor = executor;
        this.mainThread = mainThread;
    }

    @Override
    public void run() {
        getItem.getAsync(listener);
    }

    @Override
    public Item get() {
        return getItem.get();
    }

    @Override
    public void getAsync(Listener listener) {
        if (listener != null) {
            this.listener = listener;
        }

        this.executor.run(this);
    }

    @Override
    public void onItemReceived(Item item) {
        listener.onItemReceived(item);
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
