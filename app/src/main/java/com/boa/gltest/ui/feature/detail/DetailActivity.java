package com.boa.gltest.ui.feature.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.boa.gltest.R;
import com.boa.gltest.global.App;
import com.boa.gltest.global.di.DaggerDetailComponent;
import com.boa.gltest.global.di.DetailComponent;
import com.boa.gltest.global.di.DetailModule;
import com.boa.gltest.global.model.Item;
import com.boa.gltest.ui.base.BaseActivity;
import com.boa.gltest.ui.feature.list.ListActivity;
import com.squareup.picasso.Picasso;

import butterknife.InjectView;

public class DetailActivity extends BaseActivity implements DetailContract.View {
    @InjectView(R.id.tvDetailTitle)
    TextView tvDetailTitle;

    @InjectView(R.id.tvDetailDescription)
    TextView tvDetailDescription;

    @InjectView(R.id.ivImage)
    ImageView ivImage;

    @InjectView(R.id.ibBack)
    ImageButton ibBack;

    DetailPresenter presenter;
    private DetailComponent component;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        component().inject(this);
        init();
        prepare();
        presenter.initialize();
    }

    public void init() {
        if (presenter == null) {
            presenter = new DetailPresenter(this);
            presenter.attach(this);
        }
    }

    public void prepare() {
        ibBack.setVisibility(View.VISIBLE);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        prepare();
        init();
        presenter.resume();
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
    }

    @Override
    public void showItemError(Exception e) {
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
        Intent intent = new Intent(this, ListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
    }
}
