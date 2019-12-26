package com.boa.gltest.ui.feature.list;

import com.boa.gltest.ui.base.BaseContract;

public class ListContract {
    interface View extends BaseContract.View {
        void showList();
    }

    interface Presenter extends BaseContract.Presenter<ListContract.View> {
        void getList();
    }
}
