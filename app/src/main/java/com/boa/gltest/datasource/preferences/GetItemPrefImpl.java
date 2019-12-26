package com.boa.gltest.datasource.preferences;

import android.content.Context;

import com.boa.gltest.global.model.Item;
import com.boa.gltest.repository.ItemRepository;
import com.boa.gltest.usecase.GetItem;

public class GetItemPrefImpl implements GetItem {
    private Context context;

    public GetItemPrefImpl(Context context) {
        this.context = context;
    }

    @Override
    public Item get() {
        return bring(context);
    }

    @Override
    public void getAsync(Listener listener) {
        listener.onItemReceived(bring(context));
    }

    public static Item bring(Context context) {
        return ItemRepository.bring(context);
    }
}
