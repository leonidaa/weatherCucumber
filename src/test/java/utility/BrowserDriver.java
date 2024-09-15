package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserDriver {
    public static WebDriver driver;
    public static FirefoxOptions optionsFF;
    public static ChromeOptions optionsCh;

    public BrowserDriver(String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
            // Setup FirefoxDriver
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
                    "/src/test/resources/drivers/geckodriver");
            optionsFF = new FirefoxOptions();
            optionsFF.setBinary("/opt/firefox/firefox");

            driver = new FirefoxDriver(optionsFF);
        } else if (browser.equalsIgnoreCase("chrome")) {
            // Setup ChromeDriver
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +
                    "/src/test/resources/drivers/chromedriver");
            optionsCh = new ChromeOptions();
            driver = new ChromeDriver(optionsCh);
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        driver.get("https://openweathermap.org/");

    }

    public void close() {
        if (driver != null) {
            driver.close();
        }
    }
}
