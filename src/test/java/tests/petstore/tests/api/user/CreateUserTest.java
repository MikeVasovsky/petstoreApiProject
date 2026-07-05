package tests.petstore.tests.api.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("api-petstore")
public class CreateUserTest extends BaseTest {

    @Test
    @DisplayName("Проверка успешного создания пользователя")
    void successfulCreateUserTest() {
        registrationUser();
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
