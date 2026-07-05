package tests.petstore.rest.models.error;

import lombok.Data;

@Data
public class ErrorResponseModel {
    private Integer code;
    private String message;
}
