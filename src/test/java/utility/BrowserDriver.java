package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

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
            // Provide the path to the existing Firefox profile
            //String profilePath = "/path/to/firefox/profile";
            String profilePath = "/home/adrian/.mozilla/firefox/a04rox3h.default-release-1";

            // Load the Firefox profile from the specified path
            FirefoxProfile profile = new FirefoxProfile(new File(profilePath));

            // Set up FirefoxOptions and add the profile to it
            optionsFF.setProfile(profile);



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

        // Load cookies from a file, if available
        File cookieFile = new File("cookies.data");
        if (cookieFile.exists()) {
            loadCookies(cookieFile);
            driver.navigate().refresh();  // Refresh to apply cookies
        } else {
            // If no cookies, interact with the consent dialog and save cookies
            handleConsentDialog();
            saveCookies(cookieFile);
        }
    }
    // Method to handle the cookie consent dialog
    private static void handleConsentDialog() {
        try {
            // Assuming the "Accept Cookies" button has an identifiable element (adjust the selector if needed)
            WebElement acceptButton = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[3]/button"));
            acceptButton.click();
            System.out.println("Accepted cookie consent.");
        } catch (Exception e) {
            System.out.println("No cookie consent dialog found.");
        }
    }
    // Method to save cookies to a file
    private static void saveCookies(File file) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            Set<Cookie> cookies = driver.manage().getCookies();
            out.writeObject(cookies);
            System.out.println("Cookies saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load cookies from a file
    private static void loadCookies(File file) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Set<Cookie> cookies = (Set<Cookie>) in.readObject();
            for (Cookie cookie : cookies) {
                driver.manage().addCookie(cookie);
            }
            System.out.println("Cookies loaded.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void close() {
        if (driver != null) {
            driver.close();
        }
    }
    // Expose the WebDriver's findElement method through BrowserDriver
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }
}
