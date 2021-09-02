@petStore @getUser
  Feature: Get a user by user name

    Scenario: Getting a user by user name after creating a user
      Given I have user information to be created
      And I send a post request to create user in pet store
      And I see the user is created in pet store successfully
      When I send a get request to get the user by user name
      Then I see the user is listed successfully