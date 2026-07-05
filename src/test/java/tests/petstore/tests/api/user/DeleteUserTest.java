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
    void successfulCreateUserTest() {
        registrationUser();
        api.user.login(request.getUsername(), request.getPassword());
        api.user.deleteUser(request.getUsername());
        UserNotFounfResponseModel response = api.user.getUserByUsername(request.getUsername());

        step("Проверки", () -> {
            assertThat(response.getMessage()).contains("User not found");
        });
    }

    @Test
    @DisplayName("Проверка удаления несуществующего пользователя")
    void failedDeleteNonExistentUserTest() {
        String username = randomUsername();
        api.user.deleteUser(username);
        UserNotFounfResponseModel response = api.user.getUserByUsername(username);

        step("Проверки", () -> {
            assertThat(response.getMessage()).contains("User not found");
        });
    }

}
