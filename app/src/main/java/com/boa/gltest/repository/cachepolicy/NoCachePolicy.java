package com.boa.gltest.repository.cachepolicy;

public class NoCachePolicy implements CachePolicy {
    @Override
    public boolean isCacheValid() {
        return false;
    }
}