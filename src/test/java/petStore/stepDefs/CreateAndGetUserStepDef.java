package petStore.stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import petStore.pojo.User;
import petStore.requests.UserRequest;
import petStore.utils.TestRunContext;

import java.util.Random;

public class CreateAndGetUserStepDef {

    final TestRunContext testRunContext = new TestRunContext();

    final User userParameters = new User();
    private static final Random r = new Random();
    static final int id = r.nextInt(100) + 1;
    static final String username = "testUserName";
    static final String firstName = "testFirstName";
    static final String lastName = "testLastName";
    static final String email = "testEmail@email.com";
    static final String password = "testPassword123*-";
    static final String phone = "+905651254125";
    static final int userStatus = 0;

    @Given("I have user information to be created")
    public void iHaveUserInformationToBeCreated() {
        userParameters.setId(id);
        userParameters.setUsername(username);
        userParameters.setFirstName(firstName);
        userParameters.setLastName(lastName);
        userParameters.setEmail(email);
        userParameters.setPassword(password);
        userParameters.setPhone(phone);
        userParameters.setUserStatus(userStatus);
    }

    @When("I send a post request to create user in pet store")
    public void iSendAPostRequestToCreateUserInPetStore() throws Exception {
        // send the create a user request
        User user;
        UserRequest userRequest = new UserRequest(userParameters);
        user = userRequest.createUserRequest();

        // save the response to use another part
        testRunContext.setUser(user);
    }

    @Then("I see the user is created in pet store successfully")
    public void iSeeTheUserIsCreatedInPetStoreSuccessfully() {
        // get the last saved response
        User user = testRunContext.getUser();

        // make assertions
        Assert.assertEquals(200,user.getCode());
        Assert.assertEquals("unknown",user.getType());
        Assert.assertEquals(Integer.toString(id),user.getMessage());
    }

    @When("I send a get request to get the user by user name")
    public void iSendAGetRequestToGetTheUserByUserName() throws Exception {
        User user;
        UserRequest userRequest = new UserRequest(userParameters);
        user = userRequest.getUserByUsernameRequest();

        // save the response to use another part
        testRunContext.setUser(user);
    }

    @Then("I see the user is listed successfully")
    public void iSeeTheUserIsListedSuccessfully() {
        // get the last saved response
        User user = testRunContext.getUser();

        Assert.assertEquals(200,user.getHttpStatusCode());
//        Assert.assertEquals(id, user.getId());
        Assert.assertEquals(username, user.getUsername());
        Assert.assertEquals(firstName, user.getFirstName());
        Assert.assertEquals(lastName, user.getLastName());
        Assert.assertEquals(email, user.getEmail());
        Assert.assertEquals(password, user.getPassword());
        Assert.assertEquals(phone, user.getPhone());
        Assert.assertEquals(userStatus,user.getUserStatus());
    }
}
