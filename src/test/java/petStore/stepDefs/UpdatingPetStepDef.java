package petStore.stepDefs;

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

public class UpdatingPetStepDef {

    final TestRunContext testRunContext = new TestRunContext();
    Pet pet = new Pet();
    final List<String> photoUrl = new ArrayList<>();
    final String name = "catty";
    final String url = "https://imgur.com/5woimoe";
    final String categoryName = "cats";
    final String tagName = "cat";
    final String tagName2 = "cat2";

    @When("I send a put request to update an existing pet")
    public void iSendAPutRequestToUpdateAnExistingPet() throws Exception {
        Category category = new Category();
        Tag tag1 = new Tag();
        Tag tag2 = new Tag();
        List<Tag> tags = new ArrayList<>();

        // get the last saved response
        Pet pet = testRunContext.getPet();

//        pet = AddingPetStepDef.setPetParameters();

        // changing the name, photoUrl, categoryName, and tagNames
        photoUrl.add(url);
        pet.setName(name);
        pet.setPhotoUrls(photoUrl);
        category.setName(categoryName);
        pet.setCategory(category);
        tag1.setName(tagName);
        tag2.setName(tagName2);
        tags.add(tag1);
        tags.add(tag2);
        pet.setTags(tags);

        // send the add a pet request
        PetRequest petRequest = new PetRequest(pet);
        pet = petRequest.updatePetRequest2();

        // save the response to use another part
        testRunContext.setPet(pet);
    }

    @Then("I see pet is updated successfully")
    public void iSeePetIsUpdatedSuccessfully() {
        // get the last saved response
        Pet pet = testRunContext.getPet();

        // make assertions
        Assert.assertEquals(200, pet.getHttpStatusCode());
        Assert.assertEquals(name, pet.getName());
        Assert.assertEquals(photoUrl, pet.getPhotoUrls());
        Assert.assertEquals(categoryName, pet.getCategory().getName());
        Assert.assertEquals(tagName, pet.getTags().get(0).getName());
        Assert.assertEquals(tagName2, pet.getTags().get(1).getName());
    }
}
