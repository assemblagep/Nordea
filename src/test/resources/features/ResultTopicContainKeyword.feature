Feature: Resultset row topic should contains keyword

  As a user
  I want to get website
  Search the goods with keyword
  Sort the results by price DESC
  Check the list row contains the keyword

  Scenario: Check topic contains keyword

    Given Site "Amazon.com"
    When I search "Nikon"
    And  I Sort results DESC
    Then Product "1" topic contains "Nikon D3X"



