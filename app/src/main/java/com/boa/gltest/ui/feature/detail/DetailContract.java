package com.boa.gltest.ui.feature.detail;

import com.boa.gltest.ui.base.BaseContract;

public class DetailContract {
    interface View extends BaseContract.View {
        void showItem();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getItem();
    }
}
