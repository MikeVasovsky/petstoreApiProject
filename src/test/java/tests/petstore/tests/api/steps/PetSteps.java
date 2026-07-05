package tests.petstore.tests.api.steps;

import io.qameta.allure.Step;
import lombok.Getter;
import tests.petstore.rest.api.ApiClient;
import tests.petstore.rest.models.pet.request.CreatePetRequestModel;
import tests.petstore.rest.models.pet.response.PetResponseModel;

import static tests.petstore.rest.data.TestData.newPet;

@Getter

public class PetSteps {
    private final ApiClient api;
    private CreatePetRequestModel petRequest;
    private PetResponseModel petResponse;

    public PetSteps(ApiClient api) {
        this.api = api;
    }

    @Step("Создание питомца")
    public void createPet() {
        petRequest = newPet();
        petResponse = api.pet.createPet(petRequest);
    }
}
