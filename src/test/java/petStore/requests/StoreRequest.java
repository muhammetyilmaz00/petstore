package petStore.requests;

import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import petStore.pojo.Store;
import petStore.utils.PropertiesFactory;

public class StoreRequest {

    private final Store store;
    boolean bool = true;

    public StoreRequest(Store store) {
        this.store = store;
    }

    public Store orderPetRequest() throws Exception {
        //set a max wait time before the request
        Unirest.setTimeouts(180000, 180000);
        HttpResponse<String> response = Unirest.post(PropertiesFactory.getProperty("petStore.store.order"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(new GsonBuilder().create().toJson(store))
                .asString();

        String json = response.getBody();
        //set the default timeout after the request
        Unirest.setTimeouts(10000, 60000);
        Store store = new Store();

        // if the response status is 200 set the response parameters to objects, else set the response message
        if(response.getStatus() == 200){
            store = new GsonBuilder().create().fromJson(json, Store.class);
        }else{
            store.setResponseMessage(json);
        }
        store.setHttpStatusCode(response.getStatus());
        return store;
    }

    public Store deleteOrderRequest() throws Exception {
        //set a max wait time before the request
        Unirest.setTimeouts(180000, 180000);
        HttpResponse<String> response = Unirest.delete(PropertiesFactory.getProperty("petStore.store.order")+"/"+store.getId())
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .asString();

        String json = response.getBody();
        //set the default timeout after the request
        Unirest.setTimeouts(10000, 60000);
        Store store = new Store();

        // if the response status is 200 set the response parameters to objects, else set the response message
        if(response.getStatus() == 200){
            store = new GsonBuilder().create().fromJson(json, Store.class);
        }else if(response.getStatus() == 404 && bool){
            // trying to request second time because sometimes it returns 404 wrongly
            // and sleep time is because of wrong 404 status code
            bool = false;
            Thread.sleep(2000);
            deleteOrderRequest();
        }else{
            store.setResponseMessage(json);
            store = new GsonBuilder().create().fromJson(json, Store.class);
        }
        store.setHttpStatusCode(response.getStatus());
        return store;
    }
}
