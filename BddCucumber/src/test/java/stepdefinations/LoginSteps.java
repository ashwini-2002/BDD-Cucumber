package stepdefinations;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
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

@When("I enter the valid username and password")
public void enter_credentials() {
    driver.findElement(By.id("user-name")).sendKeys("standard_user");
    driver.findElement(By.id("password")).sendKeys("secret_sauce");
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


