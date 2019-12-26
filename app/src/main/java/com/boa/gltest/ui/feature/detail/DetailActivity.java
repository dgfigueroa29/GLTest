package com.boa.gltest.ui.feature.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.boa.gltest.R;
import com.boa.gltest.global.App;
import com.boa.gltest.global.di.DaggerDetailComponent;
import com.boa.gltest.global.di.DetailComponent;
import com.boa.gltest.global.di.DetailModule;
import com.boa.gltest.global.model.Item;
import com.boa.gltest.interactor.GetItemInteractor;
import com.boa.gltest.ui.base.BaseActivity;
import com.boa.gltest.ui.feature.list.ListActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements DetailContract.View {
    @Bind(R.id.tvDetailTitle)
    TextView tvDetailTitle;

    @Bind(R.id.tvDetailDescription)
    TextView tvDetailDescription;

    @Bind(R.id.ivImage)
    ImageView ivImage;

    DetailPresenter presenter;
    private DetailComponent component;

    @Inject
    GetItemInteractor interactor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        component().inject(this);
        setContentView(R.layout.activity_detail);
        presenter = new DetailPresenter(this, interactor);
        presenter.attach(this);
        presenter.initialize();
        ButterKnife.bind(this);
        showLoading();
    }

    @Override
    public void showItem(Item item) {
        tvDetailTitle.setText(item.getTitle());
        tvDetailDescription.setText(item.getDescription());
        Picasso.get()
                .load(item.getImage())
                .resizeDimen(R.dimen.user_thumbnail_w, R.dimen.user_thumbnail_h)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(ivImage);
        hideLoading();
    }

    @Override
    public void showListError(Exception e) {
        showError(e);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public void showNoInternetMessage() {
        showOfflineMessage();
    }

    private DetailComponent component() {
        if (component == null) {
            component = DaggerDetailComponent.builder()
                    .rootComponent(((App) getApplication()).getRootComponent())
                    .detailModule(new DetailModule(getApplicationContext()))
                    .mainModule(((App) getApplication()).getMainModule())
                    .build();
        }

        return component;
    }

    @Override
    public void onBackPressed() {
        getSharedPreferences("boa", Context.MODE_PRIVATE).edit()
                .putString("item", "").apply();
        startActivity(new Intent(this, ListActivity.class));
        finish();
    }
}
