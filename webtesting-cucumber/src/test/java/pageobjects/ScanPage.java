package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ScanPage {

    WebDriver driver;

    public ScanPage(WebDriver driver){ this.driver = driver;}

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
    }

    public void get() { driver.get("https://www.zara.com/mt/");}

    public void logIn(){
        driver.findElement(By.className("_accountLink _login account-link-login")).click();
        sleep(5);

        driver.findElement(By.name("email")).sendKeys("kevinuagius@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Abcd1234");
        driver.findElement(By.id("login-button")).click();

        sleep(3);
    }

}
