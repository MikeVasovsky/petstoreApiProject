package tests.petstore.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:petstore/${env}.properties",
})
public interface ApiTestConfig extends Config {

    @Key("env")
    @DefaultValue("local")
    String getEnv();

    @Key("base.uri")
    @DefaultValue("http://localhost:8080")
    String getBaseUri();

    @Key("base.path")
    @DefaultValue("/api/v3")
    String getBasePath();
}
