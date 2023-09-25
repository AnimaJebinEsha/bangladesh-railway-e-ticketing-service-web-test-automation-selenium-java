package bd.gov.railwayeticket.pages;

import bd.gov.railwayeticket.utils.TrainClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static bd.gov.railwayeticket.utils.CommonUtil.clearAndType;
import static bd.gov.railwayeticket.utils.WaitUtil.*;

public class HomePage {

    private static final String PAGE_URL = "https://eticket.railway.gov.bd/";
    public static final String EXPECTED_TITLE = "Home | Bangladesh Railway E-Ticketing Service";

    private By agreeButton = By.className("agree-btn");
    private By fromInputField = By.id("dest_from");
    private By toInputField = By.id("dest_to");
    private By dateOfJourneyCalendar = By.id("doj");
    private By classDropdown = By.id("choose_class");
    private By submitButton = By.xpath("//button[@type='submit']");


    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public static HomePage navigateToHomePage(WebDriver driver) {
        driver.get(PAGE_URL);
        waitForSeconds(10);
        return new HomePage(driver);
    }

    public void clickAgreeButton() {
        waitForElementToBeVisible(driver, agreeButton);
        WebElement agreeButtonElement = driver.findElement(agreeButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", agreeButtonElement);
        waitForSeconds(5);
    }

    public void typeFromStation(String stationName) {
        waitForElementToBeVisible(driver, fromInputField);
        clearAndType(driver.findElement(fromInputField), stationName);
    }

    public void typeToStation(String stationName) {
        waitForElementToBeVisible(driver, toInputField);
        clearAndType(driver.findElement(toInputField), stationName);
    }

    public void selectJourneyDate(String date) {
        waitForElementToBeClickable(driver, dateOfJourneyCalendar);
        driver.findElement(dateOfJourneyCalendar).click();
        driver.findElement(By.xpath("//a[@class='ui-state-default' and text()='" + date + "']")).click();
    }

    public void selectClass(TrainClass trainClass) {
        waitForElementToBeClickable(driver, classDropdown);
        Select dropdown = new Select(driver.findElement(classDropdown));
        dropdown.selectByValue(trainClass.name());
    }

    public WebDriver clickSubmitButton() {
        waitForElementToBeClickable(driver, submitButton);
        driver.findElement(submitButton).click();
        waitForSeconds(15);
        return driver;
    }

    public String getCurrentPageTitle() {
        return driver.getTitle();
    }
}
