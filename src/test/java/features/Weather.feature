Feature: page search for the correct placeholder
    Scenario: Verify the main page's search field contains correct placeholder text
        Given "User open weather homepage"
        When "User find search field box "
        Then "User should see correct placeholder in search field"
    Scenario: Second Method to Verify the main page's search field contains correct placeholder text
        Given "User open weather homepage and navigate to search field box"
        When "User find search field box placeholder equal with the message"
        Then "it is correct"
