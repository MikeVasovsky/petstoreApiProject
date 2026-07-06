package tests.petstore.tests.api.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.rest.models.user.response.UserNotFounfResponseModel;
import tests.petstore.rest.models.user.response.UserResponseModel;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.petstore.rest.data.TestData.randomUsername;

@Tag("api-petstore")
public class GetUserTest extends BaseTest {

    @Test
    @DisplayName("Проверка получения пользователя по имени")
    void successfulGetUserByUsernameTest() {
        userSteps.createUser();
        UserResponseModel result = userSteps.getUser();

        step("Проверки", () -> {
            assertThat(result.getUsername()).isEqualTo(userSteps.getUserRequest().getUsername());
        });
    }

    @Test
    @DisplayName("Проверка получения несуществующего пользователя")
    void failedGetUserByUsernameNotFoundTest() {
        UserNotFounfResponseModel result = userSteps.getUserNotFound(randomUsername());

        step("Проверки", () -> {
            assertThat(result.getMessage()).contains("User not found");
        });
    }
}
