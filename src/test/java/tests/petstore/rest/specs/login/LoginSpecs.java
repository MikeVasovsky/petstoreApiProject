package tests.petstore.rest.specs.login;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;
import static tests.petstore.rest.specs.BaseSpec.baseRequestSpec;

public class LoginSpecs {

    public static RequestSpecification loginRequestSpec = baseRequestSpec;

    public static ResponseSpecification successfulLoginResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectHeader("X-Rate-Limit", not(emptyOrNullString()))
            .expectHeader("X-Expires-After", not(emptyOrNullString()))
            .expectBody(containsString("Logged in user session:"))
            .build();
}
