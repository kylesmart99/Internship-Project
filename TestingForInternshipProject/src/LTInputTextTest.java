import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.util.concurrent.TimeUnit;

//Tests if the application lets the user input valid text.
public class LTInputTextTest {
    public static final String USERNAME = "kylesmart99";
    public static final String AUTOMATE_KEY = "I1LEPGfYzoJaUkhKlCzqhTnrrQsBrCsxtc1YxELaG2MEsY0h34";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.lambdatest.com/wd/hub";

    public static void main(String[] args) throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "Firefox");
        capabilities.setCapability("version","78.0");
        capabilities.setCapability("build", "Firefox Valid Text Input test");
        capabilities.setCapability("name", "LoadWebPage");
        capabilities.setCapability("video", true);

        WebDriver driver = new RemoteWebDriver(new URL(URL), capabilities);

        driver.get("https://internproject2020.web.app/home");

        WebElement textInputElement = driver.findElement(By.id("password"));
        textInputElement.sendKeys("password");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement buttonElement = driver.findElement(By.className("button"));
        buttonElement.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        boolean isInputValid = driver.getPageSource().contains("Thank you for inputing your password!");

        if (isInputValid)
        {
            System.out.println("Input Valid for password text input. Test Passed.");
            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
        } else {
            System.out.println("Input invalid for password text input. Test failed.");
        }

        driver.quit();
    }
}
