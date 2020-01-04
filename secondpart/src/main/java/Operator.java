import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Operator {
        private boolean signedOut = true, signedIn = false, viewSearches = false, viewProduct = false, viewCart = false, checkOut = false;

        public static WebDriver driver;

        public static void sleep(int seconds) {
                try {
                        Thread.sleep(seconds*1000);
                } catch (Exception e) {}
        }

        public void setDriver(){
                System.setProperty("webdriver.chrome.driver", "/users/Kevin/Downloads/chromedriver.exe");
                driver = new ChromeDriver();

                driver.get("https://www.zara.com/mt/en/logon");
        }



        boolean logIn(){
                // Go back to home
                if(driver == null){
                        setDriver();

                }

                if(signedOut == true){
                        //driver.findElement(By.className("_login account-link-login")).click();
                        sleep(2);

                        driver.findElement(By.name("email")).sendKeys("kevinuagius@gmail.com");
                        driver.findElement(By.name("password")).sendKeys("Abcd1234");
                        driver.findElement(By.id("login-button")).click();
                        sleep(2);


                        signedIn = true;
                        signedOut = false;
                }

                return true;
        }

        boolean logOut(){
                driver.findElement(By.className("layout-header__logo-icon")).click();
                sleep(2);

                if(signedIn = true){
                        driver.findElement(By.className("_userName")).click();
                        sleep(2);

                        driver.findElement(By.className("_logout")).click();
                        sleep(2);

                        signedIn = false;
                        signedOut = true;
                }

                return true;
        }

        boolean search(){
                if((viewCart = true )|| (checkOut = true)){
                        //Go to home
                        driver.findElement(By.className("layout-header__logo-icon")).click();
                        sleep(2);
                        viewCart = false;
                        checkOut = false;
                }


                driver.findElement(By.className("_searchButton")).click();
                sleep(3);

                driver.findElement(By.name("searchTerm")).sendKeys("bracelet");
                sleep(3);


                viewSearches = true;
                return true;
        }

        boolean viewProduct(){
                if(viewSearches = true){
                        driver.findElement(By.className("_imageLoaded")).click();
                        sleep(2);

                        viewSearches = false;
                        viewProduct = true;
                }

                return true;
        }

        boolean addProduct(){
                if(viewProduct = true){
                        driver.findElement(By.className("_add-to-cart-btn")).click();
                        sleep(2);
                }

                return true;
        }

        boolean removeProduct(){
                if(viewCart = false){
                        driver.findElement(By.className("_mini-shop-cart-link")).click();
                        sleep(3);
                        viewCart = true;
                }

                if(driver.findElement(By.className("_units")).getText().equals("1")){
                        emptyCart();
                }else {
                        driver.findElement(By.className("shop-cart-item-amount__decrease")).click();
                        sleep(5);
                }

                return true;
        }

        boolean viewCart(){
                if(viewCart = false){
                        driver.findElement(By.className("layout-header__logo-icon")).click();
                        sleep(2);

                        driver.findElement(By.className("_mini-shop-cart-link")).click();
                        sleep(3);

                        viewCart = true;
                } else {

                        viewCart = true;

                }


                return true;
        }

        boolean emptyCart(){
                if(viewCart = false){
                        //Go to cart
                        driver.findElement(By.className("_mini-shop-cart-link")).click();
                        sleep(3);
                        viewCart = true;
                }

                //Click the delete
                driver.findElement(By.className("typography--xxs")).click();
                sleep(1);
                //Then go to home
                driver.findElement(By.className("layout-header__logo-icon")).click();
                sleep(2);


                return true;
        }

        boolean checkOut(){
                if(viewCart = false && !(driver.findElement(By.className("_mini-shop-cart-quantity")).getText().equals("0"))){
                        //Go to cart
                        driver.findElement(By.className("_mini-shop-cart-link")).click();
                        sleep(3);
                        viewCart = true;
                } else {
                        driver.findElement(By.className("layout-header__logo-icon")).click();
                        sleep(2);
                }

                if((viewCart = true) && (signedIn = true)){
                        driver.findElement(By.className("button shop-cart-next-button ")).click();
                        viewCart = false;
                        checkOut = true;
                } else{
                        driver.findElement(By.className("button shop-cart-next-button ")).click();
                        driver.findElement(By.name("email")).sendKeys("kevinuagius@gmail.com");
                        driver.findElement(By.name("password")).sendKeys("Abcd1234");
                        driver.findElement(By.id("login-button")).click();
                        sleep(2);

                        checkOut = true;
                        signedIn = true;
                }

                return true;
        }

        public boolean isCheckOut() {return checkOut;}
        public boolean isSignedIn() {return signedIn;}
        public boolean isSignedOut() {return signedOut;}
        public boolean isViewCart() {return viewCart;}
        public boolean isViewProduct() {return viewProduct;}
        public boolean isViewSearches() {return viewSearches;}
}





