package tests.petstore.tests.api.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.rest.models.user.response.UserNotFounfResponseModel;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.petstore.rest.data.TestData.randomUsername;

@Tag("api-petstore")
class DeleteUserTest extends BaseTest {

    @Test
    @DisplayName("Проверка успешного удаления пользователя")
    void successfulDeleteUserTest() {
        userSteps.createUser();
        userSteps.login();
        userSteps.deleteUser();
        UserNotFounfResponseModel notFoundResponse = userSteps.getUserNotFound();

        step("Проверки", () -> {
            assertThat(notFoundResponse.getMessage()).contains("User not found");
        });
    }

    @Test
    @DisplayName("Проверка удаления несуществующего пользователя")
    void failedDeleteNonExistentUserTest() {
        String username = randomUsername();
        userSteps.deleteUser(username);
        UserNotFounfResponseModel response = userSteps.getUserNotFound(username);

        step("Проверки", () -> {
            assertThat(response.getMessage()).contains("User not found");
        });
    }
}
