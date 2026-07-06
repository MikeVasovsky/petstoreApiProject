package tests.petstore.tests.api;

import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import tests.petstore.config.ApiTestConfig;
import tests.petstore.rest.api.ApiClient;
import tests.petstore.tests.api.steps.PetSteps;
import tests.petstore.tests.api.steps.StoreSteps;
import tests.petstore.tests.api.steps.UserSteps;

public class BaseTest {
    protected static final ApiClient api = new ApiClient();

    protected UserSteps userSteps = new UserSteps(api);
    protected PetSteps petSteps = new PetSteps(api);
    protected StoreSteps storeSteps = new StoreSteps(api);

    @BeforeAll
    static void setUp() {
        ApiTestConfig config = ConfigFactory.create(ApiTestConfig.class, System.getProperties());
        RestAssured.baseURI = config.getBaseUri();
        RestAssured.basePath = config.getBasePath();
    }
}
