import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class testingGospodarPrstenova {
    private WebDriver driver;
    private WebDriverWait chromeWait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        chromeWait = new WebDriverWait(driver, 20);
        driver.navigate().to("http://www.gospodarprstenova.com/");
    }

    @Test
    public void test1_SignInWrongPassword() throws Exception {
        driver.findElement(By.xpath("(//a[contains(text(),'Korisnički račun')])[2]")).click();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("shalligator@gmail.com");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("kamehameha323");
        driver.findElement(By.name("login")).click();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Narukvice')])[2]")));
        driver.quit();
    }

    @Test
    public void test2_SignIn() throws Exception {
        signIn();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Narukvice')])[2]")));
        driver.quit();
    }

    @Test
    public void test3_SignOut() throws Exception {
        signIn();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Odjava')])[2]")));
        driver.findElement(By.xpath("(//a[contains(text(),'Odjava')])[2]")).click();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Narukvice')])[2]")));
        driver.quit();
    }

    @Test
    public void test4_AddToCart() throws Exception {
        signIn();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Narukvice')])[2]")));
        driver.findElement(By.xpath("(//a[contains(text(),'Narukvice')])[2]")).click();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src,'http://www.gospodarprstenova.com/wp-content/uploads/2019/04/s-l1600-7-175x200.jpg')]")));
        driver.findElement(By.xpath("//img[contains(@src,'http://www.gospodarprstenova.com/wp-content/uploads/2019/04/s-l1600-7-175x200.jpg')]")).click();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("add-to-cart")));
        driver.findElement(By.name("add-to-cart")).click();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Prstenje')])[2]")));
        driver.findElement(By.xpath("(//a[contains(text(),'Prstenje')])[2]")).click();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src,'http://www.gospodarprstenova.com/wp-content/uploads/2019/04/s-l500-3-175x200.jpg')]")));
        driver.findElement(By.xpath("//img[contains(@src,'http://www.gospodarprstenova.com/wp-content/uploads/2019/04/s-l500-3-175x200.jpg')]")).click();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pa_velicin")));
        driver.findElement(By.id("pa_velicin")).click();
        new Select(driver.findElement(By.id("pa_velicin"))).selectByVisibleText("8");
        driver.findElement(By.id("pa_velicin")).click();
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Ogrlice')])[2]")));
        driver.findElement(By.xpath("(//a[contains(text(),'Ogrlice')])[2]")).click();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src,'http://www.gospodarprstenova.com/wp-content/uploads/2019/01/cover_celtic-mystery-trinity-triquetra-knot-necklace-ogrlica-nakit-goth-gothic-gospodar-prstenova-1-175x200.jpg')]")));
        driver.findElement(By.xpath("//img[contains(@src,'http://www.gospodarprstenova.com/wp-content/uploads/2019/01/cover_celtic-mystery-trinity-triquetra-knot-necklace-ogrlica-nakit-goth-gothic-gospodar-prstenova-1-175x200.jpg')]")).click();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("add-to-cart")));
        driver.findElement(By.name("add-to-cart")).click();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src,'http://www.gospodarprstenova.com/wp-content/uploads/2019/01/cover_celtic-mystery-trinity-triquetra-knot-necklace-ogrlica-nakit-goth-gothic-gospodar-prstenova-1-175x200.jpg')]")));
        driver.quit();
    }

    @Test
    public void test5_RemoveFromCart() throws Exception {
        signIn();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("3 Items")));
        driver.findElement(By.linkText("3 Items")).click();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"post-19\"]/div/div/form/table/tbody/tr[1]/td[1]/a")));
        driver.findElement(By.xpath("//*[@id=\"post-19\"]/div/div/form/table/tbody/tr[1]/td[1]/a")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"post-19\"]/div/div/form/table/tbody/tr[2]/td[1]/a")).click();
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void test6_Checkout() throws Exception {
        signIn();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("1 Item")));
        driver.findElement(By.linkText("1 Item")).click();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Kreni na plaćanje")));
        driver.findElement(By.linkText("Kreni na plaćanje")).click();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("billing_first_name")));
        driver.findElement(By.id("billing_first_name")).click();
        driver.findElement(By.id("billing_first_name")).clear();
        driver.findElement(By.id("billing_first_name")).sendKeys("Pero");
        driver.findElement(By.id("billing_last_name")).click();
        driver.findElement(By.id("billing_last_name")).clear();
        driver.findElement(By.id("billing_last_name")).sendKeys("Perić");
        driver.findElement(By.id("billing_address_1")).click();
        driver.findElement(By.id("billing_address_1")).clear();
        driver.findElement(By.id("billing_address_1")).sendKeys("Ulica 45");
        driver.findElement(By.id("billing_city")).click();
        driver.findElement(By.id("billing_city")).clear();
        driver.findElement(By.id("billing_city")).sendKeys("Grad");
        driver.findElement(By.id("billing_state")).click();
        driver.findElement(By.id("billing_state")).clear();
        driver.findElement(By.id("billing_state")).sendKeys("Gradsko-riječna");
        driver.findElement(By.id("billing_postcode")).click();
        driver.findElement(By.id("billing_postcode")).clear();
        driver.findElement(By.id("billing_postcode")).sendKeys("55555");
        driver.findElement(By.id("billing_phone")).click();
        driver.findElement(By.id("billing_phone")).clear();
        driver.findElement(By.id("billing_phone")).sendKeys("018666555");
        driver.quit();
    }



    private void signIn() throws Exception {
        driver.findElement(By.xpath("(//a[contains(text(),'Korisnički račun')])[2]")).click();
        chromeWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("shalligator@gmail.com");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("kamehameha32");
        driver.findElement(By.name("login")).click();
    }
}
