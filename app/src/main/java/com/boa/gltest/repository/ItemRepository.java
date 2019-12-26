package com.boa.gltest.repository;

import android.content.Context;

import com.boa.gltest.global.model.Item;
import com.boa.gltest.repository.cachepolicy.CachePolicy;
import com.boa.gltest.repository.cachepolicy.TimedCachePolicy;
import com.boa.gltest.usecase.GetItems;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ItemRepository implements GetItems {
    Context context;
    CachePolicy cachePolicy;
    GetItems apiDataSource;
    List<Item> items = new ArrayList<>();

    public ItemRepository(Context context, GetItems apiDataSource) {
        this.context = context;
        this.apiDataSource = apiDataSource;
    }

    public void setCachePolicy(CachePolicy cachePolicy) {
        this.cachePolicy = cachePolicy;
    }

    @Override
    public List<Item> get() {
        invalidateCacheIfNecessary();

        if (items != null && items.size() > 0) {
            return items;
        }

        List<Item> apiUsers = apiDataSource.get();
        cachePolicy = new TimedCachePolicy(TimedCachePolicy.ONE_MINUTE);
        items = apiUsers;
        return items;
    }

    @Override
    public void getAsync(final Listener listener) {
        invalidateCacheIfNecessary();

        if (items != null && items.size() > 0) {
            listener.onItemsReceived(items, true);
        } else {
            apiDataSource.getAsync(new Listener() {
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
            });
        }
    }

    private void invalidateCacheIfNecessary() {
        if (!cachePolicy.isCacheValid()) {
            items.clear();
        }
    }

    public static void conserve(Context context, Item item) {
        String json = new Gson().toJson(item);
        context.getSharedPreferences("boa", Context.MODE_PRIVATE).edit()
                .putString("item", json).apply();
    }

    public static Item bring(Context context) {
        String json = context.getSharedPreferences("boa", Context.MODE_PRIVATE)
                .getString("item", "");
        return new Gson().fromJson(json, Item.class);
    }
}
