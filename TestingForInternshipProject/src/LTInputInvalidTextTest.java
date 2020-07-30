import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

//Tests if the application throws an error when the user inputs invalid texts
//Tests if all three errors are thrown
public class LTInputInvalidTextTest {
    public static final String USERNAME = "kylesmart99";
    public static final String AUTOMATE_KEY = "I1LEPGfYzoJaUkhKlCzqhTnrrQsBrCsxtc1YxELaG2MEsY0h34";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.lambdatest.com/wd/hub";

    public static void main(String[] args) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        boolean isInputInvalid;
        int testsPassed = 0;


        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "Firefox");
        capabilities.setCapability("version","78.0");
        capabilities.setCapability("build", "Firefox Invalid Text Input test");
        capabilities.setCapability("name", "LoadWebPage");
        capabilities.setCapability("video", true);

        WebDriver driver = new RemoteWebDriver(new URL(URL), capabilities);
        driver.get("https://internproject2020.web.app/home");
        WebElement textInputElement = driver.findElement(By.id("password"));
        WebElement buttonElement = driver.findElement(By.className("button"));

        //Checks if empty text input
        textInputElement.sendKeys("");
        buttonElement.click();
        isInputInvalid = driver.getPageSource().contains("Password is required!");

        if (isInputInvalid) {
            System.out.println("Password is missing. Test passed!");
            ++testsPassed;
        } else {
            System.out.println("Password accepted. Test failed.");
        }

        //Checks if text inputted is too short
        textInputElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        textInputElement.sendKeys("passwor");
        isInputInvalid = driver.getPageSource().contains("Password must be at least 8 characters!");

        if (isInputInvalid) {
            System.out.println("Password is too short. Test passed!");
            ++testsPassed;
        } else {
            System.out.println("Password accepted. Test failed.");
        }

        //Checks if text inputted has special characters
        textInputElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        textInputElement.sendKeys("P@ssw0rd");
        isInputInvalid = driver.getPageSource().contains("Password can not have special characters!");

        if (isInputInvalid) {
            System.out.println("Password has special characters. Test passed!");
            ++testsPassed;
        } else {
            System.out.println("Password accepted. Test failed.");
        }

        //Prints out how many tests have passed.
        System.out.println("Amount of tests passed: " + testsPassed);
        if (testsPassed == 3) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
        } else {
            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
        }

        driver.quit();
    }
}
