package tests.petstore.tests.api.steps;

import io.qameta.allure.Step;
import lombok.Getter;
import tests.petstore.rest.api.ApiClient;
import tests.petstore.rest.models.user.request.CreateUserRequestModel;
import tests.petstore.rest.models.user.response.UserResponseModel;

import static tests.petstore.rest.data.TestData.newUser;

@Getter
public class UserSteps {
    private final ApiClient api;
    private CreateUserRequestModel userRequest;
    private UserResponseModel userResponse;

    public UserSteps(ApiClient api) {
        this.api = api;
    }

    @Step("Создание пользователя")
    public void createUser() {
        userRequest = newUser();
        userResponse = api.user.createUser(userRequest);
    }
}
