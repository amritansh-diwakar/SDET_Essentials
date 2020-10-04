package remoteTesting.docker.hub;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import util.DockerUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumHubTest1 {

    @BeforeTest
    public void dockerStartUp() throws IOException, InterruptedException {
        DockerUtil dockerUtil = new DockerUtil();
        Assert.assertTrue(dockerUtil.startDocker("src\\docker\\docker_container_up.bat","src\\docker\\terminal_output.txt"));
        dockerUtil.scaleUp("src\\docker\\scale_chrome.bat");
    }

    @AfterTest
    public void dockerTearDown() throws IOException, InterruptedException {
        DockerUtil dockerUtil = new DockerUtil();
        Assert.assertTrue(dockerUtil.stopDocker("src\\docker\\docker_container_down.bat","src\\docker\\terminal_output.txt"));
        dockerUtil.deleteFile("src\\docker\\terminal_output.txt");
    }

    @Test
    public void chrome_google() throws MalformedURLException, InterruptedException {
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        URL url = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(url,cap);
        driver.get("https://www.google.com/");
        Thread.sleep(30000);
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Google");
        driver.quit();
    }
}
