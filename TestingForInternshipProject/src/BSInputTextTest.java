import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;

//Tests if the application lets the user input valid text.
public class BSInputTextTest {
    public static final String USERNAME = "kylesmart1";
    public static final String AUTOMATE_KEY = "uQfjJZMYibyy5zpR8dzq";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void main(String[] args) throws Exception{
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Firefox");
        caps.setCapability("browser_version", "79.0 beta");
        caps.setCapability("name", "Firefox Valid Text Input test");

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        URI uri = new URI("https://kylesmart1:uQfjJZMYibyy5zpR8dzq@api.browserstack.com/automate/sessions/" + ((RemoteWebDriver)driver).getSessionId().toString() + ".json");
        HttpPut putRequest = new HttpPut(uri);
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

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
            nameValuePairs.add((new BasicNameValuePair("status", "passed")));
            nameValuePairs.add((new BasicNameValuePair("reason", "")));
        } else {
            System.out.println("Input invalid for password text input. Test failed.");
            nameValuePairs.add((new BasicNameValuePair("status", "failed")));
            nameValuePairs.add((new BasicNameValuePair("reason", "Failed to accept a valid password.")));
        }

        putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpClientBuilder.create().build().execute(putRequest);

        driver.close();
        driver.quit();
    }
}
