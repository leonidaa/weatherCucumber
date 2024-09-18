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
    public void user_open_weather_homepage() {
        // Write code here that turns the phrase above into concrete actions
        driver = new BrowserDriver("firefox");
    }

    @When("User find search field box")
    public void user_find_search_field_box() {

        webElement = driver.findElement(By.xpath("//*[@id=\"desktop-menu\"]/form/input[1]"));
    }

    @Then("User should see correct placeholder in search field {string}")
    public void user_should_see_correct_placeholder(String text_actual) {
        String text;
        text = webElement.getAttribute("placeholder");
        assertEquals(text, text_actual);
        driver.close();
    }

    @When("User enter Syndey in searched field")
    public void user_enter_Syndey_in_search_field() {
        webElement = driver.findElement(By.xpath("/html/body/main/div[3]/div[2]/div/div/div[2]/div[1]/div/input"));
        webElement.sendKeys("Sydney");
    }

    @When("User click on search button")
    public void user_click_on_search_button() {
        driver.findElement(By.xpath("/html/body/main/div[3]/div[2]/div/div/div[2]/div[1]/button")).click();
    }

}