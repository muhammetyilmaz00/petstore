package petStore.requests;

import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import petStore.pojo.User;
import petStore.utils.PropertiesFactory;

public class UserRequest {

    private final User user;

    public UserRequest(User user) {
        this.user = user;
    }

    public User createUserRequest() throws Exception {
        //set a max wait time before the request
        Unirest.setTimeouts(180000, 180000);
        HttpResponse<String> response = Unirest.post(PropertiesFactory.getProperty("petStore.user"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(new GsonBuilder().create().toJson(user))
                .asString();

        String json = response.getBody();
        //set the default timeout after the request
        Unirest.setTimeouts(10000, 60000);
        User user = new User();

        // if the response status is 200 set the response parameters to objects, else set the response message
        if (response.getStatus() == 200) {
            user = new GsonBuilder().create().fromJson(json, User.class);
        } else {
            user.setResponseMessage(json);
        }
        user.setHttpStatusCode(response.getStatus());
        return user;
    }

    public User getUserByUsernameRequest() throws Exception {
        //set a max wait time before the request
        Unirest.setTimeouts(180000, 180000);
        HttpResponse<String> response = Unirest.get(PropertiesFactory.getProperty("petStore.user")+ "/" + user.getUsername())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .asString();

        String json = response.getBody();
        //set the default timeout after the request
        Unirest.setTimeouts(10000, 60000);
        User user = new User();

        // if the response status is 200 set the response parameters to objects, else set the response message
        if (response.getStatus() == 200) {
            user = new GsonBuilder().create().fromJson(json, User.class);
        } else {
            user.setResponseMessage(json);
        }
        user.setHttpStatusCode(response.getStatus());
        return user;
    }
}