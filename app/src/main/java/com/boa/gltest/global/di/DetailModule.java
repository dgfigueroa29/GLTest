package com.boa.gltest.global.di;

import android.content.Context;

import dagger.Module;

@Module
public class DetailModule {
    Context context;

    public DetailModule(Context context) {
        this.context = context;
    }
}
