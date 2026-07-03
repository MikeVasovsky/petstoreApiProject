package tests.petstore.tests.api.login;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.rest.models.login.response.LoginResponseModel;
import tests.petstore.rest.models.user.request.CreateUserRequestModel;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.petstore.rest.data.TestData.newUser;

@Tag("api-petstore")
public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Проверка успешной авторизации пользователя")
    void successfulLoginTest() {
        CreateUserRequestModel user = newUser();
        api.user.createUser(user);

        LoginResponseModel response = api.user.login(user.getUsername(), user.getPassword());

        step("Проверки", () -> {
            assertThat(response.getMessage()).contains("Logged in user session:");
            assertThat(response.getSessionId()).isNotBlank();
        });
    }
}
