package tests.petstore.rest.specs.user;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.notNullValue;
import static tests.petstore.rest.specs.BaseSpec.baseRequestSpec;

public class UserSpecs {

    public static RequestSpecification createUserRequestSpec = baseRequestSpec;

    public static ResponseSpecification successfulCreateUserResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath(
                    "petstore/schemas/user/create_user_response_schema.json"))
            .expectBody("username", notNullValue())
            .build();

    public static ResponseSpecification successfulDeleteUserResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification successfulSearchNotFoundUserResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(404)
            .build();
}
