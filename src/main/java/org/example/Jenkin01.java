package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class Jenkin01 {

    ChromeOptions options;
    WebDriver driver;

    @BeforeSuite
    public void setUp(){
        options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);

    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("TC#1 - Verify that with Invalid username and Valid password, Login is not sucessfull !!")


    public void testInvalidLogin() throws InterruptedException {

        driver.get("https://app.vwo.com/#/login");
        driver.findElement(By.id("login-username")).sendKeys("paxiki7049@huizk.co");
        driver.findElement(By.id("login-password")).sendKeys("Test12345!");
        driver.findElement(By.id("js-login-btn")).click();


        // Thread will stop the JVM
        //Thread.sleep(3000);

        //Explicit wait
        WebElement errorMessage = driver.findElement(By.className("notification-box-description"));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(errorMessage));

//     //Fluent wait
//        Wait<WebDriver> wait = new FluentWait<>(driver)
//                .withTimeout(Duration.ofSeconds(40))
//                .pollingEvery(Duration.ofSeconds(5))
//                .ignoring(org.openqa.selenium.NoSuchElementException.class);
//
//        WebElement errorMessage2 = wait.until(new Function<WebDriver, WebElement>() {
//            @Override
//            public WebElement apply(WebDriver driver) {
//                return driver.findElement(By.className("notification-box-description"));
//            }
//        });

//
        String errorString = driver.findElement(By.className("notification-box-description")).getText();
        Assert.assertEquals(errorMessage.getText(),"Your email, password, IP address or location did not match");

    }

    @Test
    @Description("Verify that with Invalid username and Valid password, Login is sucessfull !!")
    public void testValidLogin() throws InterruptedException {

        driver.get("https://app.vwo.com/#/login");
        clearField(driver.findElement(By.id("login-username")));
        driver.findElement(By.id("login-username")).sendKeys("paxiki7049@huizk.com");
        driver.findElement(By.id("login-password")).sendKeys("Test12345!");
        //  driver.findElement(By.id("recaptcha-anchor")).click();
        driver.findElement(By.id("js-login-btn")).click();

       // Thread.sleep(3000);

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.page-heading")));

        Assert.assertEquals(driver.getCurrentUrl(),("https://app.vwo.com/#/dashboard"));

    }
    @AfterSuite
    public void tearDown(){
        driver.quit();

    }
    private void clearField(WebElement element){
        element.clear();
    }
}
