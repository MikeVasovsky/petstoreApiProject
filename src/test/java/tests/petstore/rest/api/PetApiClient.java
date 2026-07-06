package tests.petstore.rest.api;

import io.qameta.allure.Step;
import tests.petstore.rest.models.error.ErrorResponseModel;
import tests.petstore.rest.models.pet.request.CreatePetRequestModel;
import tests.petstore.rest.models.pet.response.PetNotFoundResponseModel;
import tests.petstore.rest.models.pet.response.PetResponseModel;

import static io.restassured.RestAssured.given;
import static tests.petstore.rest.specs.pet.PetSpecs.*;

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

    @Step("Обновить питомца")
    public PetResponseModel updatePet(int id, String name, String status) {
        return given(createPetRequestSpec)
                .queryParam("name", name)
                .queryParam("status", status)
                .when()
                .post("/pet/" + id + "/")
                .then()
                .spec(successfulCreatePetResponseSpec)
                .extract()
                .as(PetResponseModel.class);
    }

    @Step("Получить питомца по id")
    public PetResponseModel getPet(int id) {
        return given(createPetRequestSpec)
                .get("/pet/" + id)
                .then()
                .spec(successfulSearchPetById)
                .extract()
                .as(PetResponseModel.class);
    }

    @Step("Удаление питомца")
    public int deletePet(int id) {
        return given(createPetRequestSpec)
                .delete("/pet/" + id + "/")
                .then()
                .spec(successfulDeletePetResponseSpec)
                .extract()
                .statusCode();
    }

    @Step("Создание питомца с невалидным телом запроса")
    public ErrorResponseModel createPetWithInvalidBody(String invalidBody) {
        return given(createPetRequestSpec)
                .body(invalidBody)
                .post("/pet")
                .then()
                .spec(invalidCreatePetResponseSpec)
                .extract()
                .as(ErrorResponseModel.class);
    }

    @Step("Обновление несуществующего питомца")
    public PetNotFoundResponseModel updatePetNotFound(int id, String name, String status) {
        String message = given(createPetRequestSpec)
                .queryParam("name", name)
                .queryParam("status", status)
                .post("/pet/" + id + "/")
                .then()
                .spec(petNotFoundResponseSpec)
                .extract()
                .asString();
        return PetNotFoundResponseModel.fromMessage(message);
    }

    @Step("Поиск удаленного или несуществующего питомца по id")
    public PetNotFoundResponseModel getPetNotFound(int id) {
        String message = given(createPetRequestSpec)
                .get("/pet/" + id)
                .then()
                .spec(petNotFoundResponseSpec)
                .extract()
                .asString();
        return PetNotFoundResponseModel.fromMessage(message);
    }
}
