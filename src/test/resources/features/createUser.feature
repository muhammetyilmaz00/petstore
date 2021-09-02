@petStore @createUser
  Feature: Create a user in pet store

    Scenario: Creating a user
      Given I have user information to be created
      When I send a post request to create user in pet store
      Then I see the user is created in pet store successfully