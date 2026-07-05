package tests.petstore.tests.api.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.rest.models.error.ErrorResponseModel;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("api-petstore")
public class CreateUserTest extends BaseTest {

    @Test
    @DisplayName("Проверка успешного создания пользователя")
    void successfulCreateUserTest() {
        userSteps.createUser();
        step("Проверки", () -> {
            assertThat(userSteps.getUserResponse().getUsername()).isEqualTo(userSteps.getUserRequest().getUsername());
            assertThat(userSteps.getUserResponse().getFirstName()).isEqualTo(userSteps.getUserRequest().getFirstName());
            assertThat(userSteps.getUserResponse().getLastName()).isEqualTo(userSteps.getUserRequest().getLastName());
            assertThat(userSteps.getUserResponse().getEmail()).isEqualTo(userSteps.getUserRequest().getEmail());
            assertThat(userSteps.getUserResponse().getPhone()).isEqualTo(userSteps.getUserRequest().getPhone());
            assertThat(userSteps.getUserResponse().getUserStatus()).isEqualTo(userSteps.getUserRequest().getUserStatus());
        });
    }

    @Test
    @DisplayName("Проверка создания пользователя с невалидным телом запроса")
    void failedCreateUserWithInvalidBodyTest() {
        ErrorResponseModel error = api.user.createUserWithInvalidBody("invalid-json");

        step("Проверки", () -> {
            assertThat(error.getCode()).isEqualTo(400);
            assertThat(error.getMessage()).contains("Input error");
        });
    }
}
