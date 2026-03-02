package stepdefinations;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import stepdefinations.DBUtils;

import java.sql.ResultSet;

import static org.junit.Assert.*;

public class LoginSteps {

    WebDriver driver;
    String dbUsername;
    String dbPassword;

    @Given("Launch the browser")
    public void launch_browser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("I connect to database")
    public void connect_database() throws Exception {
        DBUtils.connectDB();
    }

    @When("I fetch login data from database")
    public void fetch_data() throws Exception {

        ResultSet rs = DBUtils.getLoginData();

        if (rs.next()) {
            dbUsername = rs.getString("username");
            dbPassword = rs.getString("password");
        }
    }

    @When("I open the login page")
    public void open_page() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("I login using database credentials")
    public void login_with_db_data() {

        driver.findElement(By.id("user-name")).sendKeys(dbUsername);
        driver.findElement(By.id("password")).sendKeys(dbPassword);
        driver.findElement(By.id("login-button")).click();
    }

    @Then("I should see the products page")
    public void verify_page() throws Exception {
        assertTrue(driver.getCurrentUrl().contains("inventory"));
        driver.quit();
        DBUtils.closeDB();
    }
}