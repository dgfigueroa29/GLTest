package com.boa.gltest.global.di;

import android.content.Context;

import com.boa.gltest.datasource.api.GetItemsApiImpl;
import com.boa.gltest.datasource.preferences.GetItemPrefImpl;
import com.boa.gltest.global.App;
import com.boa.gltest.interactor.GetItemInteractor;
import com.boa.gltest.interactor.GetItemsInteractor;
import com.boa.gltest.interactor.impl.MainThreadImpl;
import com.boa.gltest.interactor.impl.ThreadExecutor;
import com.boa.gltest.navigator.ShowItemClickedImpl;
import com.boa.gltest.repository.ItemRepository;
import com.boa.gltest.usecase.ShowItemClicked;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private App application;
    GetItemsInteractor getItemsInteractor;
    GetItemInteractor getItemInteractor;
    ShowItemClickedImpl showItemClicked;
    ItemRepository itemRepository;

    public MainModule(App application) {
        this.application = application;

        getItemsInteractor = new GetItemsInteractor(new GetItemsApiImpl(),
                new ThreadExecutor(),
                new MainThreadImpl());
        getItemInteractor = new GetItemInteractor(new GetItemPrefImpl(application),
                new ThreadExecutor(),
                new MainThreadImpl());
        showItemClicked = new ShowItemClickedImpl(application);
        itemRepository = new ItemRepository(application, getItemsInteractor);
    }

    @Provides
    GetItemsInteractor provideGetItemsInteractor() {
        return getItemsInteractor;
    }

    @Provides
    ShowItemClicked provideShowItemClicked() {
        return showItemClicked;
    }

    @Provides
    GetItemInteractor provideGetItemInteractor() {
        return getItemInteractor;
    }

    @Provides
    ItemRepository provideItemRepository() {
        return itemRepository;
    }

    @Provides
    @Named("applicationContext")
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }
}
