package darksky;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class abstractclass extends reusablelibrary {

    public static WebDriver driver = null;

    @BeforeSuite
    public static void captureTestName() {
        driver = reusablelibrary.setChrome();

        driver.navigate().to("https://darksky.net");

    }

    @AfterSuite
    public static void closeBrowser() {
        driver.manage().deleteAllCookies();
        driver.quit();
    } // end of after suite

}
