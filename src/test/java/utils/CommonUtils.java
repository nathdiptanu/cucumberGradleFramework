package test.java.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.utils.DriverManager;

public class CommonUtils {
    static WebDriver driver = DriverManager.getDriver();

    public boolean clickOption(String xpathPassed) throws InterruptedException {
        WebDriverWait waitClickOption = new WebDriverWait(driver, 5);
        boolean clicked = false;
        int retryCount = 0;
        while (retryCount < 5) {
            try {
                WebElement elementToClick = waitClickOption.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathPassed)));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementToClick);
                new Actions(driver).moveToElement(elementToClick).click().build().perform();
                clicked = true;
                break;
            } catch (WebDriverException e) {
                System.out.println(e.getMessage());
                Thread.sleep(500);
            }
            retryCount++;
        }

        return clicked;

    }


    public boolean clickSendText(String xpathPassed, String textPassed, Boolean enterKey) throws InterruptedException {
        WebDriverWait clickSendText = new WebDriverWait(driver, 30);
        boolean clicked = false;
        int retryCount = 0;
        while (retryCount < 5) {
            try {
                WebElement elementToClick = clickSendText.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathPassed)));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementToClick);
                try {

                    elementToClick.sendKeys(Keys.chord(Keys.CONTROL, "a"), textPassed);
                    String value = driver.findElement(By.xpath(xpathPassed)).getAttribute("value");

                    if (value.equals(textPassed)) {
                        if (enterKey) {
                            elementToClick.sendKeys(Keys.ENTER);
                        }
                        clicked = true;
                        break;
                    }

                } catch (WebDriverException e) {
                    System.out.println(e.getMessage());
                }
            } catch (WebDriverException e) {
                System.out.println(e.getMessage());
                Thread.sleep(1000);
            }
            retryCount++;
        }

        return clicked;

    }
    public boolean isAlertPresent(){
        try{
            Alert a = new WebDriverWait(driver, 2).until(ExpectedConditions.alertIsPresent());
            if(a!=null){
                System.out.println("Alert is present");
                return true;
            }else{
                throw new Throwable();
            }
        }
        catch (Throwable e) {
            System.err.println("Alert isn't present!!");
            return false;
        }

    }
}
