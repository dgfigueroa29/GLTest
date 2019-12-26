package com.boa.gltest.navigator;

import android.content.Context;
import android.content.Intent;

import com.boa.gltest.global.model.Item;
import com.boa.gltest.repository.ItemRepository;
import com.boa.gltest.ui.feature.detail.DetailActivity;
import com.boa.gltest.usecase.ShowItemClicked;

public class ShowItemClickedImpl implements ShowItemClicked {
    private Context context;

    public ShowItemClickedImpl(Context context) {
        this.context = context;
    }

    @Override
    public void show(Item item) {
        ItemRepository.conserve(context, item);
        Intent intent = new Intent(context, DetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
