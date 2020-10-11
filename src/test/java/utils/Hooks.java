package test.java.utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import test.java.utils.DriverManager;
import test.java.utils.ReadProperty;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Hooks implements En {

    public Hooks(){
        AfterStep((Scenario scenario) -> {
            System.out.println("Do something after each Step");
        });
    }
    static WebDriver driver= DriverManager.getDriver();
    static ReadProperty readProperty= ReadProperty.getInstance();

    @Before
    public static void openBrowser(){
        driver.get(String.valueOf(readProperty.get("applicationUrl")));
    }

    @AfterClass
    public static void runAfterExecution() throws IOException {
        /*
        Commenting Send email for now
         */
        MailUtils.senEmail();
        driver.quit();
        System.out.println("Send email");
    }

    @After
    public void before_or_after(io.cucumber.java.Scenario scenario) {
        System.out.println("Screenshot taken");
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "snapshot");
        }
    }
}
