@petStore @orderPetFromStore
  Feature: Order a pet from a store

    Scenario: Ordering a pet from store
      Given I have pet information to be ordered
      When I send a post request to order a pet from the store
      Then I see pet is ordered successfully