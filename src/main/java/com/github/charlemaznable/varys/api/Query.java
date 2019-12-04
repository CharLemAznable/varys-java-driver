package com.github.charlemaznable.varys.api;

import com.github.charlemaznable.core.net.HttpReq;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.github.charlemaznable.varys.resp.AppAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.AppTokenResp;
import com.github.charlemaznable.varys.resp.CorpAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.CorpTokenResp;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.AllArgsConstructor;
import lombok.val;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;

import static com.github.charlemaznable.core.codec.Json.unJson;
import static com.github.charlemaznable.core.lang.Condition.checkNotNull;
import static com.github.charlemaznable.core.lang.LoadingCachee.get;
import static com.github.charlemaznable.core.lang.LoadingCachee.writeCache;

public class Query {

    private LoadingCache<String, AppTokenResp> appTokenCache;
    private LoadingCache<Pair<String, String>, AppAuthorizerTokenResp> appAuthorizerTokenCache;
    private LoadingCache<String, CorpTokenResp> corpTokenCache;
    private LoadingCache<Pair<String, String>, CorpAuthorizerTokenResp> corpAuthorizerTokenCache;

    public Query(VarysConfig varysConfig) {
        appTokenCache = writeCache(new QueryCacheLoader<String, AppTokenResp>(varysConfig) {
            @Override
            public AppTokenResp load(@Nonnull String codeName) {
                return unJson(httpGet(varysConfig.appTokenCachePath() + codeName), AppTokenResp.class);
            }
        }, varysConfig.appTokenCacheDuration(), varysConfig.appTokenCacheTimeUnit());

        appAuthorizerTokenCache = writeCache(new QueryCacheLoader<Pair<String, String>, AppAuthorizerTokenResp>(varysConfig) {
            @Override
            public AppAuthorizerTokenResp load(@Nonnull Pair<String, String> pair) {
                val codeName = pair.getLeft();
                val authorizerAppId = pair.getRight();

                return unJson(httpGet(varysConfig.appAuthorizerTokenCachePath() + codeName
                        + "/" + authorizerAppId), AppAuthorizerTokenResp.class);
            }
        }, varysConfig.appAuthorizerTokenCacheDuration(), varysConfig.appAuthorizerTokenCacheTimeUnit());

        corpTokenCache = writeCache(new QueryCacheLoader<String, CorpTokenResp>(varysConfig) {
            @Override
            public CorpTokenResp load(@Nonnull String codeName) {
                return unJson(httpGet(varysConfig.corpTokenCachePath() + codeName), CorpTokenResp.class);
            }
        }, varysConfig.corpTokenCacheDuration(), varysConfig.corpTokenCacheTimeUnit());

        corpAuthorizerTokenCache = writeCache(new QueryCacheLoader<Pair<String, String>, CorpAuthorizerTokenResp>(varysConfig) {
            @Override
            public CorpAuthorizerTokenResp load(@Nonnull Pair<String, String> pair) {
                val codeName = pair.getLeft();
                val corpId = pair.getRight();

                return unJson(httpGet(varysConfig.corpAuthorizerTokenCachePath() + codeName
                        + "/" + corpId), CorpAuthorizerTokenResp.class);
            }
        }, varysConfig.corpAuthorizerTokenCacheDuration(), varysConfig.corpAuthorizerTokenCacheTimeUnit());
    }

    public AppTokenResp appToken(String codeName) {
        checkNotNull(appTokenCache);
        checkNotNull(codeName);
        return get(appTokenCache, codeName);
    }

    public AppAuthorizerTokenResp appAuthorizerToken(String codeName, String authorizerAppId) {
        checkNotNull(appAuthorizerTokenCache);
        checkNotNull(codeName);
        checkNotNull(authorizerAppId);
        return get(appAuthorizerTokenCache, Pair.of(codeName, authorizerAppId));
    }

    public CorpTokenResp corpToken(String codeName) {
        checkNotNull(corpTokenCache);
        checkNotNull(codeName);
        return get(corpTokenCache, codeName);
    }

    public CorpAuthorizerTokenResp corpAuthorizerToken(String codeName, String corpId) {
        checkNotNull(corpAuthorizerTokenCache);
        checkNotNull(codeName);
        checkNotNull(corpId);
        return get(corpAuthorizerTokenCache, Pair.of(codeName, corpId));
    }

    @AllArgsConstructor
    private abstract static class QueryCacheLoader<K, V> extends CacheLoader<K, V> {
        protected final VarysConfig varysConfig;

        protected String httpGet(String subpath) {
            val path = checkNotNull(varysConfig.address()) + subpath;
            return checkNotNull(new HttpReq(path).get());
        }
    }
}
