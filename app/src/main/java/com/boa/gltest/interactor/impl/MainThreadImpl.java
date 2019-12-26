package com.boa.gltest.interactor.impl;

import android.os.Handler;
import android.os.Looper;

import com.boa.gltest.interactor.MainThread;

public class MainThreadImpl implements MainThread {
    private Handler handler;

    public MainThreadImpl() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}