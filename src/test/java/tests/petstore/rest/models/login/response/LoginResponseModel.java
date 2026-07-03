package tests.petstore.rest.models.login.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseModel {
    private String message;
    private String sessionId;

    public static LoginResponseModel fromMessage(String message) {
        String prefix = "Logged in user session: ";
        String sessionId = message.startsWith(prefix)
                ? message.substring(prefix.length()).trim()
                : message;
        return new LoginResponseModel(message, sessionId);
    }
}
