package stepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.BrowserDriver;
import static org.junit.Assert.assertEquals;

public class HomepageTest {
    BrowserDriver driver;
    WebElement webElement;
    @Given("User open weather homepage")
    public void user_open_weather_homepage( ) {
        // Write code here that turns the phrase above into concrete actions
        driver= new BrowserDriver("firefox");
    }
    @When("User find search field box")
    public void user_find_search_field_box(){

        webElement=driver.findElement(By.xpath("//*[@id=\"desktop-menu\"]/form/input[1]"));
    }
    @Then("User should see correct placeholder in search field")
    public void user_should_see_correct_placeholder(){
        String text;
      text=webElement.getAttribute("placeholder");
        assertEquals(text,"Weather in your city");
        driver.close();
    }


}
