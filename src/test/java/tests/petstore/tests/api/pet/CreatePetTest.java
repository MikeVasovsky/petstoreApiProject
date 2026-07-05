package tests.petstore.tests.api.pet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

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
}
