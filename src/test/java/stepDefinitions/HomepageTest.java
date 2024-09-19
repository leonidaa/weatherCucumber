package stepDefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BrowserDriver;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

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


    @And("User select Sydney, AU from list")
    public void userSelectSydneyAUFromList() {
        WebDriverWait webDriverWait;
        webDriverWait = new WebDriverWait(BrowserDriver.getDriver(), Duration.ofSeconds(10));
        String path = "/html/body/main/div[3]/div[2]/div/div/div[2]/div[1]/div/ul/li";
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(path)));
        List<WebElement> webElementsList = driver.findElements(By.xpath(path));
        for (WebElement element : webElementsList) {
            if (element.getText().equals("Sydney, AU")) {
                element.click();
                System.out.println("s-a dat click");
            } else
                throw new NoSuchElementException("The specified location 'Sydney, AU' was not found.");
        }
    }
}
//<li data-v-833cbf78=""><span data-v-833cbf78="" style="width: 140px;">Sydney,
// AU <img data-v-49496dbe="" data-v-833cbf78="" src="https://openweathermap.org/images/flags/au.png" class="flag"></span><span data-v-833cbf78="">13°C</span><span data-v-833cbf78=""><svg data-v-833cbf78="" width="50px" height=
// "50px" viewBox="0 0 148 148" class="owm-weather-icon"></svg></span><span data-v-833cbf78=
// "" class="sub" style="width: 75px; text-align: right;">-33.868, 151.207</span></li>
