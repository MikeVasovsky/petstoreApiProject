package tests.petstore.tests.api.login;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.rest.models.error.ErrorResponseModel;
import tests.petstore.rest.models.login.response.LoginResponseModel;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("api-petstore")
public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Проверка успешной авторизации пользователя")
    void successfulLoginTest() {
        registrationUser();

        LoginResponseModel response = api.user.login(request.getUsername(), request.getPassword());

        step("Проверки", () -> {
            assertThat(response.getMessage()).contains("Logged in user session:");
            assertThat(response.getSessionId()).isNotBlank();
        });
    }

    @Test
    @DisplayName("Проверка авторизации с неверным HTTP-методом")
    void failedLoginWithInvalidMethodTest() {
        ErrorResponseModel error = api.user.loginWithInvalidMethod();

        step("Проверки", () -> {
            assertThat(error.getCode()).isEqualTo(405);
            assertThat(error.getMessage()).contains("Method Not Allowed");
        });
    }
}
