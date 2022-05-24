package petStore.requests;

import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import petStore.pojo.Pet;
import petStore.utils.PropertiesFactory;

import static io.restassured.RestAssured.given;

public class PetRequest {

    private final Pet pet;

    public PetRequest(Pet pet) {
        this.pet = pet;
    }

    public Pet addPetRequest2() throws Exception {

        RestAssured.baseURI = PropertiesFactory.getProperty("petStore.pet");
        Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(pet)
                .when()
                .post()
                .then()
                .extract().response();

        Pet pet = new Pet();

//        ResponseBody body = response.getBody();

        // if the response status is 200 set the response parameters to objects, else set the response message
        if(response.statusCode() == 200){
            pet = response.getBody().as(Pet.class);
        }else{
            pet.setResponseMessage(response.statusLine());
        }
        pet.setHttpStatusCode(response.statusCode());
        return pet;
    }

    public Pet addPetRequest() throws Exception {
        //set a max wait time before the request
        Unirest.setTimeouts(180000, 180000);
        HttpResponse<String> response = Unirest.post(PropertiesFactory.getProperty("petStore.pet"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(new GsonBuilder().create().toJson(pet))
                .asString();

        String json = response.getBody();
        //set the default timeout after the request
        Unirest.setTimeouts(10000, 60000);
        Pet pet = new Pet();

        // if the response status is 200 set the response parameters to objects, else set the response message
        if(response.getStatus() == 200){
            pet = new GsonBuilder().create().fromJson(json, Pet.class);
        }else{
            pet.setResponseMessage(json);
        }
        pet.setHttpStatusCode(response.getStatus());
        return pet;
    }

    public Pet updatePetRequest() throws Exception {
        //set a max wait time before the request
        Unirest.setTimeouts(180000, 180000);
        HttpResponse<String> response = Unirest.put(PropertiesFactory.getProperty("petStore.pet"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(new GsonBuilder().create().toJson(pet))
                .asString();

        String json = response.getBody();
        //set the default timeout after the request
        Unirest.setTimeouts(10000, 60000);
        Pet pet = new Pet();

        // if the response status is 200 set the response parameters to objects, else set the response message
        if(response.getStatus() == 200){
            pet = new GsonBuilder().create().fromJson(json, Pet.class);
        }else{
            pet.setResponseMessage(json);
        }
        pet.setHttpStatusCode(response.getStatus());
        return pet;
    }

    public Pet updatePetRequest2() throws Exception {

        RestAssured.baseURI = PropertiesFactory.getProperty("petStore.pet");
        Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(pet)
                .when()
                .put()
                .then()
                .extract().response();

        Pet pet = new Pet();

//        ResponseBody body = response.getBody();

        // if the response status is 200 set the response parameters to objects, else set the response message
        if(response.statusCode() == 200){
            pet = response.getBody().as(Pet.class);
        }else{
            pet.setResponseMessage(response.statusLine());
        }
        pet.setHttpStatusCode(response.statusCode());
        return pet;
    }
}
