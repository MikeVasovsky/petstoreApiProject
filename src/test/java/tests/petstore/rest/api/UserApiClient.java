package tests.petstore.rest.api;

import io.qameta.allure.Step;
import tests.petstore.rest.models.login.response.LoginResponseModel;
import tests.petstore.rest.models.user.request.CreateUserRequestModel;
import tests.petstore.rest.models.user.response.UserResponseModel;

import static io.restassured.RestAssured.given;
import static tests.petstore.rest.specs.login.LoginSpecs.loginRequestSpec;
import static tests.petstore.rest.specs.login.LoginSpecs.successfulLoginResponseSpec;
import static tests.petstore.rest.specs.user.UserSpecs.createUserRequestSpec;
import static tests.petstore.rest.specs.user.UserSpecs.successfulCreateUserResponseSpec;

public class UserApiClient {

    @Step("Создание пользователя")
    public UserResponseModel createUser(CreateUserRequestModel user) {
        return given(createUserRequestSpec)
                .body(user)
                .when()
                .post("/user")
                .then()
                .spec(successfulCreateUserResponseSpec)
                .extract()
                .as(UserResponseModel.class);
    }

    @Step("Авторизация пользователя")
    public LoginResponseModel login(String username, String password) {
        String message = given(loginRequestSpec)
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get("/user/login")
                .then()
                .spec(successfulLoginResponseSpec)
                .extract()
                .asString();

        return LoginResponseModel.fromMessage(message);
    }
}
