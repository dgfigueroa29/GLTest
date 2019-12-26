package com.boa.gltest.ui.render;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boa.gltest.R;
import com.boa.gltest.global.model.Item;
import com.pedrogomez.renderers.Renderer;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ItemRenderer extends Renderer<Item> {
    private Context context;
    private OnItemClicked listener;

    public ItemRenderer(Context context, OnItemClicked listener) {
        this.context = context.getApplicationContext();
        this.listener = listener;
    }

    @InjectView(R.id.ivItem)
    ImageView ivItem;

    @InjectView(R.id.tvItemTitle)
    TextView tvItemTitle;

    @InjectView(R.id.tvItemDescription)
    TextView tvItemDescription;

    @InjectView(R.id.rlItem)
    RelativeLayout rlItem;

    @Override
    protected void setUpView(View rootView) {
        ButterKnife.inject(this, rootView);
    }

    @Override
    protected void hookListeners(View rootView) {
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.row_item, parent, false);
    }

    @OnClick(R.id.rlItem)
    void onClickItem() {
        listener.onRowClicked(getContent());
    }

    @Override
    public void render() {
        Item item = getContent();
        renderDescription(item.getDescription());
        renderTitle(item.getTitle());
        renderImage(item.getImage());
    }

    private void renderImage(String image) {
        Picasso.get()
                .load(image)
                .resizeDimen(R.dimen.user_thumbnail_w, R.dimen.user_thumbnail_h)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(ivItem);
    }

    private void renderTitle(String title) {
        tvItemTitle.setText(title);
    }

    private void renderDescription(String description) {
        tvItemDescription.setText(description);
    }

    public interface OnItemClicked {
        void onRowClicked(Item item);
    }
}
