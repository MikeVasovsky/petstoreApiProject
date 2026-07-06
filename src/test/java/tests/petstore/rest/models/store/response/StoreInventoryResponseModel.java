package tests.petstore.rest.models.store.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreInventoryResponseModel {
    private Integer approved;
    private Integer placed;
    private Integer delivered;
}
