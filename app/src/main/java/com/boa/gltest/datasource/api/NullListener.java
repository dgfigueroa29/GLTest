package com.boa.gltest.datasource.api;

import com.boa.gltest.global.model.Item;
import com.boa.gltest.usecase.GetItems;

import java.util.List;

public class NullListener implements GetItems.Listener {
    @Override
    public void onItemsReceived(List<Item> items, boolean isCached) {
    }

    @Override
    public void onError(Exception e) {
    }

    @Override
    public void onNoInternetAvailable() {
    }
}
