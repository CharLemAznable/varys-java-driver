package com.github.charlemaznable.varys;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("UnusedAssignment")
@Builder
@Getter
@EqualsAndHashCode
public class Config {

    private String address;

    @Default
    private int queryPoolMaxTotal = 8;
    @Default
    private int queryPoolMaxIdle = 8;
    @Default
    private int queryPoolMinIdle = 0;

    @Default
    private long appTokenCacheDuration = 10;
    @Default
    private TimeUnit appTokenCacheUnit = TimeUnit.MINUTES;
    @Default
    private long appAuthorizerTokenCacheDuration = 10;
    @Default
    private TimeUnit appAuthorizerTokenCacheUnit = TimeUnit.MINUTES;
    @Default
    private long corpTokenCacheDuration = 10;
    @Default
    private TimeUnit corpTokenCacheUnit = TimeUnit.MINUTES;
    @Default
    private long corpAuthorizerTokenCacheDuration = 10;
    @Default
    private TimeUnit corpAuthorizerTokenCacheUnit = TimeUnit.MINUTES;
}
