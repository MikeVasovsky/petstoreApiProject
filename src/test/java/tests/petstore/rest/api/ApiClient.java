package tests.petstore.rest.api;

public class ApiClient {
    public final UserApiClient user = new UserApiClient();
    public final PetApiClient pet = new PetApiClient();
}
