package com.github.charlemaznable.varys.mock;

import java.util.concurrent.ConcurrentHashMap;

public class MockVarysServer {

    static ConcurrentHashMap<String/* path */, String/* response */>
            mocks = new ConcurrentHashMap<>();
    static volatile boolean testMode = false;

    public static void setUpMockServer() {
        testMode = true;
    }

    public static void tearDownMockServer() {
        mocks.clear();
        testMode = false;
    }

    public static boolean isTestMode() {
        return testMode;
    }

    public static String getVarysResponse(String path) {
        return mocks.get(path);
    }

    public static void setVarysResponse(String path, String response) {
        mocks.put(path, response);
    }
}
