package com.boa.gltest.datasource.preferences;

import android.content.Context;

import com.boa.gltest.global.model.Item;
import com.boa.gltest.usecase.GetItem;
import com.google.gson.Gson;

public class GetItemPrefImpl implements GetItem {
    private Context context;

    public GetItemPrefImpl(Context context) {
        this.context = context;
    }

    @Override
    public Item get() {
        return bring();
    }

    @Override
    public void getAsync(Listener listener) {
        listener.onItemReceived(bring());
    }

    private Item bring() {
        String json = context.getSharedPreferences("boa", Context.MODE_PRIVATE)
                .getString("item", "");
        return new Gson().fromJson(json, Item.class);
    }
}
