import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

//Tests if the application opens in firefox
public class BSLoadWebpageTest {
    public static final String USERNAME = "kylesmart1";
    public static final String AUTOMATE_KEY = "uQfjJZMYibyy5zpR8dzq";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void main(String[] args) throws Exception{
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Firefox");
        caps.setCapability("browser_version", "latest");

        caps.setCapability("name", "Firefox loading test");

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        driver.get("https://internproject2020.web.app/home");
        WebElement element = driver.findElement(By.className("navbar-brand"));

        System.out.println(element.getText());

        driver.close();
        driver.quit();
    }
}
