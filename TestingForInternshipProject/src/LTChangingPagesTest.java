import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.util.List;

//Tests if the page changes correctly when the button to change pages is clicked.
public class LTChangingPagesTest {
    public static final String USERNAME = "kylesmart99";
    public static final String AUTOMATE_KEY = "I1LEPGfYzoJaUkhKlCzqhTnrrQsBrCsxtc1YxELaG2MEsY0h34";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.lambdatest.com/wd/hub";

    public static void main(String[] args) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String url;
        String newUrl;
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "Firefox");
        capabilities.setCapability("version","78.0");
        capabilities.setCapability("build", "Firefox Page Change Test");
        capabilities.setCapability("name", "LoadWebPage");
        capabilities.setCapability("video", true);

        WebDriver driver = new RemoteWebDriver(new URL(URL), capabilities);
        driver.get("https://internproject2020.web.app/home");
        url = driver.getCurrentUrl();

        List<WebElement> changePageButton = driver.findElements(By.className("button"));

        //Since there are only two buttons on the page
        changePageButton.get(1).click();

        newUrl = driver.getCurrentUrl();

        if (newUrl != url){
            System.out.println("The url has changed to " + newUrl + " successfully! Test passed.");
            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
        } else {
            System.out.println("The url has not changed. Test failed.");
        }

        driver.quit();
    }
}
