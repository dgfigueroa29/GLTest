package com.boa.gltest.ui.feature.detail;

public class DetailPresenter implements DetailContract.Presenter {
    private DetailContract.View view;

    public DetailPresenter(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void getItem() {
    }

    @Override
    public void initialize() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void attach(DetailContract.View view) {
    }
}
