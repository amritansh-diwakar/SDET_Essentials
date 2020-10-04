package remoteTesting.docker.hub;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumHubTest2 {

    @Test
    public void firefox_wikipedia() throws MalformedURLException, InterruptedException {
        DesiredCapabilities cap = DesiredCapabilities.firefox();
        URL url = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(url,cap);
        driver.get("https://www.wikipedia.org//");
        Thread.sleep(30000);
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Wikipedia");
        driver.quit();
    }
}
