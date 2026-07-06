package tests.petstore.rest.data;

import com.github.javafaker.Faker;
import lombok.Data;
import tests.petstore.rest.models.pet.request.CreatePetRequestModel;
import tests.petstore.rest.models.store.request.CreateOrderRequestModel;
import tests.petstore.rest.models.user.request.CreateUserRequestModel;

import java.time.Instant;
import java.util.List;

@Data
public class TestData {
    public static final Faker FAKER = new Faker();

    public static String randomUsername() {
        return FAKER.name().username();
    }

    public static String randomPassword() {
        return FAKER.internet().password(8, 16);
    }

    public static String randomPetName() {
        return FAKER.animal().name();
    }

    public static long randomPetId() {
        return FAKER.number().numberBetween(900_000_000, 999_999_999);
    }

    public static CreatePetRequestModel newPet() {
        return CreatePetRequestModel.builder()
                .id(0L)
                .name(randomPetName())
                .photoUrls(List.of(FAKER.internet().url()))
                .status("available")
                .build();
    }
    public static String newStatus() {
        return FAKER.options().option("available", "pending", "sold");
    }

    public static CreateOrderRequestModel newOrder(Long petId) {
        return CreateOrderRequestModel.builder()
                .id(0L)
                .petId(petId)
                .quantity(FAKER.number().numberBetween(1, 10))
                .shipDate(Instant.now().toString())
                .status("placed")
                .complete(false)
                .build();
    }

    public static CreateUserRequestModel newUser() {
        return CreateUserRequestModel.builder()
                .id(0L)
                .username(randomUsername())
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .email(FAKER.internet().emailAddress())
                .password(randomPassword())
                .phone(FAKER.phoneNumber().subscriberNumber(10))
                .userStatus(1)
                .build();
    }
}
