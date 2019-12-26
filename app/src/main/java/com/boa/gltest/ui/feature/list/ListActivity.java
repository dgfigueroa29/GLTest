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
import com.boa.gltest.navigator.ShowItemClickedImpl;
import com.boa.gltest.ui.base.BaseActivity;
import com.boa.gltest.ui.render.ItemRenderer;
import com.boa.gltest.ui.render.ItemRendererBuilder;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ListActivity extends BaseActivity implements ListContract.View {
    @InjectView(R.id.listProgressBar)
    ProgressBar listProgressBar;

    @InjectView(R.id.list)
    RecyclerView list;

    ListPresenter presenter;
    RVRendererAdapter<Item> adapter;

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
        init();
        prepare();
        presenter.initialize();
        showLoading();
    }

    @Override
    protected void onResume() {
        super.onResume();
        prepare();
        adapter.clear();
        init();
        presenter.resume();
    }

    private void init() {
        if (presenter == null) {
            presenter = new ListPresenter(this);
            presenter.attach(this);
        }
    }

    private void prepare() {
        adapter = new RVRendererAdapter<>(
                LayoutInflater.from(this),
                new ItemRendererBuilder(this, itemClicked),
                new ListAdapteeCollection<>(new ArrayList<Item>())
        );
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        list.setAdapter(adapter);
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
        new ShowItemClickedImpl(this).show(item);
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

    @Override
    public void onBackPressed() {
        finish();
    }
}
