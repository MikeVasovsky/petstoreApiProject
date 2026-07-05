package tests.petstore.tests.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import tests.petstore.config.ApiTestConfig;
import tests.petstore.rest.api.ApiClient;
import tests.petstore.rest.models.user.request.CreateUserRequestModel;
import tests.petstore.rest.models.user.response.UserResponseModel;

import static tests.petstore.rest.data.TestData.newUser;

public class BaseTest {
    protected static final ApiClient api = new ApiClient();
    protected CreateUserRequestModel request;
    protected UserResponseModel response;

    @BeforeAll
    static void setUp() {
        ApiTestConfig config = ConfigFactory.create(ApiTestConfig.class, System.getProperties());
        RestAssured.baseURI = config.getBaseUri();
        RestAssured.basePath = config.getBasePath();
    }

    @Step("Регистрация пользователя")
    public void registrationUser() {
        request = newUser();
        response = api.user.createUser(request);
    }
}
