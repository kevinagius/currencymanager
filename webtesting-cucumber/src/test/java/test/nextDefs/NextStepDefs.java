package pageobjects;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NextStepDefs {

    WebDriver driver;

    public NextStepDefs(WebDriver driver){ this.driver = driver;}

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
    }

    public void get() { driver.get("https://www.zara.com/mt/");}

    public boolean loggedIn(){

        boolean loginstate = false;

        if (driver.findElement(By.linkText("LOG IN")).isDisplayed()){
            loginstate = false;

        }else{
            loginstate = true;

        }

        return loginstate;
    }

    public void logIn(){
        driver.findElement(By.className("_accountLink _login account-link-login")).click();
        sleep(5);

        driver.findElement(By.name("email")).sendKeys("kevinuagius@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Abcd1234");
        driver.findElement(By.id("login-button")).click();

        sleep(3);
    }


    @Before
    public void setup() throws Exception{
        System.setProperty("webdriver.chrome.driver", "./chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.zara.com/mt/");
    }

    @After
    public void teardown(){
        driver.quit();
    }

    @Given("I am a user on the website")
    public void user(){

    }

    @When("I log in using valid credentials")
    public void valid(){
        //Setup
        logIn();

        //Exercise
        String verify = driver.findElement(By.className("_accountLink _userName")).getText();

        //Verify
        assertEquals(verify,"Kevin");
    }

    @Then("I should be logged in")
    public void then(){
        assertEquals(loggedIn(), true);
    }
}
