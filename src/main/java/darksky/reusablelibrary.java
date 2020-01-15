package darksky;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class reusablelibrary {
    //method to initialize chrome driver
    public static WebDriver setChrome() {
        String chromePath = "/Users/sharmin/Desktop/IdeaProjects/darkskytest/src/main/resources/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromePath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start=maximized", "incognito");
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }

    //method to capture screenshot
    public static void screenShot(WebDriver driver, String screenshotName) throws IOException {
        String screenShotPath = "/Users/sharmin/Desktop/IdeaProjects/darkskytest/src/main/Screenshots";
        String fileName = screenshotName + ".png";
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File(screenShotPath + fileName));
    }

    //method for getting date + time for screenshots
    public static String getDateTime() {
        SimpleDateFormat sdfDateTime;
        String strDateTime;
        sdfDateTime = new SimpleDateFormat("yyyyMMdd'_'HHmmss'_'SSS");
        Date now = new Date();
        strDateTime = sdfDateTime.format(now);
        return strDateTime;
    }


    //method to click
    public static void click(WebDriver driver, WebElement locator, int indexNum, String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        try {
            System.out.println("Clicking on element " + elementName);
            wait.until(ExpectedConditions.visibilityOfAllElements(locator)).get(indexNum).click();
        } catch (Exception e) {
            System.out.println("Unable to click element " + elementName);
            screenShot(driver, elementName);
        }
    }

    //method for user to input info
    public static void userInput(WebDriver driver, WebElement locator, int indexNum, String userValue, String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        try {
            System.out.println("Entering " + userValue + " on element " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOfAllElements(locator)).get(indexNum);
            element.clear();
            element.sendKeys(userValue);
        } catch (Exception e) {
            System.out.println("Unable to enter value in element " + elementName + " -- " + e);
            screenShot(driver, elementName);
        }
    }

    //capture text from webpage
    public static String captureText(WebDriver driver, WebElement locator, int index, String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String text = null;
        try {
            System.out.println("Capturing text on element -- " + elementName);
            text = wait.until(ExpectedConditions.visibilityOfAllElements(locator)).get(index).getText();
        } catch (Exception e) {
            System.out.println("Unable to capture text on element " + elementName + " -- " + e);
            screenShot(driver, elementName);
        }
        return text;
    }
}