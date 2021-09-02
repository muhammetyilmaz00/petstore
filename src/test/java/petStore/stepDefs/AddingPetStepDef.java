package petStore.stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import petStore.pojo.Category;
import petStore.pojo.Pet;
import petStore.pojo.Tag;
import petStore.requests.PetRequest;
import petStore.utils.TestRunContext;

import java.util.ArrayList;
import java.util.List;

public class AddingPetStepDef {

    final TestRunContext testRunContext = new TestRunContext();
    static Pet pet = new Pet();

    static final int petId = 12345;
    static final String petName = "doggie";
    static final List<String> photoUrl = new ArrayList<>();
    static final String petStatus = "available";
    static final int categoryId = 0;
    static final String categoryName = "dogs";
    static final int tagId = 0;
    static final String tagName = "dog";
    static final String url = "https://imgur.com/6cddSAw";

    public static Pet setPetParameters(){
        Category category = new Category();
        Tag tag = new Tag();
        List<Tag> tags = new ArrayList<>();

        pet.setId(petId);
        pet.setName(petName);
        photoUrl.add(url);
        pet.setPhotoUrls(photoUrl);
        pet.setStatus(petStatus);
        category.setId(categoryId);
        category.setName(categoryName);
        tag.setId(tagId);
        tag.setName(tagName);
        tags.add(tag);
        pet.setCategory(category);
        pet.setTags(tags);
        return pet;
    }

    @Given("I have pet information to be added")
    public void iHavePetInformationToBeAdded() {
        setPetParameters();
    }

    @When("I send a post request to add pet to the store")
    public void iSendAPostRequestToAddPetToTheStore() throws Exception {
        // send the add a pet request
        PetRequest petRequest = new PetRequest(pet);
        pet = petRequest.addPetRequest();

        // save the response to use another part
        testRunContext.setPet(pet);
    }

    @Then("I see the pet is added to the store successfully")
    public void iSeeThePetIsAddedToTheStoreSuccessfully() {
        // get the last saved response
        Pet pet = testRunContext.getPet();

        // make assertions
        Assert.assertEquals(200,pet.getHttpStatusCode());
        Assert.assertEquals(petId,pet.getId());
        Assert.assertEquals(petName,pet.getName());
        Assert.assertEquals(photoUrl,pet.getPhotoUrls());
        Assert.assertEquals(petStatus,pet.getStatus());
        Assert.assertEquals(categoryId,pet.getCategory().getId());
        Assert.assertEquals(categoryName,pet.getCategory().getName());
        Assert.assertEquals(tagId,pet.getTags().get(0).getId());
        Assert.assertEquals(tagName,pet.getTags().get(0).getName());
    }
}