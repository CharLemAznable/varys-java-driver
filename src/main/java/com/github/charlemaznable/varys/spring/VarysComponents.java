package com.github.charlemaznable.varys.spring;

import com.github.charlemaznable.core.spring.ElvesImport;
import com.github.charlemaznable.core.spring.NeoComponentScan;
import com.github.charlemaznable.httpclient.ohclient.OhScan;
import com.github.charlemaznable.varys.impl.Query;

@ElvesImport
@NeoComponentScan(basePackageClasses = Query.class)
@OhScan(basePackageClasses = Query.class)
public final class VarysComponents {
}
