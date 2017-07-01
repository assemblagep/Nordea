Feature: Results should contain keyword

  As a user
  I want to get website
  Search the goods with keyword
  Check that all items contain the keyword

  Scenario: Search by keyword

    Given Site "Amazon.com"
    When I search "Nikon"
    Then Search results should contain "Nikon"




