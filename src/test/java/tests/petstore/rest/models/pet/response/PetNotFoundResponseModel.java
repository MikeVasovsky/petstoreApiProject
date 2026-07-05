package tests.petstore.rest.models.pet.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetNotFoundResponseModel {
    private String message;
    private String response;

    public static PetNotFoundResponseModel fromMessage(String message) {
        String prefix = "can't parse JSON.  Raw result:";
        String response = message.startsWith(prefix)
                ? message.substring(prefix.length()).trim()
                : message;
        return new PetNotFoundResponseModel(message, response);
    }
}
