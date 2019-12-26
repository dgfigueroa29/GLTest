package com.boa.gltest.ui.feature.list;

public class ListPresenter implements ListContract.Presenter {
    private ListContract.View view;

    public ListPresenter(ListContract.View view) {
        this.view = view;
    }

    @Override
    public void getList() {
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
    public void attach(ListContract.View view) {
    }
}
