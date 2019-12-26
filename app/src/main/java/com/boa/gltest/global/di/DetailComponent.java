package com.boa.gltest.global.di;

import com.boa.gltest.ui.feature.detail.DetailActivity;

import dagger.Component;

@Component(dependencies = RootComponent.class, modules = {DetailModule.class, MainModule.class})
public interface DetailComponent {
    void inject(DetailActivity activity);
}
