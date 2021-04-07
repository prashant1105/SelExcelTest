package selenium.practice.excel;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SelenXlsLogin
{

    String url;
    WebDriver driver;

    @Before
    public void setUp() throws Exception
    {
        url = "https://phptravels.com/demo/";
        System.setProperty("webdriver.chrome.driver", "/home/prashant_pk/IdeaProjects/Selevium_Test/driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get(url);
        driver.manage().window().maximize();
//        System.out.println(driver.getTitle());
    }

    @Test
    public void linkTest() throws Exception
    {
        String expPageTitle = "Demo Script Test drive - PHPTRAVELS";
        String actPageTitle = driver.getTitle();
        Assert.assertEquals(actPageTitle, expPageTitle);

        Thread.sleep(1000);
        driver.findElement(By.linkText("Login")).click();
        Thread.sleep(1000);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Set<String> windHand = driver.getWindowHandles();
        Iterator<String> itr = windHand.iterator();
        String homePage = itr.next();
        String logPage = itr.next();
        driver.switchTo().window(logPage);

        String projectPath = System.getProperty("user.dir");
        String filePath = "/home/prashant_pk/IdeaProjects/SelExcelTest/Excel/Book1.xls";
        FileInputStream fileStream = new FileInputStream(filePath);
//        System.out.println("Project Path: " +projectPath);

        HSSFWorkbook workBook = new HSSFWorkbook(fileStream);
        HSSFSheet wbSheet = workBook.getSheet("Sheet2");
        int rowCount = wbSheet.getPhysicalNumberOfRows();
        System.out.println("Count of Rows: " +rowCount);

        for(int i = 1; i < rowCount; i++)
        {
            String username = wbSheet.getRow(i).getCell(0).getStringCellValue();
            String password = wbSheet.getRow(i).getCell(1).getStringCellValue();

            driver.findElement(By.id("inputEmail")).sendKeys(username);
            Thread.sleep(1000);
            driver.findElement(By.id("inputPassword")).sendKeys(password);
            Thread.sleep(1000);

            WebElement checkBox = driver.findElement(By.cssSelector("#main-body > div > div > div.col-xs-12.main-content > div > div.row > div.col-sm-12 > form > div.checkbox > label > input[type=checkbox]"));
            checkBox.click();
            Thread.sleep(1000);

            driver.findElement(By.id("login")).click();
            Thread.sleep(1000);

            String titlePage = driver.getTitle();
            // Getting the current row in the sheet where we want to write the data!
            Row newRow = wbSheet.getRow(i);

            // Creating a new cell with reference to the row!
            Cell cell = newRow.createCell(2);
            // Giving the value in the cell!
            cell.setCellValue(titlePage);

            // Typecasting the driver reference variable with TakesScreenshot!
            // Typecasting will allow the methods from TakesScreenshot interface to access the file!
            // getScreenshotAs() will take argument for the output type of the file!
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            // Using the FileUtils class, we can copy the generated screenshot file to any location!
            FileUtils.copyFile(scrFile, new File(projectPath+ "/Screenshots/SelSheetLogin/Screenshot_" +i));

            List<WebElement> elementCheck = driver.findElements(By.linkText("Logout"));
            if(elementCheck.size() != 0)
            {
                System.out.println("Element is present!");
                driver.findElement(By.linkText("Logout")).click();
                Thread.sleep(2000);
                driver.findElement(By.linkText("Click here to continue..."));
                Thread.sleep(2000);
            }
            else
            {
                System.out.println("Element is not present!");
                driver.get("https://phptravels.org/clientarea.php");
            }
        }

        //Creating an output stream with the location where we want to create the file!
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);

        //Writing the workbook!
        workBook.write(fileOutputStream);

        //Closing the workbook!
        workBook.close();

    }

    @After
    public void tearDown() throws Exception
    {
        driver.quit();
    }
}