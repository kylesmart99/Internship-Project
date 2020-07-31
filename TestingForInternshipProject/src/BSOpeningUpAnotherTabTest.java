import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;

//This tests if a extra tab opens up after the stalemate button is clicked.
//Also checks if the correct page opens up.
public class BSOpeningUpAnotherTabTest {
    public static final String USERNAME = "kylesmart1";
    public static final String AUTOMATE_KEY = "uQfjJZMYibyy5zpR8dzq";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void main(String[] args) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        String newUrl = "https://en.wikipedia.org/wiki/%C3%89douard_Henry";
        List<WebElement> buttonsOnPage;
        WebElement stalemateButton;

        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Firefox");
        caps.setCapability("browser_version", "79.0 beta");
        caps.setCapability("name", "Firefox New Tab Opening test");

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        URI uri = new URI("https://kylesmart1:uQfjJZMYibyy5zpR8dzq@api.browserstack.com/automate/sessions/" + ((RemoteWebDriver)driver).getSessionId().toString() + ".json");
        HttpPut putRequest = new HttpPut(uri);
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

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
            nameValuePairs.add((new BasicNameValuePair("status", "passed")));
            nameValuePairs.add((new BasicNameValuePair("reason", "")));
        } else {
            System.out.println("Tab failed to load. Test failed.");
            nameValuePairs.add((new BasicNameValuePair("status", "failed")));
            nameValuePairs.add((new BasicNameValuePair("reason", "Tab failed to load.")));
        }

        putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpClientBuilder.create().build().execute(putRequest);

        driver.close();
        driver.quit();
    }
}
