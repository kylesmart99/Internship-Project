import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//Tests the back button on the buttons page
public class BSBackButtonTest {
    public static final String USERNAME = "kylesmart1";
    public static final String AUTOMATE_KEY = "uQfjJZMYibyy5zpR8dzq";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void main(String[] args) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        String newUrl = "https://en.wikipedia.org/wiki/%C3%89douard_Henry";
        List<WebElement> buttonsOnPage;
        WebElement stalemateButton;
        String buttonUrl = "https://internproject2020.web.app/buttons";
        String homeUrl = "https://internproject2020.web.app/home";

        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Firefox");
        caps.setCapability("browser_version", "79.0 beta");
        caps.setCapability("name", "Firefox Back Button test");

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        driver.get("https://internproject2020.web.app/home");

        System.out.println("Started on home page.");

        List<WebElement> homePageButtons = new ArrayList<>(driver.findElements(By.className("button")));
        homePageButtons.get(1).click();

        if(driver.getCurrentUrl().equals(buttonUrl)) {
            System.out.println("Buttons Page loaded.");
        } else {
            System.out.println("Buttons page failed to load. Test failed.");
        }

        WebElement buttonsPageButton = driver.findElement(By.className("button"));
        buttonsPageButton.click();

        if(driver.getCurrentUrl().equals(homeUrl)) {
            System.out.println("Home Page loaded. Test passed.");
        } else {
            System.out.println("Home page failed to load. Test failed.");
        }

        driver.close();
        driver.quit();
    }
}
