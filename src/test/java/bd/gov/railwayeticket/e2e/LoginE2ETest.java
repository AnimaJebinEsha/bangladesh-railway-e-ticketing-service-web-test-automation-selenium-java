package bd.gov.railwayeticket.e2e;

import bd.gov.railwayeticket.pages.HomePage;
import bd.gov.railwayeticket.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static bd.gov.railwayeticket.utils.CommonUtil.deleteSpacesAndToUpper;
import static bd.gov.railwayeticket.utils.PropertyUtil.getCorrectPassword;
import static bd.gov.railwayeticket.utils.PropertyUtil.getCorrectPhoneNumber;
import static bd.gov.railwayeticket.utils.WaitUtil.waitForElementToBeVisible;
import static bd.gov.railwayeticket.utils.WaitUtil.waitForSeconds;
import static java.util.Objects.nonNull;

public class LoginE2ETest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass()
    public void setup() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @BeforeMethod()
    public void navigateToLoginPage() {
        loginPage = LoginPage.navigateLoginPage(driver);
    }

    @Test()
    public void testCorrectPhoneNumberAndPassword() {
        loginPage.typeMobileNumber(getCorrectPhoneNumber());
        loginPage.typePassword(getCorrectPassword());
        HomePage homePage = new HomePage(loginPage.clickLoginButton());

        Assert.assertEquals(deleteSpacesAndToUpper(homePage.getCurrentPageTitle()),
                deleteSpacesAndToUpper(HomePage.EXPECTED_TITLE));

        homePage.clickAgreeButton();
        logout();
    }

    @Test()
    public void testIncorrectPhoneNumberAndPassword() {
        loginPage.typeMobileNumber("01512345678");
        loginPage.typePassword("zxcvbnma");
        loginPage.clickLoginButton();

        Assert.assertEquals(deleteSpacesAndToUpper(loginPage.getErrorMessage()),
                deleteSpacesAndToUpper(LoginPage.EXPECTED_LOGIN_ERROR_MESSAGE));
    }

    @AfterClass()
    public void tearDown() {
        if (nonNull(driver)) {
            driver.quit();
        }
    }

    private void logout() {
        waitForElementToBeVisible(driver, By.className("railway-logged-user"));
        driver.findElement(By.className("railway-logged-user")).click();
        waitForSeconds(5);
        driver.findElement(By.cssSelector("a[title='Logout']")).click();
        waitForSeconds(5);
    }
}
