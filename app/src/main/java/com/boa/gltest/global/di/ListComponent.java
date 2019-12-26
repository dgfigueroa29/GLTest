package com.boa.gltest.global.di;

import com.boa.gltest.ui.feature.list.ListActivity;

import dagger.Component;

@Component(dependencies = RootComponent.class, modules = {ListModule.class, MainModule.class})
public interface ListComponent {
    void inject(ListActivity activity);
}
