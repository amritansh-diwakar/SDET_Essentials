package remoteTesting.docker.hub;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumHubTest3 {

    @Test
    public void chrome_dictionary() throws MalformedURLException, InterruptedException {
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        URL url = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(url,cap);
        driver.get("https://www.dictionary.com/");
        Thread.sleep(30000);
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Dictionary.com | Meanings and Definitions of Words at Dictionary.com");
        driver.quit();
    }
}
