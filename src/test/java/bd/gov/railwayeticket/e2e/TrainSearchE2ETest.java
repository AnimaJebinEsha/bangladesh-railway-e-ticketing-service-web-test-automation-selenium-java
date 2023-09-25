package bd.gov.railwayeticket.e2e;

import bd.gov.railwayeticket.pages.HomePage;
import bd.gov.railwayeticket.pages.SearchResultPage;
import bd.gov.railwayeticket.utils.TrainClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static bd.gov.railwayeticket.utils.CommonUtil.deleteSpacesAndToUpper;
import static java.util.Objects.nonNull;

public class TrainSearchE2ETest {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeClass()
    public void setup() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @BeforeMethod()
    public void navigateToHomePage() {
        homePage = HomePage.navigateToHomePage(driver);
        homePage.clickAgreeButton();
    }

    @Test()
    public void testCorrectTicketSearch() {
        String from = "Dhaka";
        String to = "Rajshahi";
        String date = "28";
        TrainClass trainClass = TrainClass.S_CHAIR;

        homePage.typeFromStation(from);
        homePage.typeToStation(to);
        homePage.selectJourneyDate(date);
        homePage.selectClass(trainClass);

        SearchResultPage searchResultPage = new SearchResultPage(homePage.clickSubmitButton());

        Assert.assertEquals(deleteSpacesAndToUpper(searchResultPage.getCurrentPageTitle()),
                deleteSpacesAndToUpper(searchResultPage.getExpectedTitle(from, to)));
    }

    @AfterClass()
    public void tearDown() {
        if (nonNull(driver)) {
            driver.quit();
        }
    }

}
