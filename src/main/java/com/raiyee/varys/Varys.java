package com.raiyee.varys;

import com.raiyee.varys.api.Query;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Varys {

    private final Config config;

    public Query query() {
        return new Query(config);
    }

    public Query query(String codeName) {
        return new Query(config, codeName);
    }
}
