package tests.petstore.tests.api.steps;

import io.qameta.allure.Step;
import lombok.Getter;
import tests.petstore.rest.api.ApiClient;
import tests.petstore.rest.models.error.ErrorResponseModel;
import tests.petstore.rest.models.login.response.LoginResponseModel;
import tests.petstore.rest.models.user.request.CreateUserRequestModel;
import tests.petstore.rest.models.user.response.UserNotFounfResponseModel;
import tests.petstore.rest.models.user.response.UserResponseModel;

import static tests.petstore.rest.data.TestData.newUser;

@Getter
public class UserSteps {
    private final ApiClient api;

    private CreateUserRequestModel userRequest;
    private UserResponseModel userResponse;

    private LoginResponseModel loginResponse;

    public UserSteps(ApiClient api) {
        this.api = api;
    }

    @Step("Создание пользователя")
    public void createUser() {
        userRequest = newUser();
        userResponse = api.user.createUser(userRequest);
    }

    @Step("Создание пользователя с невалидным телом запроса")
    public ErrorResponseModel createUserWithInvalidBody(String invalidBody) {
        return api.user.createUserWithInvalidBody(invalidBody);
    }

    @Step("Авторизация пользователя")
    public LoginResponseModel login() {
        loginResponse = api.user.login(userRequest.getUsername(), userRequest.getPassword());
        return loginResponse;
    }

    @Step("Авторизация с неверным HTTP-методом")
    public ErrorResponseModel loginWithInvalidMethod() {
        return api.user.loginWithInvalidMethod();
    }

    @Step("Удаление пользователя")
    public int deleteUser() {
        return api.user.deleteUser(userResponse.getUsername());
    }

    @Step("Удаление пользователя по имени")
    public int deleteUser(String username) {
        return api.user.deleteUser(username);
    }

    @Step("Получение пользователя по имени")
    public UserResponseModel getUser() {
        return api.user.getCorrectUserByUsername(userResponse.getUsername());
    }

    @Step("Поиск удаленного или несуществующего пользователя по имени")
    public UserNotFounfResponseModel getUserNotFound() {
        return api.user.getUserByUsername(userResponse.getUsername());
    }

    @Step("Поиск несуществующего пользователя по имени")
    public UserNotFounfResponseModel getUserNotFound(String username) {
        return api.user.getUserByUsername(username);
    }
}
