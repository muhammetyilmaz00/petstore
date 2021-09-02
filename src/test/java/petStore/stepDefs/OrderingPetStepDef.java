package petStore.stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import petStore.pojo.Store;
import petStore.requests.StoreRequest;
import petStore.utils.TestRunContext;

public class OrderingPetStepDef {

    final TestRunContext testRunContext = new TestRunContext();

    static Store store = new Store();
    static final int id = (int) (Math.random()*100);
    static final int petId = (int) (Math.random()*10);

    public static void setStoreParameters(){
        store.setId(id);
        store.setPetId(petId);
        store.setQuantity(1);
        store.setShipDate("2021-09-01T20:09:53.956Z");
        store.setStatus("placed");
        store.setComplete(true);
    }

    @Given("I have pet information to be ordered")
    public void iHavePetInformationToBeOrdered() {
        setStoreParameters();
    }

    @When("I send a post request to order a pet from the store")
    public void iSendAPostRequestToOrderAPetFromTheStore() throws Exception {
        // send the order a pet request
        StoreRequest storeRequest = new StoreRequest(store);
        store = storeRequest.orderPetRequest();

        // save the response to use another part
        testRunContext.setStore(store);
    }

    @Then("I see pet is ordered successfully")
    public void iSeePetIsOrderedSuccessfully() {
        // get the last saved response
        Store store = testRunContext.getStore();

        // make assertions
        Assert.assertEquals(id,store.getId());
        Assert.assertEquals(petId,store.getPetId());
        Assert.assertEquals(1,store.getQuantity());
        Assert.assertEquals("placed",store.getStatus());
        Assert.assertTrue(store.isComplete());
    }
}
