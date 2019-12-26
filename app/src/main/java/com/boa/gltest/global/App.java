package com.boa.gltest.global;

import android.app.Application;

import androidx.annotation.VisibleForTesting;

import com.boa.gltest.global.di.DaggerRootComponent;
import com.boa.gltest.global.di.MainModule;
import com.boa.gltest.global.di.RootComponent;

public class App extends Application {
    private RootComponent rootComponent;
    private MainModule mainModule;

    @Override
    public void onCreate() {
        super.onCreate();
        mainModule = new MainModule(this);
        rootComponent = DaggerRootComponent.builder()
                .mainModule(mainModule)
                .build();
        rootComponent.inject(this);
    }

    public RootComponent getRootComponent() {
        return rootComponent;
    }

    @VisibleForTesting
    public void setRootComponent(RootComponent rootComponent) {
        this.rootComponent = rootComponent;
    }

    public MainModule getMainModule() {
        return mainModule;
    }

    public void setMainModule(MainModule mainModule) {
        this.mainModule = mainModule;
    }
}
