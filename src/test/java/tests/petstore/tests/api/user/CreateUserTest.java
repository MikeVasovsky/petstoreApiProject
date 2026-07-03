package tests.petstore.tests.api.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.rest.models.user.request.CreateUserRequestModel;
import tests.petstore.rest.models.user.response.UserResponseModel;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.petstore.rest.data.TestData.newUser;

@Tag("api-petstore")
public class CreateUserTest extends BaseTest {

    @Test
    @DisplayName("Проверка успешного создания пользователя")
    void successfulCreateUserTest() {
        CreateUserRequestModel request = newUser();
        UserResponseModel response = api.user.createUser(request);

        step("Проверки", () -> {
            assertThat(response.getUsername()).isEqualTo(request.getUsername());
            assertThat(response.getFirstName()).isEqualTo(request.getFirstName());
            assertThat(response.getLastName()).isEqualTo(request.getLastName());
            assertThat(response.getEmail()).isEqualTo(request.getEmail());
            assertThat(response.getPhone()).isEqualTo(request.getPhone());
            assertThat(response.getUserStatus()).isEqualTo(request.getUserStatus());
        });
    }
}
