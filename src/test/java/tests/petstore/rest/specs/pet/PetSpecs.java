package tests.petstore.rest.specs.pet;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.notNullValue;
import static tests.petstore.rest.specs.BaseSpec.baseRequestSpec;

public class PetSpecs {

    public static RequestSpecification createPetRequestSpec = baseRequestSpec;

    public static ResponseSpecification successfulCreatePetResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath(
                    "petstore/schemas/pet/create_pet_response_schema.json"))
            .expectBody("name", notNullValue())
            .build();
}
