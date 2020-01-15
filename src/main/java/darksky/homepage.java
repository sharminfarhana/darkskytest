package darksky;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static io.restassured.RestAssured.get;

public class homepage extends abstractclass {


    //create constructor to use driver and logger as a Page Object
    public homepage(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
    }

    //Storing webelements
    @FindBy(xpath = "//input[@type='text']")
    public static WebElement searchBoxLocator;
    @FindBy(xpath = "//*[@class='searchButton']")
    public static WebElement searchButtonLocator;
    @FindBy(xpath = "//*[contains(@class,'summary swap')]")
    public static WebElement currentTempLocator;

    //method to input zipcode into search bar and search
    public homepage searchBar(String zipCode) throws IOException {
        userInput(driver, searchBoxLocator, 0, zipCode, "Location Search Box");
        click(driver, searchButtonLocator, 0, "Search Button");
        return this;
    } // end of zipcode input

    //method to capture current temperature, API endpoint temperature and assert against each othr
    public homepage validateTemperature(String APIpath) throws IOException {
        WebElement currentTempLocator = driver.findElement(By.xpath("//*[contains(@class,'summary')]"));
        String uiTemp = captureText(driver, currentTempLocator, 0, "Current Temperature");
        String[] uiTempSplit = uiTemp.split("˚");
        System.out.println("Current temperature is " + uiTempSplit[0]);

        //capturing API response
        Response response = get(APIpath);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String apiTemp = jsonPathEvaluator.get("currently.temperature").toString();
        System.out.println("Temperature received from Response " + apiTemp);

        //6. Assert current temp from UI to API
        Assert.assertEquals(apiTemp, uiTempSplit[0]);
        return this;
    }

    //method to capture current time and compare to time on UI
    public homepage temperatureTimeline() throws IOException, InterruptedException {
        String pattern = "haa";
        DateFormat dateF = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.HOUR_OF_DAY, 0);
        String currentTime = dateF.format(calendar.getTime());
        System.out.println("Current time is: " + currentTime);

        for (int i = 2; i < 13; i += 2) {
            String pattern1 = "haa";
            DateFormat dateFormat = new SimpleDateFormat(pattern1);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(calendar2.HOUR_OF_DAY, i);
            String currentHourPlus2 = dateFormat.format(calendar2.getTime());
            currentHourPlus2 = currentHourPlus2.replace("AM", "am").replace("PM", "pm");
            Thread.sleep(2000);
            if (driver.findElement(By.xpath("//*[@class='hour']/span[contains(@class,'" + currentHourPlus2 + "')]")).isDisplayed()) {
                System.out.println("Current time plus 2 is: " + currentHourPlus2);
            }

            WebElement currTimePlus2 = driver.findElement(By.xpath("//*[@class='hour']/span[contains(@class,'" + currentHourPlus2 + "')]"));
            String currentTimePlus2 = currTimePlus2.getText();

            System.out.println("The next time listed on the timeline is " + currentTimePlus2);

            Assert.assertEquals(currentHourPlus2, currentTimePlus2);
        }
        return this;

    }

}


