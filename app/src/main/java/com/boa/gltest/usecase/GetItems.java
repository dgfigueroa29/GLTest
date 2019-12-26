package com.boa.gltest.usecase;

import com.boa.gltest.global.model.Item;

import java.util.List;

public interface GetItems {
    List<Item> get();

    void getAsync(final Listener listener);

    interface Listener {
        void onItemsReceived(final List<Item> items, boolean isCached);

        void onError(Exception e);

        void onNoInternetAvailable();
    }
}
