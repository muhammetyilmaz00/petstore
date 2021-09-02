@petStore @updatePet
  Feature: Update an existing pet

    Scenario: Updating a pet after adding a pet to store
      Given I have pet information to be added
      And I send a post request to add pet to the store
      When I send a put request to update an existing pet
      Then I see pet is updated successfully