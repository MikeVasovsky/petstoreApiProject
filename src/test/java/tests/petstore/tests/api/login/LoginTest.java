package tests.petstore.tests.api.login;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.rest.models.error.ErrorResponseModel;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("api-petstore")
public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Проверка успешной авторизации пользователя")
    void successfulLoginTest() {
        userSteps.createUser();
        userSteps.login();

        step("Проверки", () -> {
            assertThat(userSteps.getLoginResponse().getMessage()).contains("Logged in user session:");
            assertThat(userSteps.getLoginResponse().getSessionId()).isNotBlank();
        });
    }

    @Test
    @DisplayName("Проверка авторизации с неверным HTTP-методом")
    void failedLoginWithInvalidMethodTest() {
        ErrorResponseModel error = userSteps.loginWithInvalidMethod();

        step("Проверки", () -> {
            assertThat(error.getCode()).isEqualTo(405);
            assertThat(error.getMessage()).contains("Method Not Allowed");
        });
    }
}
