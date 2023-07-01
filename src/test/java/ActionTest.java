import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ActionTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("http://pragmatic.bg/automation/lecture13/Config.html");
    }

    //
    @Test
    public void selectOptions() {
        WebElement airbags = driver.findElement(By.name("airbags"));
        WebElement parksensor = driver.findElement(By.name("parksensor"));

        Actions actions = new Actions(driver);
        actions.click(airbags).perform();
        actions.click(parksensor).perform();

        Assert.assertTrue(airbags.isSelected());
        Assert.assertTrue(parksensor.isSelected());

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
