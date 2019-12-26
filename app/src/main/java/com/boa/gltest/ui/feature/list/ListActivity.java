package com.boa.gltest.ui.feature.list;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.boa.gltest.R;
import com.boa.gltest.global.App;
import com.boa.gltest.global.di.DaggerListComponent;
import com.boa.gltest.global.di.ListComponent;
import com.boa.gltest.global.di.ListModule;
import com.boa.gltest.global.model.Item;
import com.boa.gltest.interactor.GetItemsInteractor;
import com.boa.gltest.ui.base.BaseActivity;
import com.boa.gltest.ui.render.ItemRenderer;
import com.boa.gltest.ui.render.ItemRendererBuilder;
import com.boa.gltest.usecase.ShowItemClicked;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ListActivity extends BaseActivity implements ListContract.View {
    @Bind(R.id.listProgressBar)
    ProgressBar listProgressBar;

    @Bind(R.id.list)
    RecyclerView list;

    ListPresenter presenter;
    RVRendererAdapter<Item> adapter;

    @Inject
    GetItemsInteractor interactor;
    ShowItemClicked showItemClicked;

    private ListComponent component;

    final ItemRenderer.OnItemClicked itemClicked = new ItemRenderer.OnItemClicked() {
        @Override
        public void onRowClicked(Item item) {
            presenter.onItemClicked(item);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        component().inject(this);

        adapter = new RVRendererAdapter<>(
                LayoutInflater.from(this),
                new ItemRendererBuilder(this, itemClicked),
                new ListAdapteeCollection<>(new ArrayList<Item>())
        );
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        list.setAdapter(adapter);
        presenter = new ListPresenter(this, interactor);
        presenter.attach(this);
        presenter.initialize();
        showLoading();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    public void showList(List<Item> itemList) {
        for (Item item : itemList) {
            adapter.add(item);
        }

        adapter.notifyDataSetChanged();
        hideLoading();
    }

    @Override
    public void showLoading() {
        listProgressBar.setVisibility(VISIBLE);
    }

    @Override
    public void hideLoading() {
        listProgressBar.setVisibility(GONE);
    }

    @Override
    public void goToDetail(Item item) {
        showItemClicked.show(item);
    }

    @Override
    public void showListError(Exception e) {
        showError(e);
    }

    @Override
    public void showNoInternetMessage() {
        showOfflineMessage();
    }

    private ListComponent component() {
        if (component == null) {
            component = DaggerListComponent.builder()
                    .rootComponent(((App) getApplication()).getRootComponent())
                    .listModule(new ListModule(getApplicationContext()))
                    .mainModule(((App) getApplication()).getMainModule())
                    .build();
        }

        return component;
    }
}
