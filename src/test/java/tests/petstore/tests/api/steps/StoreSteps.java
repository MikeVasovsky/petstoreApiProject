package tests.petstore.tests.api.steps;

import io.qameta.allure.Step;
import lombok.Getter;
import tests.petstore.rest.api.ApiClient;
import tests.petstore.rest.models.error.ErrorResponseModel;
import tests.petstore.rest.models.store.request.CreateOrderRequestModel;
import tests.petstore.rest.models.store.response.OrderResponseModel;
import tests.petstore.rest.models.store.response.StoreInventoryResponseModel;

import static tests.petstore.rest.data.TestData.newOrder;

@Getter
public class StoreSteps {
    private final ApiClient api;

    private CreateOrderRequestModel orderRequest;
    private OrderResponseModel orderResponse;

    public StoreSteps(ApiClient api) {
        this.api = api;
    }

    @Step("Получение инвентаря магазина")
    public StoreInventoryResponseModel getInventory() {
        return api.store.getInventory();
    }

    @Step("Получение инвентаря с неверным HTTP-методом")
    public ErrorResponseModel getInventoryWithInvalidMethod() {
        return api.store.getInventoryWithInvalidMethod();
    }

    @Step("Размещение заказа")
    public void placeOrder(Long petId) {
        orderRequest = newOrder(petId);
        orderResponse = api.store.placeOrder(orderRequest);
    }

    @Step("Размещение заказа с невалидным телом запроса")
    public ErrorResponseModel placeOrderWithInvalidBody(String invalidBody) {
        return api.store.placeOrderWithInvalidBody(invalidBody);
    }
}
