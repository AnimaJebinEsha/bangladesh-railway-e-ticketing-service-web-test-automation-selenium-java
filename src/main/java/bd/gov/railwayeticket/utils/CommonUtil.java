package bd.gov.railwayeticket.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.apache.commons.lang3.StringUtils.deleteWhitespace;

public class CommonUtil {

    public static void clearAndType(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public static String deleteSpacesAndToUpper(String text) {
        return deleteWhitespace(text).toUpperCase();
    }
}
