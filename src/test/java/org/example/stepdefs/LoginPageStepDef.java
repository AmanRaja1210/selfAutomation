package org.example.stepdefs;

import com.aventstack.extentreports.ExtentTest;
import hooks.ExtentHooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.LoginPage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPageStepDef {

    private final WebDriver driver = ExtentHooks.getDriver();
    private final LoginPage loginPage = new LoginPage(driver);
    private final ExtentTest test = ExtentHooks.getTest(); // Use ExtentHooks.getTest() here
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("I am on the openCart Login Page")
    public void i_am_on_the_open_cart_login_page() {
        String expectedUrl="https://naveenautomationlabs.com/opencart/index.php?route=account/login";
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
        test.info("Navigated to the login page.");
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "The page did not launch the expected URL!");

    }


    @Given("I have entered a valid username and password")

    public void i_have_entered_a_valid_username_and_password() {
        loginPage.enterEmail("qatestertest@gmail.com");
        loginPage.enterPassword("Test@123");
        test.info("Entered valid username and password.");
    }

    @Given("I have entered invalid {} and {}")
    public void i_have_entered_invalid_and(String username, String password) {
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
        test.info(String.format("Entered invalid username: %s and password: %s", username, password));
    }

    @When("I click on Login Button")
    public void i_click_on_login_button() {
        loginPage.clickLoginButton();
        test.info("Clicked on the login button.");
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        Assert.assertTrue(loginPage.checkLogoutLink(), "Logout link not found!");
        test.pass("Logged in successfully.");
    }

    @Then("I should see an error message indicating {string}")
    public void i_should_see_an_error_message_indicating(String expectedMessage) {
        boolean isErrorDisplayed = loginPage.isErrorMessageDisplayed();
        Assert.assertTrue(isErrorDisplayed, "Error message not displayed!");
        test.pass("Validated error message: " + expectedMessage);
    }

    @When("I click on the \"Forgotten Password\" link")
    public void i_click_on_the_forgotten_password_link() {
        loginPage.clickForgottenPasswordLink();
        test.info("Clicked on 'Forgotten Password' link.");
    }

    @Then("I should be redirected to the password reset page")
    public void iShouldBeRedirectedToThePasswordResetPage() {
        String currentUrl = loginPage.getForgotPwdPageUrl();
        Assert.assertTrue(currentUrl.contains("account/forgotten"), "Not redirected to the password reset page!");
        test.pass("Redirected to the password reset page.");
    }
}
