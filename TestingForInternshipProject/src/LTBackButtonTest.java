import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//Tests the back button on the buttons page
public class LTBackButtonTest {
    public static final String USERNAME = "kylesmart99";
    public static final String AUTOMATE_KEY = "I1LEPGfYzoJaUkhKlCzqhTnrrQsBrCsxtc1YxELaG2MEsY0h34";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.lambdatest.com/wd/hub";

    public static void main(String[] args) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String newUrl = "https://en.wikipedia.org/wiki/%C3%89douard_Henry";
        List<WebElement> buttonsOnPage;
        WebElement stalemateButton;
        String buttonUrl = "https://internproject2020.web.app/buttons";
        String homeUrl = "https://internproject2020.web.app/home";

        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "Firefox");
        capabilities.setCapability("version","78.0");
        capabilities.setCapability("build", "Firefox Back Button test");
        capabilities.setCapability("name", "LoadWebPage");
        capabilities.setCapability("video", true);

        WebDriver driver = new RemoteWebDriver(new URL(URL), capabilities);
        driver.get("https://internproject2020.web.app/home");

        System.out.println("Started on home page.");

        List<WebElement> homePageButtons = new ArrayList<>(driver.findElements(By.className("button")));
        homePageButtons.get(1).click();

        if(driver.getCurrentUrl().equals(buttonUrl)) {
            System.out.println("Buttons Page loaded.");
        } else {
            System.out.println("Buttons page failed to load. Test failed.");
            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
        }

        WebElement buttonsPageButton = driver.findElement(By.className("button"));
        buttonsPageButton.click();

        if(driver.getCurrentUrl().equals(homeUrl)) {
            System.out.println("Home Page loaded. Test passed.");
            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
        } else {
            System.out.println("Home page failed to load. Test failed.");
        }

        driver.quit();
    }
}
