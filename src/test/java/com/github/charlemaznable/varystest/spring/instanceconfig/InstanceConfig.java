package com.github.charlemaznable.varystest.spring.instanceconfig;

import com.github.charlemaznable.varys.config.VarysConfig;
import org.springframework.stereotype.Component;

@Component
public class InstanceConfig implements VarysConfig {

    @Override
    public String address() {
        return "http://127.0.0.1:4236/varys";
    }

    @Override
    public long callTimeout() {
        return 0;
    }

    @Override
    public long connectTimeout() {
        return 10_000;
    }

    @Override
    public long readTimeout() {
        return 10_000;
    }

    @Override
    public long writeTimeout() {
        return 10_000;
    }
}
