package test.java.pojo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import test.java.utils.CommonUtils;

/**
 * Page Object encapsulates the Sign-in page.
 */
public class LoginPojo {
    protected static WebDriver driver;
    CommonUtils commonUtils=new CommonUtils();
    private By usernameBy = By.name("username");
    private By passwordBy = By.name("password");
    private By signinBy = By.name("login");

    public LoginPojo(WebDriver driver){
        this.driver = driver;
    }


    public boolean  loginValidUser(String userName, String password) {
        try {
            driver.findElement(usernameBy).clear();
            driver.findElement(usernameBy).sendKeys(userName);
            driver.findElement(passwordBy).clear();
            driver.findElement(passwordBy).sendKeys(password);
            driver.findElement(signinBy).click();
            return true;
        }
        catch (WebDriverException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}