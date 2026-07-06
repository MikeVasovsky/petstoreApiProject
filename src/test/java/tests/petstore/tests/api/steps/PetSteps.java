package tests.petstore.tests.api.steps;

import io.qameta.allure.Step;
import lombok.Getter;
import tests.petstore.rest.api.ApiClient;
import tests.petstore.rest.models.error.ErrorResponseModel;
import tests.petstore.rest.models.pet.request.CreatePetRequestModel;
import tests.petstore.rest.models.pet.response.PetNotFoundResponseModel;
import tests.petstore.rest.models.pet.response.PetResponseModel;

import static tests.petstore.rest.data.TestData.newPet;

@Getter
public class PetSteps {
    private final ApiClient api;

    private CreatePetRequestModel petRequest;
    private PetResponseModel petResponse;

    private PetResponseModel updatedPetResponse;

    public PetSteps(ApiClient api) {
        this.api = api;
    }

    @Step("Создание питомца")
    public void createPet() {
        petRequest = newPet();
        petResponse = api.pet.createPet(petRequest);
    }

    @Step("Создание питомца с невалидным телом запроса")
    public ErrorResponseModel createPetWithInvalidBody(String invalidBody) {
        return api.pet.createPetWithInvalidBody(invalidBody);
    }

    @Step("Обновление питомца")
    public void updatePet(String name, String status) {
        updatedPetResponse = api.pet.updatePet(petResponse.getId().intValue(), name, status);
    }

    @Step("Получение питомца по id")
    public PetResponseModel getPet() {
        return api.pet.getPet(petResponse.getId().intValue());
    }

    @Step("Удаление питомца")
    public int deletePet() {
        return api.pet.deletePet(petResponse.getId().intValue());
    }

    @Step("Удаление питомца по id")
    public int deletePet(int id) {
        return api.pet.deletePet(id);
    }

    @Step("Обновление несуществующего питомца")
    public PetNotFoundResponseModel updatePetNotFound(int id, String name, String status) {
        return api.pet.updatePetNotFound(id, name, status);
    }

    @Step("Поиск удаленного или несуществующего питомца по id")
    public PetNotFoundResponseModel getPetNotFound() {
        return api.pet.getPetNotFound(petResponse.getId().intValue());
    }

    @Step("Поиск несуществующего питомца по id")
    public PetNotFoundResponseModel getPetNotFound(int id) {
        return api.pet.getPetNotFound(id);
    }
}
