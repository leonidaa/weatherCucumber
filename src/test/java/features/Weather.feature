Feature: page search for the correct placeholder
    Scenario: Verify the main page's search field contains correct placeholder text
        Given User open weather homepage
        When User find search field box
        Then User should see correct placeholder in search field "Weather in your city"
    Scenario: Search for city Sydney,Verify the corrected selected searched city(Sydney,AU), date and time
        Given User open weather homepage
        When User enter Syndey in searched field
        And User click on search button
        And User select Sydney, AU from list
        Then User should see the correct city name
        And user should see the correct date
        And user should see the correct time