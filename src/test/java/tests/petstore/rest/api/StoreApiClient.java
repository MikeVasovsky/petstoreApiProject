package tests.petstore.rest.api;

import io.qameta.allure.Step;
import tests.petstore.rest.models.error.ErrorResponseModel;
import tests.petstore.rest.models.store.request.CreateOrderRequestModel;
import tests.petstore.rest.models.store.response.OrderResponseModel;
import tests.petstore.rest.models.store.response.StoreInventoryResponseModel;

import static io.restassured.RestAssured.given;
import static tests.petstore.rest.specs.store.StoreSpecs.*;

public class StoreApiClient {

    @Step("Получение инвентаря магазина")
    public StoreInventoryResponseModel getInventory() {
        return given(storeRequestSpec)
                .get("/store/inventory")
                .then()
                .spec(successfulGetInventoryResponseSpec)
                .extract()
                .as(StoreInventoryResponseModel.class);
    }

    @Step("Размещение заказа")
    public OrderResponseModel placeOrder(CreateOrderRequestModel order) {
        return given(storeRequestSpec)
                .body(order)
                .post("/store/order")
                .then()
                .spec(successfulPlaceOrderResponseSpec)
                .extract()
                .as(OrderResponseModel.class);
    }

    @Step("Размещение заказа с невалидным телом запроса")
    public ErrorResponseModel placeOrderWithInvalidBody(String invalidBody) {
        return given(storeRequestSpec)
                .body(invalidBody)
                .post("/store/order")
                .then()
                .spec(invalidPlaceOrderResponseSpec)
                .extract()
                .as(ErrorResponseModel.class);
    }

    @Step("Получение инвентаря с неверным HTTP-методом")
    public ErrorResponseModel getInventoryWithInvalidMethod() {
        return given(storeRequestSpec)
                .post("/store/inventory")
                .then()
                .spec(invalidGetInventoryResponseSpec)
                .extract()
                .as(ErrorResponseModel.class);
    }
}
