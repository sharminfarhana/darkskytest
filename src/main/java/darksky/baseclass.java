package darksky;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class baseclass extends abstractclass {

    public baseclass(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
    }

    public static homepage home_page() {
        homepage home_page = new homepage(driver);
        return home_page;
    }

    public static darkskyscenario3 dark_skyscenario() {
        darkskyscenario3 dark_skyscenario = new darkskyscenario3(driver);
        return dark_skyscenario;
    }
}
