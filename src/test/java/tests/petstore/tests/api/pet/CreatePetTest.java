package tests.petstore.tests.api.pet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.rest.models.error.ErrorResponseModel;
import tests.petstore.rest.models.pet.response.PetNotFoundResponseModel;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.petstore.rest.data.TestData.newStatus;
import static tests.petstore.rest.data.TestData.randomPetId;
import static tests.petstore.rest.data.TestData.randomPetName;

@Tag("api-petstore")
public class CreatePetTest extends BaseTest {

    @Test
    @DisplayName("Проверка успешного создания питомца")
    void successfulCreatePetTest() {
        petSteps.createPet();

        step("Проверки", () -> {
            assertThat(petSteps.getPetResponse().getName()).isEqualTo(petSteps.getPetRequest().getName());
            assertThat(petSteps.getPetResponse().getPhotoUrls()).isEqualTo(petSteps.getPetRequest().getPhotoUrls());
            assertThat(petSteps.getPetResponse().getStatus()).isEqualTo(petSteps.getPetRequest().getStatus());
        });
    }

    @Test
    @DisplayName("Обновить данные питомца")
    void successfulUpdatePetTest() {
        petSteps.createPet();

        String updatedName = randomPetName();
        String updatedStatus = newStatus();
        if (updatedStatus.equals(petSteps.getPetRequest().getStatus())) {
            updatedStatus = "sold";
        }
        final String expectedStatus = updatedStatus;

        petSteps.updatePet(updatedName, expectedStatus);

        step("Проверки", () -> {
            assertThat(petSteps.getUpdatedPetResponse().getName()).isEqualTo(updatedName);
            assertThat(petSteps.getUpdatedPetResponse().getStatus()).isEqualTo(expectedStatus);

            assertThat(petSteps.getPetRequest().getName()).isEqualTo(petSteps.getPetResponse().getName());
            assertThat(petSteps.getPetRequest().getStatus()).isEqualTo(petSteps.getPetResponse().getStatus());

            assertThat(petSteps.getUpdatedPetResponse().getName()).isNotEqualTo(petSteps.getPetRequest().getName());
            assertThat(petSteps.getUpdatedPetResponse().getStatus()).isNotEqualTo(petSteps.getPetRequest().getStatus());
        });
    }

    @Test
    @DisplayName("Проверка создания питомца с невалидным телом запроса")
    void failedCreatePetWithInvalidBodyTest() {
        ErrorResponseModel error = petSteps.createPetWithInvalidBody("invalid-json");

        step("Проверки", () -> {
            assertThat(error.getCode()).isEqualTo(400);
            assertThat(error.getMessage()).contains("Input error");
        });
    }

    @Test
    @DisplayName("Проверка обновления несуществующего питомца")
    void failedUpdateNonExistentPetTest() {
        PetNotFoundResponseModel result = petSteps.updatePetNotFound(
                randomPetId(), randomPetName(), "sold");

        step("Проверки", () -> {
            assertThat(result.getMessage()).contains("Pet not found");
        });
    }
}
