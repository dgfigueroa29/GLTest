package com.boa.gltest.usecase;

import com.boa.gltest.global.model.Item;

public interface GetItem {
    Item get();

    void getAsync(final Listener listener);

    interface Listener {
        void onItemReceived(final Item item);

        void onError(Exception e);

        void onNoInternetAvailable();
    }
}
