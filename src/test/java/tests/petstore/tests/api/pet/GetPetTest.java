package tests.petstore.tests.api.pet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.rest.models.pet.response.PetNotFoundResponseModel;
import tests.petstore.rest.models.pet.response.PetResponseModel;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.petstore.rest.data.TestData.randomPetId;

@Tag("api-petstore")
public class GetPetTest extends BaseTest {

    @Test
    @DisplayName("Получение питомца по id")
    void getPetTest() {
        petSteps.createPet();
        PetResponseModel result = petSteps.getPet();

        step("Проверки", () -> {
            assertThat(result.getId()).isEqualTo(petSteps.getPetResponse().getId());
            assertThat(result.getName()).isEqualTo(petSteps.getPetRequest().getName());
            assertThat(result.getPhotoUrls()).isEqualTo(petSteps.getPetRequest().getPhotoUrls());
            assertThat(result.getStatus()).isEqualTo(petSteps.getPetRequest().getStatus());
        });
    }

    @Test
    @DisplayName("Проверка получения несуществующего питомца")
    void failedGetPetNotFoundTest() {
        PetNotFoundResponseModel result = petSteps.getPetNotFound(randomPetId());

        step("Проверки", () -> {
            assertThat(result.getMessage()).contains("Pet not found");
        });
    }
}
