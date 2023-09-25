package bd.gov.railwayeticket.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            FileInputStream input = new FileInputStream("src/test/resources/env.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCorrectPhoneNumber() {
        return properties.getProperty("correct.phone.number");
    }

    public static String getCorrectPassword() {
        return properties.getProperty("correct.password");
    }
}
