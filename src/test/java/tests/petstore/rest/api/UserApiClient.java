package tests.petstore.rest.api;

import io.qameta.allure.Step;
import tests.petstore.rest.models.login.response.LoginResponseModel;
import tests.petstore.rest.models.user.request.CreateUserRequestModel;
import tests.petstore.rest.models.user.response.UserNotFounfResponseModel;
import tests.petstore.rest.models.user.response.UserResponseModel;

import static io.restassured.RestAssured.given;
import static tests.petstore.rest.specs.login.LoginSpecs.loginRequestSpec;
import static tests.petstore.rest.specs.login.LoginSpecs.successfulLoginResponseSpec;
import static tests.petstore.rest.specs.user.UserSpecs.*;

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

    @Step("Удаление пользователя")
    public int deleteUser(String username) {
        return given(createUserRequestSpec)
                .delete("/user/"+username+"/")
                .then()
                .spec(successfulDeleteUserResponseSpec)
                .extract().statusCode();

    }

    @Step("Поиск удаленного или несуществующего пользователя по имени")
    public UserNotFounfResponseModel getUserByUsername(String username){
        String message =  given(createUserRequestSpec)
                .get("/user/"+username+"/")
                .then()
                .spec(successfulSearchNotFoundUserResponseSpec)
                .extract()
                .asString();
        return UserNotFounfResponseModel.fromMessage(message);
    }

    @Step("Поиск пользователя по имени")
    public UserResponseModel getCorrectUserByUsername(String username){
        return given(createUserRequestSpec)
                .get("/user/"+username+"/")
                .then()
                .spec(successfulSearchUserByUsername)
                .extract()
                .as(UserResponseModel.class);
    }
}
