package tests.petstore.rest.api;

import io.qameta.allure.Step;
import tests.petstore.rest.models.pet.request.CreatePetRequestModel;
import tests.petstore.rest.models.pet.response.PetResponseModel;

import static io.restassured.RestAssured.given;
import static tests.petstore.rest.specs.pet.PetSpecs.createPetRequestSpec;
import static tests.petstore.rest.specs.pet.PetSpecs.successfulCreatePetResponseSpec;

public class PetApiClient {

    @Step("Создание питомца")
    public PetResponseModel createPet(CreatePetRequestModel pet) {
        return given(createPetRequestSpec)
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .spec(successfulCreatePetResponseSpec)
                .extract()
                .as(PetResponseModel.class);
    }
}
