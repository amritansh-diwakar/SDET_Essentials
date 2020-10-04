package remoteTesting.docker.hub;

import org.testng.Assert;
import org.testng.annotations.Test;
import util.DockerUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class StartDocker {
    @Test
    public void testDocker() throws IOException, InterruptedException {
        DockerUtil dockerUtil = new DockerUtil();
        Assert.assertTrue(dockerUtil.startDocker("src\\docker\\docker_container_up.bat","src\\docker\\terminal_output.txt"));
        dockerUtil.scaleUp("src\\docker\\scale_chrome.bat");
        Assert.assertTrue(dockerUtil.stopDocker("src\\docker\\docker_container_down.bat","src\\docker\\terminal_output.txt"));
        dockerUtil.deleteFile("src\\docker\\terminal_output.txt");
    }
}
