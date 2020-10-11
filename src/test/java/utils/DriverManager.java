package test.java.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {

    private static DriverManager instance=null;
    private static WebDriver driver;
    static ReadProperty readProperty= ReadProperty.getInstance();


    private DriverManager(){

    }

    public static DriverManager getInstance(){
        if(instance==null){
            instance = new DriverManager();
        }
        return instance;
    }

    public static WebDriver getDriver(){
        if (driver==null && instance==null) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver");
            if (System.getProperty("headless").equals(true)) {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
            }
            else{
                driver = new ChromeDriver();


            }

        }
        return driver;
    }
}
