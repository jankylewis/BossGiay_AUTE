package com.bossgiay.testUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigurationReader {

    Properties properties;
    public ConfigurationReader() {

        File proPath = new File("src/test/java/com/bossgiay/testEnv/conf.properties");
        try {
            FileInputStream fileInputStream = new FileInputStream(proPath);
            properties= new Properties();
            properties.load(fileInputStream);
        } catch (final Exception exception) {
            //facilitate throw exceptional malfunction
            System.out.println("\n"+ exception.getMessage()+
                    "\n"+ exception.getCause());
        }
    }

    public final String getWebApplicationBaseURL() {
        String gottenURL= properties.getProperty("baseURL");
        return gottenURL;
    }

    public final String getChromeExecutablePath() {
        String gottenChromePath= properties.getProperty("chromeExecutablePath");
        return gottenChromePath;
    }
}


