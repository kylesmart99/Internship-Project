import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//This tests if a extra tab opens up after the stalemate button is clicked.
//Also checks if the correct page opens up.
public class LTOpeningUpAnotherTabTest {
    public static final String USERNAME = "kylesmart99";
    public static final String AUTOMATE_KEY = "I1LEPGfYzoJaUkhKlCzqhTnrrQsBrCsxtc1YxELaG2MEsY0h34";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.lambdatest.com/wd/hub";

    public static void main(String[] args) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String newUrl = "https://en.wikipedia.org/wiki/%C3%89douard_Henry";
        List<WebElement> buttonsOnPage;
        WebElement stalemateButton;

        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "Firefox");
        capabilities.setCapability("version","78.0");
        capabilities.setCapability("build", "Firefox New Tab Opening test");
        capabilities.setCapability("name", "LoadWebPage");
        capabilities.setCapability("video", true);

        WebDriver driver = new RemoteWebDriver(new URL(URL), capabilities);
        driver.get("https://internproject2020.web.app/home");
        buttonsOnPage = driver.findElements(By.className("button"));
        //Since there are only two buttons on the home page
        buttonsOnPage.get(1).click();

        //Get the button on the other page
        stalemateButton = driver.findElement(By.className("stalemate-button"));
        stalemateButton.click();

        //Get both tabs and switch to the new one
        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));

        //Wait until the second tab is loaded
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.titleIs("Édouard Henry - Wikipedia"));

        //Check if the tab has changed properly
        if (driver.getCurrentUrl().equals(newUrl)){
            System.out.println("Tab loaded successfully! Test Passed.");
            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
        } else {
            System.out.println("Tab failed to load. Test failed.");
        }

        driver.quit();
    }
}
