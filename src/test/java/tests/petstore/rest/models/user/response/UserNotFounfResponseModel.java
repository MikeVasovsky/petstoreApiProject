package tests.petstore.rest.models.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNotFounfResponseModel {
    private String message;
    private String response;

    public static UserNotFounfResponseModel fromMessage(String message) {
        String prefix = "can't parse JSON.  Raw result:";
        String response = message.startsWith(prefix)
                ? message.substring(prefix.length()).trim()
                : message;
        return new UserNotFounfResponseModel(message, response);
    }
}
