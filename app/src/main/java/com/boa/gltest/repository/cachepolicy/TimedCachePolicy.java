package com.boa.gltest.repository.cachepolicy;

public class TimedCachePolicy implements CachePolicy {
    protected static final long MS_PER_MINUTE = 60000;
    public static final long ONE_MINUTE = MS_PER_MINUTE;

    long start;
    long expiration = 0;

    public TimedCachePolicy(long millis) {
        start = System.currentTimeMillis();
        expiration = millis;
    }

    @Override
    public boolean isCacheValid() {
        return System.currentTimeMillis() <= start + expiration;
    }
}
