package tests.petstore.tests.api.pet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.rest.models.pet.response.PetNotFoundResponseModel;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.petstore.rest.data.TestData.randomPetId;

@Tag("api-petstore")
class DeletePetTest extends BaseTest {

    @Test
    @DisplayName("Проверка успешного удаления питомца")
    void successfulDeletePetTest() {
        petSteps.createPet();
        Long createdPetId = petSteps.getPetResponse().getId();

        int statusCode = petSteps.deletePet();
        PetNotFoundResponseModel notFoundResponse = petSteps.getPetNotFound();

        step("Проверки", () -> {
            assertEquals(200, statusCode);
            assertThat(notFoundResponse.getMessage()).contains("Pet not found");
            assertThat(petSteps.getPetResponse().getId()).isEqualTo(createdPetId);
        });
    }

    @Test
    @DisplayName("Проверка удаления несуществующего питомца")
    void failedDeleteNonExistentPetTest() {
        long petId = randomPetId();
        petSteps.deletePet(petId);
        PetNotFoundResponseModel response = petSteps.getPetNotFound(petId);

        step("Проверки", () -> {
            assertThat(response.getMessage()).contains("Pet not found");
        });
    }
}
