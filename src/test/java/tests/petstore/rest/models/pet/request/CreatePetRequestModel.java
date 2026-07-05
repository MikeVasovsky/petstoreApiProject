package tests.petstore.rest.models.pet.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePetRequestModel {
    private Long id;
    private String name;
    private List<String> photoUrls;
    private String status;
}
