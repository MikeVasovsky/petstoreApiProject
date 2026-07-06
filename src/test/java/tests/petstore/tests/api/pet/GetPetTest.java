package tests.petstore.tests.api.pet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.rest.models.pet.response.PetNotFoundResponseModel;
import tests.petstore.rest.models.pet.response.PetResponseModel;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.petstore.rest.data.TestData.randomId;

@Tag("api-petstore")
public class GetPetTest extends BaseTest {

    @Test
    @DisplayName("Получение питомца по id")
    void getPetTest() {
        petSteps.createPet();
        PetResponseModel result = petSteps.getPet();

        step("Проверки", () -> {
            assertThat(result.getName()).isEqualTo(petSteps.getPetRequest().getName());
        });
    }

    @Test
    @DisplayName("Проверка получения несуществующего питомца")
    void failedGetPetNotFoundTest() {
        PetNotFoundResponseModel result = petSteps.getPetNotFound(randomId());

        step("Проверки", () -> {
            assertThat(result.getMessage()).contains("Pet not found");
        });
    }
}
