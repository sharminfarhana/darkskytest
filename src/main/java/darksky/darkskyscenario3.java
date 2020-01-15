package darksky;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.get;

public class darkskyscenario3 extends abstractclass {

    //create constructor to use driver and logger as a Page Object
    public darkskyscenario3(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
    }

    //validate current weather condition
    public darkskyscenario3 validateCurrentCondition(String APIpath) throws IOException {
        WebElement currentCondLocator = driver.findElement(By.xpath("//*[contains(@class,'summary')]"));
        String uiCond = captureText(driver, currentCondLocator, 0, "Current Weather Condition");
        String[] uiCondSplit = uiCond.split("˚");
        System.out.println("Current weather conditon is " + uiCondSplit[1]);

        //capturing API response
        Response response = get(APIpath);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String apiCond = jsonPathEvaluator.get("currently.summary").toString();
        System.out.println("Weather condition received from Response " + apiCond);

        // Assert current conditions from UI to API
        Assert.assertEquals(apiCond, uiCondSplit[1]);
        return this;
    }

    //method to validate forecast summary
    public darkskyscenario3 validateForecastSummary(String APIpath) throws IOException {
        WebElement currentForecastLocator = driver.findElement(By.xpath("//span[contains(@class,'summary next swap')]"));
        String uiForecast = captureText(driver, currentForecastLocator, 0, "Current Forecast Summary");
        System.out.println("Current weather conditon is " + uiForecast);

        //capturing API response
        Response response = get(APIpath);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String apiForecast = jsonPathEvaluator.get("minutely.summary").toString();
        System.out.println("Current Forecast received from Response " + apiForecast);

        //Assert current forecast from UI to API
        Assert.assertEquals(apiForecast, uiForecast);
        return this;
    }

    // method to validate icon
    public darkskyscenario3 validateIcon(String APIpath) throws InterruptedException {
        Thread.sleep(3000);
        String currentIcon = driver.findElement(By.xpath("//*[contains(@class,'clear-day-icon-currently')]/following::img")).getAttribute("alt");
        String[] currentIconSplit = currentIcon.split(" ");
        System.out.println("Current icon showing is " + currentIconSplit[0]);

        //capturing API response
        Response response = get(APIpath);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String apiIcon = jsonPathEvaluator.get("currently.icon").toString();
        System.out.println("Current Forecast received from Response " + apiIcon);

        //Assert current icon from UI to API
        Assert.assertEquals(apiIcon, currentIconSplit[0]);

        return this;
    }
}