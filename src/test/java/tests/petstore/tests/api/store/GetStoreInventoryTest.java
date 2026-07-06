package tests.petstore.tests.api.store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.petstore.rest.models.error.ErrorResponseModel;
import tests.petstore.rest.models.store.response.StoreInventoryResponseModel;
import tests.petstore.tests.api.BaseTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("api-petstore")
public class GetStoreInventoryTest extends BaseTest {

    @Test
    @DisplayName("Проверка получения инвентаря магазина")
    void successfulGetStoreInventoryTest() {
        StoreInventoryResponseModel inventory = storeSteps.getInventory();

        step("Проверки", () -> {
            assertThat(inventory.getPlaced()).isPositive();
        });
    }

    @Test
    @DisplayName("Проверка получения инвентаря с неверным HTTP-методом")
    void failedGetStoreInventoryWithInvalidMethodTest() {
        ErrorResponseModel error = storeSteps.getInventoryWithInvalidMethod();

        step("Проверки", () -> {
            assertThat(error.getCode()).isEqualTo(405);
        });
    }
}
