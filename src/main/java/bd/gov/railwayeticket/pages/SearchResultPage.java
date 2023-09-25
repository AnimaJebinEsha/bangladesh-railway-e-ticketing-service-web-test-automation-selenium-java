package bd.gov.railwayeticket.pages;

import org.openqa.selenium.WebDriver;

public class SearchResultPage {

    private final WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCurrentPageTitle() {
        return driver.getTitle();
    }

    public String getExpectedTitle(String from, String to) {
        return "Train ticket from " + from + " to " + to + " | Bangladesh Railway E-Ticketing Service";
    }
}
