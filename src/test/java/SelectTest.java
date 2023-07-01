import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SelectTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("http://shop.pragmatic.bg/admin");

    }

    @BeforeMethod
    public void logIn() {
        WebElement username = driver.findElement(By.id("input-username"));
        username.sendKeys("admin");

        WebElement password = driver.findElement(By.id("input-password"));
        password.sendKeys("parola123!");

        WebElement button = driver.findElement(By.className("btn"));
        button.click();

        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(actualPageTitle, "Dashboard");

    }

    @Test
    public void selectList() {

        WebElement saleMenu = driver.findElement(By.id("menu-sale"));
        saleMenu.click();

        WebElement orders = driver.findElement(By.linkText("Orders"));
       // WebElement orders = driver.findElement(By.cssSelector('#collapse4>li[class=\'active\']>a'));
        orders.click();

        Select orderStatus = new Select(driver.findElement(By.id("input-order-status")));
        Assert.assertFalse(orderStatus.isMultiple());

        List<WebElement> allOptions = orderStatus.getOptions();
        Assert.assertEquals(allOptions.size(), 16);

        for (WebElement options: allOptions) {
            System.out.println(options.getText());
        }

        orderStatus.selectByIndex(2);
        Assert.assertEquals(orderStatus.getFirstSelectedOption().getText(), "Canceled");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
