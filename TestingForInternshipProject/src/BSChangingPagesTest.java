import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.util.List;

//Tests if the page changes correctly when the button to change pages is clicked.
public class BSChangingPagesTest {
    public static final String USERNAME = "kylesmart1";
    public static final String AUTOMATE_KEY = "uQfjJZMYibyy5zpR8dzq";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void main(String[] args) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        String url;
        String newUrl;
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Firefox");
        caps.setCapability("browser_version", "79.0 beta");
        caps.setCapability("name", "Firefox Page Change test");

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        driver.get("https://internproject2020.web.app/home");
        url = driver.getCurrentUrl();

        List<WebElement> changePageButton = driver.findElements(By.className("button"));

        //Since there are only two buttons on the page
        changePageButton.get(1).click();

        newUrl = driver.getCurrentUrl();

        if (newUrl != url){
            System.out.println("The url has changed to " + newUrl + " successfully! Test passed.");
        } else {
            System.out.println("The url has not changed. Test failed.");
        }

        driver.close();
        driver.quit();
    }
}
