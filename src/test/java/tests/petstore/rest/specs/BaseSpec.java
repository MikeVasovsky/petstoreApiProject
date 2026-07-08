package tests.petstore.rest.specs;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static tests.allure.CustomAllureListener.withCustomTemplate;

public class BaseSpec {

    public static RequestSpecification baseRequestSpec = with()
            .filter(withCustomTemplate())
            .log()
            .all()
            .contentType(JSON);
}
