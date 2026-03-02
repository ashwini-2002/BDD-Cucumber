package stepdefinitions;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class LoginSteps {

    WebDriver driver;

    @Given("Launch the browser")
    public void launch_the_browser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @When("I open the login page")
    public void open_page() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("I enter the following credentials")
    public void enter_credentials(DataTable dataTable) {

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        String username = data.get(0).get("username");
        String password = data.get(0).get("password");

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @When("I click on login button")
    public void click_login() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("I should see the products page")
    public void verify_page() {
        assertTrue(driver.getCurrentUrl().contains("inventory"));
        driver.quit();
    }
}
