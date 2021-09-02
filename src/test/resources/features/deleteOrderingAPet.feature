@petStore @deleteOrderPetFromStore
Feature: Delete an ordering a pet from a store

  Scenario: Deleting an ordering a pet from store
    Given I have pet information to be ordered
    And I send a post request to order a pet from the store
    And I see pet is ordered successfully
    When I send a delete request to the store
    Then I see order is deleted successfully