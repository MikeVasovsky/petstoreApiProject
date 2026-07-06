package tests.petstore.tests.api.pet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.rest.models.error.ErrorResponseModel;
import tests.petstore.rest.models.pet.response.PetNotFoundResponseModel;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.petstore.rest.data.TestData.randomId;
import static tests.petstore.rest.data.TestData.randomPetName;

@Tag("api-petstore")
public class CreatePetTest extends BaseTest {

    @Test
    @DisplayName("Проверка успешного создания питомца")
    void successfulCreatePetTest() {
        petSteps.createPet();

        step("Проверки", () -> {
            assertThat(petSteps.getPetResponse().getName()).isEqualTo(petSteps.getPetRequest().getName());
        });
    }

    @Test
    @DisplayName("Обновить данные питомца")
    void successfulUpdatePetTest() {
        petSteps.createPet();

        String updatedName = randomPetName();
        petSteps.updatePet(updatedName, "sold");

        step("Проверки", () -> {
            assertThat(petSteps.getUpdatedPetResponse().getName()).isEqualTo(updatedName);
            assertThat(petSteps.getUpdatedPetResponse().getStatus()).isEqualTo("sold");
        });
    }

    @Test
    @DisplayName("Проверка создания питомца с невалидным телом запроса")
    void failedCreatePetWithInvalidBodyTest() {
        ErrorResponseModel error = petSteps.createPetWithInvalidBody("invalid-json");

        step("Проверки", () -> {
            assertThat(error.getMessage()).contains("Input error");
        });
    }

    @Test
    @DisplayName("Проверка обновления несуществующего питомца")
    void failedUpdateNonExistentPetTest() {
        PetNotFoundResponseModel result = petSteps.updatePetNotFound(
                randomId(), randomPetName(), "sold");

        step("Проверки", () -> {
            assertThat(result.getMessage()).contains("Pet not found");
        });
    }
}
