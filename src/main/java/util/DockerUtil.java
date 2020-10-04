package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class DockerUtil {
    public boolean startDocker(String dockerUpFile, String outputFile) throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("cmd /c start " + dockerUpFile);
        return waitUntilTextRead(outputFile, "The node is registered to the hub and ready to use");
    }

    public void scaleUp(String scaleFile) throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("cmd /c start " + scaleFile);
        Thread.sleep(15000);
    }

    public boolean stopDocker(String dockerDownFile, String outputFile) throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("cmd /c start " + dockerDownFile);
        return waitUntilTextRead(outputFile, "selenium-hub exited");
    }

    public void deleteFile(String outputFile) throws InterruptedException {
        boolean deleteFlag = false;
        while(!deleteFlag){
            File f = new File(outputFile);
            deleteFlag = f.delete();
        }
        System.out.println("File Deleted");
    }

    public boolean waitUntilTextRead(String filename, String text) throws IOException, InterruptedException {
        boolean flag = false;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 45);
        long stopnow = cal.getTimeInMillis();
        //basic wait for output file to create
        Thread.sleep(3000);

        while(System.currentTimeMillis() < stopnow){
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String currentLine = reader.readLine();
            while(currentLine!=null){
                if (currentLine.contains(text)){
                    flag = true;
                    System.out.println("Found in file: " + text);
                    break;
                }
                currentLine = reader.readLine();
            }
            reader.close();
            if (flag==true){
                break;
            }
        }
        return flag;
    }
}
