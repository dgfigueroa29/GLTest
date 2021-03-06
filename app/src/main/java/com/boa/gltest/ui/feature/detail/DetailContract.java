package com.boa.gltest.ui.feature.detail;

import com.boa.gltest.global.model.Item;
import com.boa.gltest.ui.base.BaseContract;

public class DetailContract {
    interface View extends BaseContract.View {
        void showItem(Item item);

        void showItemError(Exception e);

        void showNoInternetMessage();
    }

    interface Presenter extends BaseContract.Presenter<DetailContract.View> {
        void getItem();
    }
}
