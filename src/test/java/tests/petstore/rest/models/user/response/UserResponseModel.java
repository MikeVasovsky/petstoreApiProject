package tests.petstore.rest.models.user.response;

import lombok.Data;

@Data
public class UserResponseModel {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Integer userStatus;
}
