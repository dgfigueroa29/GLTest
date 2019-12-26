package com.boa.gltest.ui.base;

public class BaseContract {
    public interface Presenter<T> {
        void initialize();

        void resume();

        void pause();

        void destroy();

        void attach(T view);
    }

    public interface View {
    }
}
