package selenium.practice.excel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SelPopUpTest
{
    String url1;
    String url;
    WebDriver driver;

    @Before
    public void setUp() throws Exception
    {
        String username = "admin";
        String password = "admin";
        //url1 = "http://the-internet.herokuapp.com/";
        url = "http://" + username + ":" + password +"@the-internet.herokuapp.com/";
        System.setProperty("webdriver.chrome.driver", "/home/prashant_pk/IdeaProjects/Selevium_Test/driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get(url);
        driver.manage().window().maximize();
        System.out.println("Title of the Page: " +driver.getTitle());
    }

    @Test
    public void linkTest() throws Exception
    {
        Thread.sleep(2000);
        String expTitlePage = "The Internet";
        String actPageTitle = driver.getTitle();
        Assert.assertEquals(actPageTitle, expTitlePage);

        Thread.sleep(1000);
        driver.findElement(By.linkText("Basic Auth")).click();
        Thread.sleep(2000);
//        String message = driver.switchTo().alert().getText();
//        System.out.println("Alert Message: " + message);
        //driver.get(url);
//        driver.switchTo().alert().sendKeys("username");
//        Thread.sleep(2000);
//        driver.switchTo().alert().sendKeys("Password123");
//        Thread.sleep(2000);
//        driver.switchTo().alert().accept();
        Thread.sleep(2000);

        /*
        // Handling PopUps in the Selenium!
        driver.findElement(By.xpath("Path for Alert Button")).click();
        String message = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept(); // To Accept the Alert.
        driver.switchTo().alert().dismiss(); // To confirm No for the Alert.
        driver.switchTo().alert().sendKeys("Value"); // If the Alert asks for some value.
         */
    }

    @After
    public void tearDown() throws Exception
    {
        driver.quit();
    }
}
