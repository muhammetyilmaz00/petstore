package petStore.stepDefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import petStore.pojo.Store;
import petStore.requests.StoreRequest;
import petStore.utils.TestRunContext;

public class DeletingOrderStepDef {

    final TestRunContext testRunContext = new TestRunContext();
    static int id;

    @When("I send a delete request to the store")
    public void iSendADeleteRequestToTheStore() throws Exception {
        // get the last saved response
        Store store = testRunContext.getStore();
        id = store.getId();

        StoreRequest storeRequest = new StoreRequest(store);
        store = storeRequest.deleteOrderRequest();

        // save the response to use another part
        testRunContext.setStore(store);
    }

    @Then("I see order is deleted successfully")
    public void iSeeOrderIsDeletedSuccessfully() {
        // get the last saved response
        Store store = testRunContext.getStore();

        // make assertions
        Assert.assertEquals(200,store.getHttpStatusCode());
        Assert.assertEquals(200,store.getCode());
        Assert.assertEquals("unknown",store.getType());
        Assert.assertEquals(Integer.toString(id),store.getMessage());
    }
}
