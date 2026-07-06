package tests.petstore.rest.specs.store;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static tests.petstore.rest.specs.BaseSpec.baseRequestSpec;

public class StoreSpecs {

    public static RequestSpecification storeRequestSpec = baseRequestSpec;

    public static ResponseSpecification successfulGetInventoryResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath(
                    "petstore/schemas/store/store_inventory_response_schema.json"))
            .expectBody(notNullValue())
            .build();

    public static ResponseSpecification successfulPlaceOrderResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath(
                    "petstore/schemas/store/place_order_response_schema.json"))
            .expectBody("petId", notNullValue())
            .build();

    public static ResponseSpecification invalidPlaceOrderResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(400)
            .expectBody("message", containsString("Input error"))
            .build();

    public static ResponseSpecification invalidGetInventoryResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(405)
            .expectBody("message", containsString("Method Not Allowed"))
            .build();
}
