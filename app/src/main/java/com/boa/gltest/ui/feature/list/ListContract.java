package com.boa.gltest.ui.feature.list;

import com.boa.gltest.global.model.Item;
import com.boa.gltest.ui.base.BaseContract;

import java.util.List;

public class ListContract {
    interface View extends BaseContract.View {
        void showList(List<Item> itemList);

        void goToDetail(Item item);

        void showListError(Exception e);

        void showNoInternetMessage();
    }

    interface Presenter extends BaseContract.Presenter<ListContract.View> {
        void getList();
    }
}
