package tests.petstore.tests.api.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.rest.models.user.response.UserResponseModel;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("api-petstore")
public class GetUserTest extends BaseTest {

    @Test
    @DisplayName("Проверка получения пользователя по имени")
    void successfulGetUserByUsernameTest() {
        registrationUser();
        UserResponseModel result = api.user.getCorrectUserByUsername(response.getUsername());
        step("Проверки", () -> {
            assertThat(result.getUsername()).isEqualTo(request.getUsername());
            assertThat(result.getFirstName()).isEqualTo(request.getFirstName());
            assertThat(result.getLastName()).isEqualTo(request.getLastName());
            assertThat(result.getEmail()).isEqualTo(request.getEmail());
            assertThat(result.getPhone()).isEqualTo(request.getPhone());
            assertThat(result.getUserStatus()).isEqualTo(request.getUserStatus());
        });
    }
}
