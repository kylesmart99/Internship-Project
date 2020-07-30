import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class LTLoadWebpageTest {
    public static final String USERNAME = "kylesmart99";
    public static final String AUTOMATE_KEY = "I1LEPGfYzoJaUkhKlCzqhTnrrQsBrCsxtc1YxELaG2MEsY0h34";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.lambdatest.com/wd/hub";

    public static void main(String[] args) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String pageTitle = "Welcome to the super secure password login for {{user.bankName}}.";
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "Firefox");
        capabilities.setCapability("version","78.0");
        capabilities.setCapability("build", "Firefox loading test");
        capabilities.setCapability("name", "LoadWebPage");
        capabilities.setCapability("video", true);

        WebDriver driver = new RemoteWebDriver(new URL(URL), capabilities);

        driver.get("https://internproject2020.web.app/home");
        WebElement element = driver.findElement(By.className("navbar-brand"));

        System.out.println(element.getText());
        if (element.getText().equals(pageTitle)) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
        } else {
            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
        }

        driver.quit();
    }
}
