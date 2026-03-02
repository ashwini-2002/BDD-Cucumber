package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class LoginSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    // -------------------- SETUP --------------------

    @Before
    public void setUp() {

        // Set path if needed
        // System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");

        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        } catch (WebDriverException e) {
            throw new IllegalStateException("WebDriver initialization failed", e);
        }
    }

    // -------------------- BACKGROUND --------------------

    @Given("user is on login page")
    public void user_is_on_login_page() {

        driver.get("https://example.com/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
    }

    // -------------------- ENTER CREDENTIALS --------------------

    @When("user enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) {

        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("username"))
        );

        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("password"))
        );

        usernameField.clear();
        usernameField.sendKeys(username);

        passwordField.clear();
        passwordField.sendKeys(password);
    }

    // -------------------- CLICK LOGIN --------------------

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {

        try {
            WebElement loginBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("loginButton"))
            );

            loginBtn.click();

        } catch (TimeoutException e) {
            throw new IllegalStateException("Login button was not clickable", e);
        }
    }

    // -------------------- VALIDATE RESULT --------------------

    @Then("login should be {string}")
    public void login_should_be(String result) {

        if (result.equalsIgnoreCase("successful")) {

            wait.until(ExpectedConditions.urlContains("dashboard"));
            Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));

        } else {

            WebElement errorMsg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage"))
            );

            Assert.assertTrue(errorMsg.isDisplayed());
        }
    }

    // -------------------- DATATABLE SCENARIO --------------------

    @When("user logs in with following credentials")
    public void user_logs_in_with_following_credentials(DataTable table) {

        List<Map<String, String>> users = table.asMaps(String.class, String.class);

        for (Map<String, String> user : users) {

            user_enters_username_and_password(
                    user.get("username"),
                    user.get("password")
            );

            user_clicks_on_login_button();

            if (user.get("username").equals("admin") &&
                user.get("password").equals("admin123")) {

                wait.until(ExpectedConditions.urlContains("dashboard"));
                Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));

                driver.navigate().back();

            } else {

                WebElement errorMsg = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage"))
                );

                Assert.assertTrue(errorMsg.isDisplayed());
            }
        }
    }

    // -------------------- TEARDOWN --------------------

    @After
    public void tearDown() {

        if (driver != null) {
            try {
                driver.quit();
            } catch (WebDriverException e) {
                System.out.println("Error while closing driver: " + e.getMessage());
            }
        }
    }
}
