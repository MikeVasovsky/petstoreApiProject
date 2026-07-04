package tests.petstore.tests.api.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.rest.models.user.request.CreateUserRequestModel;
import tests.petstore.rest.models.user.response.UserNotFounfResponseModel;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.petstore.rest.data.TestData.newUser;

@Tag("api-petstore")
class DeleteUserTest extends BaseTest {

    @Test
    @DisplayName("Проверка успешного удаления пользователя")
    void successfulCreateUserTest() {
        CreateUserRequestModel user = newUser();
        api.user.createUser(user);

        api.user.login(user.getUsername(), user.getPassword());

        int resultStatus = api.user.deleteUser(user.getUsername());
        assertEquals(200,resultStatus);

        UserNotFounfResponseModel response = api.user.getUserByUsername(user.getUsername());

        step("Проверки", () -> {
            assertThat(response.getMessage()).contains("User not found");
        });
    }

}
