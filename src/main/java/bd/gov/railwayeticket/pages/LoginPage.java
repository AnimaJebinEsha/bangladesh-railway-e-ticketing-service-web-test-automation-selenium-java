package bd.gov.railwayeticket.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static bd.gov.railwayeticket.utils.CommonUtil.clearAndType;
import static bd.gov.railwayeticket.utils.WaitUtil.*;

public class LoginPage {

    private static final String PAGE_URL = "https://eticket.railway.gov.bd/login/en";
    public static final String EXPECTED_LOGIN_ERROR_MESSAGE = "Invalid Mobile Number Or Password";

    private final By mobileNumberInputField = By.id("mobile_number");
    private final By passwordInputField = By.id("password");
    private final By loginButton = By.className("login-form-submit-btn");
    private final By loginFormError = By.className("railway-form-error");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public static LoginPage navigateLoginPage(WebDriver driver) {
        driver.get(PAGE_URL);
        waitForSeconds(10);
        return new LoginPage(driver);
    }

    public void typeMobileNumber(String mobileNumber) {
        waitForElementToBeVisible(driver, mobileNumberInputField);
        clearAndType(driver.findElement(mobileNumberInputField), mobileNumber);
    }

    public void typePassword(String password) {
        waitForElementToBeVisible(driver, passwordInputField);
        clearAndType(driver.findElement(passwordInputField), password);
    }

    public WebDriver clickLoginButton() {
        waitForElementToBeClickable(driver, mobileNumberInputField);
        driver.findElement(loginButton).click();
        waitForSeconds(15);

        return driver;
    }

    public String getErrorMessage() {
        return driver.findElement(loginFormError).getText();
    }
}
