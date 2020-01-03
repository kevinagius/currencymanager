package test.nextDefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

public class NextStepDefs {

    static WebDriver browser;

    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
    }

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/users/Kevin/Downloads/chromedriver.exe");
        browser = new ChromeDriver();
    }

    @After
    public void teardown() {
        browser.quit();
    }

    @Given("I am a user on the website")
    public void iAmAUserOnTheWebsite() {
        browser.get("https://www.zara.com/mt/en/logon");
    }

    @When("I log in using valid credentials")
    public void iLogInUsingValidCredentials() {
        browser.findElement(By.name("email")).sendKeys("kevinuagius@gmail.com");
        browser.findElement(By.name("password")).sendKeys("Abcd1234");
        browser.findElement(By.id("login-button")).click();
        sleep(5);
    }

    @Then("I should be logged in")
    public void iShouldBeLoggedIn() {
        assertTrue(browser.findElement(By.linkText("KEVIN")).isDisplayed());
    }

    @When("I log in using invalid credentials")
    public void iLogInUsingInvalidCredentials() {
        browser.findElement(By.name("email")).sendKeys("wrong@email.com");
        browser.findElement(By.name("password")).sendKeys("wrong");
        browser.findElement(By.id("login-button")).click();
        sleep(2);
    }

    @Then("I should not be logged in")
    public void iShouldNotBeLoggedIn() {
        assertTrue(browser.findElement(By.id("password-error")).isDisplayed());
    }

    @Given("I am a logged in user on the website")
    public void iAmALoggedInUserOnTheWebsite(){
        browser.get("https://www.zara.com/mt/en/logon");
        browser.findElement(By.name("email")).sendKeys("kevinuagius@gmail.com");
        browser.findElement(By.name("password")).sendKeys("Abcd1234");
        browser.findElement(By.id("login-button")).click();
        sleep(5);
    }

    public void emptyCart(){
        browser.findElement(By.className("_mini-shop-cart-link")).click();
        sleep(3);
        browser.findElement(By.className("typography--xxs")).click();
        sleep(1);

        //Go back home
        browser.findElement(By.className("layout-header__logo-icon")).click();
        sleep(3);

    }

    @When("I search for a product")
    public void iSearchForAProduct(){
        browser.findElement(By.className("_searchButton")).click();
        sleep(3);

        browser.findElement(By.name("searchTerm")).sendKeys("coat");
        sleep(3);
    }

    @And("I select the first product in the list")
    public void iSelectTheFirstProductInTheList(){
        browser.findElement(By.id("product-34721740")).click();
        sleep(3);
    }

    @Then("I should see the product details")
    public void iShouldSeeTheProductDetails(){
        assertTrue(browser.findElement(By.linkText("SHORT COAT WITH BUTTONS")).isDisplayed());
    }

    @And("My shopping cart is empty")
    public void myShoppingCartIsEmpty() {
        assertEquals(browser.findElement(By.className("_mini-shop-cart-quantity")).getText(), "0");
    }

    @When("I view the details of a product")
    public void iViewTheDetailsOfAProduct(){
        browser.findElement(By.className("_searchButton")).click();
        sleep(5);

        browser.findElement(By.name("searchTerm")).sendKeys("bracelet");
        sleep(5);

        browser.findElement(By.id("product-34150014")).click();
        sleep(5);


    }

    @And("I choose to buy the product")
    public void iChooseToBuyTheProduct(){
        browser.findElement(By.className("_add-to-cart-btn")).click();
        sleep(5);
    }

    @Then("My shopping cart should contain 1 item")
    public void myShoppingCartShouldContain1Item(){
        browser.findElement(By.className("layout-header__logo-icon")).click();
        sleep(3);

        assertEquals(browser.findElement(By.className("_mini-shop-cart-quantity")).getText(), "1");
    }

    @When("I add <num-products> products to my shopping cart")
    public void iAddProductsToMyShoppingCart(){

    }

    @Then("My shopping cart should contain <num-products> items")
    public void myShoppingCartShouldContainItems(){

    }

    @And("My shopping cart has 2 items")
    public void myShoppingCartHasTwoItems(){
        emptyCart();

        iViewTheDetailsOfAProduct();

        //Adding the 2 items
        browser.findElement(By.className("_add-to-cart-btn")).click();
        sleep(3);
        browser.findElement(By.className("_add-to-cart-btn")).click();
        sleep(3);

        // Verify 2 items
        assertEquals(browser.findElement(By.className("_mini-shop-cart-quantity")).getText(), "2");

        // Go to cart
        browser.findElement(By.className("_mini-shop-cart-link")).click();
        sleep(3);
    }

    @When("I remove the first product in my cart")
    public void iRemoveTheFirstProductInMyCart(){
        browser.findElement(By.className("shop-cart-item-amount__decrease")).click();
        sleep(5);

    }
}