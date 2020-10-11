package test.java.stepDef;

import io.cucumber.java8.En;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import test.java.pojo.LoginPojo;
import test.java.utils.CommonUtils;
import test.java.utils.DriverManager;

import java.io.IOException;
/*
Go through Readme.md for more details
 */
import static test.java.utils.HttpUtil.readExcel;

public class LoginStepDef implements En {
    static WebDriver driver = DriverManager.getDriver();
    static CommonUtils commonUtils = new CommonUtils();
    private static Response response;
    //creating PageObject
    LoginPojo loginPojo = new LoginPojo(driver);


    public LoginStepDef() throws IOException {
        /*
        Parse json tutorial https://rest-assured.io/
                    sample example to send a query to the API
                    response = readExcel("a == 1 & b == 2", null);
                    response.jsonPath().getString("d[0]");
         */

        Given("user opens application url", () -> {
            //application started from Hooks->Before class
            //Change implementation if required

        });

        When("user enters user credentials", () -> {
            //Reading data from excel file using Rest API
            response = readExcel(null, null);
            Assert.assertTrue(loginPojo.loginValidUser(response.jsonPath().getString("username[0]"),response.jsonPath().getString("password[0]")));



        });

        Then("verify successful operation", () -> {
            try{
                driver.switchTo().alert().accept();
            }
            catch(WebDriverException e){
                System.out.println(driver.findElement(By.xpath("//p[contains(text(),'Invalid Credentials. Please try again')]")).getText());;

            }
        });

        When("user enters user credentials for {string} and {string}", (String username,String password) -> {

            Assert.assertTrue(loginPojo.loginValidUser(username,password));


        });

            Then("verify successful message {string}", (String message) -> {
                    if (commonUtils.isAlertPresent()){
                        Assert.assertEquals("Text on UI matches with expected",message,driver.switchTo().alert().getText());
                        driver.switchTo().alert().accept();
                    }
                    else{
                    Assert.assertEquals("Text on UI matches with expected",message,driver.findElement(By.xpath("//p[contains(text(),'Invalid Credentials. Please try again')]")).getText());

                }
            });

    }


}
