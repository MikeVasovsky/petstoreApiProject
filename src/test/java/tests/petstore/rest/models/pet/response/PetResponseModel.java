package tests.petstore.rest.models.pet.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PetResponseModel {
    private Long id;
    private String name;
    private List<String> photoUrls;
    private String status;
}
