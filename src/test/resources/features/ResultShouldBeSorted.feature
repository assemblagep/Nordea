Feature: Results should be sorted

  As a user
  I want to get website
  Search the goods with keyword
  Sort the results by price DESC
  Get sorted list

  Scenario: Check results are sorted

    Given Site "Amazon.com"
    When I search "Nikon"
    And  I Sort results DESC
    Then Results have to be sorted



