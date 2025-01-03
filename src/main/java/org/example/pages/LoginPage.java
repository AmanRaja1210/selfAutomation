package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By emailInputLocator = By.xpath("//input[@name='email']");
    private final By passwordInputLocator = By.xpath("//input[@name='password']");
    private final By loginButtonLocator = By.xpath("//input[@type='submit']");
    private final By forgottenPasswordLinkLocator = By.xpath("(//a[contains(text(),'Forgotten Password')])[1]");
    private final By logoutLinkLocator = By.linkText("Logout");
    private final By errorMessageLocator = By.cssSelector(".alert-danger");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
    }

    // Actions
    public void enterEmail(String email) {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputLocator));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInputLocator));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginButtonLocator));
        loginButton.click();
    }

    public void clickForgottenPasswordLink() {
        WebElement forgottenPassword = wait.until(ExpectedConditions.elementToBeClickable(forgottenPasswordLinkLocator));
        forgottenPassword.click();
    }

    public boolean checkForgotPwdLink() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(forgottenPasswordLinkLocator)).isDisplayed();
    }

    public boolean checkLogoutLink() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLinkLocator)).isDisplayed();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public String getForgotPwdPageUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isErrorMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator)).isDisplayed();
    }
}
