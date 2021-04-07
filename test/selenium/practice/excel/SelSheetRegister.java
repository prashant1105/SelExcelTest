package selenium.practice.excel;

//import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SelSheetRegister
{
    String url;
    WebDriver driver;

    @Before
    public void setup() throws Exception
    {
        url = "https://phptravels.com/";
        System.setProperty("webdriver.chrome.driver", "/home/prashant_pk/IdeaProjects/Selevium_Test/driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        // Opening the Web Page in the Chrome Browser!
        driver.manage().window().maximize();
        driver.get(url);
        System.out.println("Title of the Web Page is: " +driver.getTitle());
    }

    @Test
    public void linkTest() throws Exception
    {
        driver.findElement(By.linkText("Login")).click();
        Thread.sleep(2000);
        // This will open the page in a new Window.
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        Set<String> winHandles = driver.getWindowHandles();
        Iterator<String> itWindow = winHandles.iterator();
        String homePage = itWindow.next().toString();
        String logPage = itWindow.next().toString();
        driver.switchTo().window(logPage);

        driver.findElement(By.linkText("Register")).click();
        Thread.sleep(2000);

//        File source = new File("/home/prashant_pk/IdeaProjects/Selevium_Test/Excel/Book1.xlsx");
//        FileInputStream fileInput = new FileInputStream(source);
//        XSSFWorkbook workBook = new XSSFWorkbook(fileInput);
//        XSSFSheet wbSheet = workBook.getSheet("Sheet1");
//        int rowCount = wbSheet.getPhysicalNumberOfRows();
//        System.out.println("Count of Rows: " +rowCount);

        String projectPath = System.getProperty("user.dir");
        System.out.println("Project Path: " +projectPath);
        XSSFWorkbook workBook = new XSSFWorkbook(projectPath+"/Excel/Book1.xlsx");
        XSSFSheet wbSheet = workBook.getSheet("Sheet1");
        int rowCount = wbSheet.getPhysicalNumberOfRows();
        System.out.println("Count of Rows: " +rowCount);

        String data0 = wbSheet.getRow(1).getCell(0).getStringCellValue();
        String data1 = wbSheet.getRow(1).getCell(1).getStringCellValue();
        String data2 = wbSheet.getRow(1).getCell(2).getStringCellValue();
        String data3 = String.valueOf(wbSheet.getRow(1).getCell(3).getNumericCellValue());
        String data4 = wbSheet.getRow(1).getCell(4).getStringCellValue();
        String data5 = wbSheet.getRow(1).getCell(5).getStringCellValue();
        String data6 = wbSheet.getRow(1).getCell(6).getStringCellValue();
        String data7 = wbSheet.getRow(1).getCell(7).getStringCellValue();
        String data8 = wbSheet.getRow(1).getCell(8).getStringCellValue();
        String data9 = String.valueOf(wbSheet.getRow(1).getCell(9).getNumericCellValue());
        String data10 = wbSheet.getRow(1).getCell(10).getStringCellValue();
        String data11 = String.valueOf(wbSheet.getRow(1).getCell(11).getNumericCellValue());
        String data12 = wbSheet.getRow(1).getCell(12).getStringCellValue();
        String data13 = wbSheet.getRow(1).getCell(13).getStringCellValue();

        driver.findElement(By.id("inputFirstName")).sendKeys(data0);
        Thread.sleep(1000);
        driver.findElement(By.id("inputLastName")).sendKeys(data1);
        Thread.sleep(1000);
        driver.findElement(By.id("inputEmail")).sendKeys(data2);
        Thread.sleep(1000);
        driver.findElement(By.id("inputPhone")).sendKeys(data3);
        Thread.sleep(1000);
        driver.findElement(By.id("inputCompanyName")).sendKeys(data4);
        Thread.sleep(1000);
        driver.findElement(By.id("inputAddress1")).sendKeys(data5);
        Thread.sleep(1000);
        driver.findElement(By.id("inputAddress2")).sendKeys(data6);
        Thread.sleep(1000);
        driver.findElement(By.id("inputCity")).sendKeys(data7);
        Thread.sleep(1000);
        driver.findElement(By.id("stateinput")).sendKeys(data8);
        Thread.sleep(1000);
        driver.findElement(By.id("inputPostcode")).sendKeys(data9);
        Thread.sleep(1000);
        driver.findElement(By.id("inputCountry")).sendKeys(data10);
        Thread.sleep(1000);
        driver.findElement(By.id("customfield2")).sendKeys(data11);
        Thread.sleep(1000);
        driver.findElement(By.id("inputNewPassword1")).sendKeys(data12);
        Thread.sleep(1000);
        driver.findElement(By.id("inputNewPassword2")).sendKeys(data13);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"frmCheckout\"]/p/input")).click();
        Thread.sleep(4000);

        // Typecasting the driver reference variable with TakesScreenshot!
        // Typecasting will allow the methods from TakesScreenshot interface to access the file!
        // getScreenshotAs() will take argument for the output type of the file!
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        // Using the FileUtils class, we can copy the generated screenshot file to any location!
        FileUtils.copyFile(scrFile, new File(projectPath+ "/Screenshots/SelSheetRegister/Screenshot_1"));

        /*
        Writing in the Excel File!

        //Getting the current row in the sheet where we want to write the data!
		Row newRow = getSheet.getRow(1);

		//Creating a new cell with reference to the row!
		Cell cell = newRow.createCell(14);

		//Giving the value in the cell!
		cell.setCellValue(<"The Message to Display">);

		//Creating an output stream with the location where we want to create the file!
		FileOutputStream fileOutputStream = new FileOutputStream(<"filePath">);

		//Writing the workbook!
		workBook.write(fileOutputStream);

		//Closing the workbook!
		workBook.close();
         */
    }

    @After
    public void tearDown() throws Exception
    {
        driver.quit();

    }
}