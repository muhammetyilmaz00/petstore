@petStore @addPet
  Feature: Add a new pet to the store

    Scenario: Adding a pet to the store successfully
      Given I have pet information to be added
      When I send a post request to add pet to the store
      Then I see the pet is added to the store successfully