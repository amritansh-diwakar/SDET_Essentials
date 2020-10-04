package remoteTesting.docker;

import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeStandAloneTest {

    @Test
    public void test1() throws MalformedURLException {
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        URL url = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(url,cap);
        driver.get("https://www.google.com/");
        System.out.println(driver.getTitle());
    }
}
