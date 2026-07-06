package tests.petstore.tests.api.store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.rest.models.error.ErrorResponseModel;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("api-petstore")
public class PlaceOrderTest extends BaseTest {

    @Test
    @DisplayName("Проверка размещения заказа")
    void successfulPlaceOrderTest() {
        petSteps.createPet();
        storeSteps.placeOrder(petSteps.getPetResponse().getId().intValue());

        step("Проверки", () -> {
            assertThat(storeSteps.getOrderResponse().getPetId())
                    .isEqualTo(storeSteps.getOrderRequest().getPetId());
        });
    }

    @Test
    @DisplayName("Проверка размещения заказа с невалидным телом запроса")
    void failedPlaceOrderWithInvalidBodyTest() {
        ErrorResponseModel error = storeSteps.placeOrderWithInvalidBody("invalid-json");

        step("Проверки", () -> {
            assertThat(error.getMessage()).contains("Input error");
        });
    }
}
