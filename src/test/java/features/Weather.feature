Feature: page search for the correct placeholder
    Scenario: Verify the main page's search field contains correct placeholder text
        Given User open weather homepage
        When User find search field box
        Then User should see correct placeholder in search field

