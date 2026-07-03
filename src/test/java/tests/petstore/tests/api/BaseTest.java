package tests.petstore.tests.api;

import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import tests.petstore.config.ApiTestConfig;
import tests.petstore.rest.api.ApiClient;
import tests.petstore.rest.data.TestData;

public class BaseTest {
    protected static final ApiClient api = new ApiClient();
    protected final TestData testData = new TestData();

    @BeforeAll
    static void setUp() {
        ApiTestConfig config = ConfigFactory.create(ApiTestConfig.class, System.getProperties());
        RestAssured.baseURI = config.getBaseUri();
        RestAssured.basePath = config.getBasePath();
    }
}
