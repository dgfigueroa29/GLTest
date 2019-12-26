package com.boa.gltest.global.di;

import android.content.Context;

import com.boa.gltest.navigator.ShowItemClickedImpl;
import com.boa.gltest.usecase.ShowItemClicked;

import dagger.Module;
import dagger.Provides;

@Module
public class ListModule {
    Context context;
    ShowItemClicked showItemClicked;

    public ListModule(final Context context) {
        this.context = context;
        showItemClicked = new ShowItemClickedImpl(context);
    }

    @Provides
    public ShowItemClicked provideShowItemClicked() {
        return showItemClicked;
    }
}
