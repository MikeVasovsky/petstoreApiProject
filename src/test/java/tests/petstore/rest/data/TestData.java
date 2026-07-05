package tests.petstore.rest.data;

import com.github.javafaker.Faker;
import lombok.Data;
import tests.petstore.rest.models.pet.request.CreatePetRequestModel;
import tests.petstore.rest.models.user.request.CreateUserRequestModel;

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

    public static CreatePetRequestModel newPet() {
        return CreatePetRequestModel.builder()
                .id(0L)
                .name(randomPetName())
                .photoUrls(List.of(FAKER.internet().url()))
                .status("available")
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
