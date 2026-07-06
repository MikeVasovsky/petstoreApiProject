package tests.petstore.tests.api.pet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.rest.models.pet.response.PetNotFoundResponseModel;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.petstore.rest.data.TestData.randomId;

@Tag("api-petstore")
class DeletePetTest extends BaseTest {

    @Test
    @DisplayName("Проверка успешного удаления питомца")
    void successfulDeletePetTest() {
        petSteps.createPet();
        petSteps.deletePet();
        PetNotFoundResponseModel notFoundResponse = petSteps.getPetNotFound();

        step("Проверки", () -> {
            assertThat(notFoundResponse.getMessage()).contains("Pet not found");
        });
    }

    @Test
    @DisplayName("Проверка удаления несуществующего питомца")
    void failedDeleteNonExistentPetTest() {
        int id = randomId();
        petSteps.deletePet(id);
        PetNotFoundResponseModel response = petSteps.getPetNotFound(id);

        step("Проверки", () -> {
            assertThat(response.getMessage()).contains("Pet not found");
        });
    }
}
