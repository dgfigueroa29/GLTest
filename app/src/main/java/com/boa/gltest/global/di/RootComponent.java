package com.boa.gltest.global.di;


import com.boa.gltest.global.App;
import com.boa.gltest.ui.feature.detail.DetailActivity;
import com.boa.gltest.ui.feature.detail.DetailPresenter;
import com.boa.gltest.ui.feature.list.ListActivity;
import com.boa.gltest.ui.feature.list.ListPresenter;

import dagger.Component;

@Component(modules = MainModule.class)
public interface RootComponent {
    void inject(App application);

    //Inject activities dependencies from this Component
    void inject(ListActivity activity);

    void inject(DetailActivity activity);

    void inject(ListPresenter presenter);

    void inject(DetailPresenter presenter);
}
