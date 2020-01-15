package darkskytest;

import darksky.abstractclass;
import org.testng.annotations.Test;

import java.io.IOException;

import static darksky.baseclass.*;

public class homepage_test extends abstractclass {

    @Test
    public void assertTemperature() throws IOException, InterruptedException {

        //Scenario one: 1. go to https://darksky.net/
        driver.navigate().to("https://darksky.net");

        //2. Enter "10001" to search text field
        home_page().searchBar("10001");

        //3. Capture current temperature from UI, 4, 5 + 6. Assessed API endpoint in Postman and extract temperature w/ restassured, asserted against UI temp
        home_page().validateTemperature("https://api.darksky.net/forecast/4d51fbb64d23886e24bc76aa280a1497/37.8267,-122.4233");
    }

    @Test
    public void tempTimeline() throws IOException, InterruptedException {
        //Scenario 2 Verify temperature timeline displays in 2 hour increments from current time. Test script should validate expected functionality
        home_page().temperatureTimeline();

    }

    @Test
    public void validateWeatherConditions() throws IOException {
        //TC.01 - Validate current weather conditions
        dark_skyscenario().validateCurrentCondition("https://api.darksky.net/forecast/4d51fbb64d23886e24bc76aa280a1497/37.8267,-122.4233");

    }

    @Test
    public void validateForecastSummary() throws IOException {
        //TC.02 - Validate current forecast summary
        dark_skyscenario().validateForecastSummary("https://api.darksky.net/forecast/4d51fbb64d23886e24bc76aa280a1497/37.8267,-122.4233");
    }

    @Test
    public void validate() throws InterruptedException {
        //TC.03 - Validate current icon
        dark_skyscenario().validateIcon("https://api.darksky.net/forecast/4d51fbb64d23886e24bc76aa280a1497/37.8267,-122.4233");
    }
}

