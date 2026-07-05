package tests.petstore.rest.specs.login;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;
import static org.hamcrest.CoreMatchers.containsString;
import static tests.petstore.rest.specs.BaseSpec.baseRequestSpec;

public class LoginSpecs {

    public static RequestSpecification loginRequestSpec = baseRequestSpec;

    public static ResponseSpecification successfulLoginResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody(containsString("Logged in user session:"))
            .build();

    public static ResponseSpecification invalidLoginResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(405)
            .expectBody("message", containsString("Method Not Allowed"))
            .build();
}
