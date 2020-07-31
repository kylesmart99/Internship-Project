import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;

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
        URI uri = new URI("https://kylesmart1:uQfjJZMYibyy5zpR8dzq@api.browserstack.com/automate/sessions/" + ((RemoteWebDriver)driver).getSessionId().toString() + ".json");
        HttpPut putRequest = new HttpPut(uri);
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        driver.get("https://internproject2020.web.app/home");

        System.out.println("Started on home page.");

        List<WebElement> homePageButtons = new ArrayList<>(driver.findElements(By.className("button")));
        homePageButtons.get(1).click();

        if(driver.getCurrentUrl().equals(buttonUrl)) {
            System.out.println("Buttons Page loaded.");
        } else {
            System.out.println("Buttons page failed to load. Test failed.");
            nameValuePairs.add((new BasicNameValuePair("status", "failed")));
            nameValuePairs.add((new BasicNameValuePair("reason", "Did not go to the button page properly.")));
            driver.quit();
        }

        WebElement buttonsPageButton = driver.findElement(By.className("button"));
        buttonsPageButton.click();

        if(driver.getCurrentUrl().equals(homeUrl)) {
            System.out.println("Home Page loaded. Test passed.");
            nameValuePairs.add((new BasicNameValuePair("status", "passed")));
            nameValuePairs.add((new BasicNameValuePair("reason", "")));
        } else {
            System.out.println("Home page failed to load. Test failed.");
            nameValuePairs.add((new BasicNameValuePair("status", "failed")));
            nameValuePairs.add((new BasicNameValuePair("reason", "Did not go back to the home page properly.")));
        }

        putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpClientBuilder.create().build().execute(putRequest);

        driver.close();
        driver.quit();
    }
}
