package com.socks.api;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface ProjectConfig extends Config {

    @DefaultValue("test")
    String env();

    @Key("test.baseUrl")
    String baseUrl();

    @DefaultValue("de")
    String locale();

    boolean logging();
}
